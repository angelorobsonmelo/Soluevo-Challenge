package com.angelomelo.soluevochallenge.application.modules.main

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import com.angelomelo.soluevochallenge.application.modules.account.AccountActivity
import com.angelomelo.soluevochallenge.application.modules.auth.AuthActivity
import com.angelomelo.soluevochallenge.application.modules.contractdetail.ContractDetailActivity
import com.angelomelo.soluevochallenge.application.modules.main.adapter.ContractAdapter
import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.AttachmentsFormActivity
import com.angelomelo.soluevochallenge.application.utils.FragmentBase
import com.angelomelo.soluevochallenge.application.utils.RecyclerItemClickListener
import com.angelomelo.soluevochallenge.application.utils.extensions.getFileName
import com.angelomelo.soluevochallenge.databinding.MainFragmentBinding
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse
import com.angelomelo.soluevochallenge.domain.response.ContractResponse
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.main_activity.*

class MainFragment : FragmentBase(), MainHandler {

    companion object {
        const val CONTRACT_RESPONSE_IDENTIFIER = "CONTRACT_RESPONSE_IDENTIFIER"
        fun newInstance() = MainFragment()

        fun getOnlyImageAttachments(attachments: List<AttachmentResponse>): List<AttachmentResponse> {
            return attachments.filter {
                it.fileName.getFileName().toLowerCase() == "png" ||
                        it.fileName.getFileName().toLowerCase() == "jpg" ||
                        it.fileName.getFileName() == "JPEG"
            }.map {
                it.urlPath = "http://159.65.244.68/assets/${it.fileName}"
                it
            }
        }

    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ContractAdapter
    private var contracs = mutableListOf<ContractResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.main_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setupElements()
        showWelcomeMessage()
    }

    private fun initAdapter() {
        adapter = ContractAdapter(mutableListOf())
    }

    private fun showWelcomeMessage() {
      val user = getUserInSession()
        Snackbar.make(view?.rootView!!, getString(R.string.welcome, user?.name,  user?.ufDetran), 8000)
            .show()
    }

    private fun getUserInSession() = SoluevoChallengeApplication.mSessionUseCase?.getAuthSession()?.user

    private fun setupElements() {
        initAdapter()
        setupBinding()
        setSupportActionBar()
        setupRecyclerView()
        initObserverOnSuccess()
        initObserverAttachmentOnSuccess()
        initObserverOnError()
        initRecyclerItemClickListener()
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.hander = this
    }

    private fun setSupportActionBar() {
        setHasOptionsMenu(true)
        val toolbar = activity?.tollbarContracts as Toolbar
        val appCompatActivity = activity as AppCompatActivity?

        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(true)
        appCompatActivity?.supportActionBar?.title = getString(R.string.contracts)
    }

    private fun setupRecyclerView() {
        adapter = ContractAdapter(contracs)

        val gridLayout = GridLayoutManager(context, 2)
        val recyclerView = binding.contractsRecyclerView

        recyclerView.layoutManager = gridLayout
    }

    private fun initObserverOnSuccess() {
        viewModel.successObserver.observe(this, Observer {
            contracs.addAll(it)
            viewModel.getAttachments()
        })
    }

    private fun initObserverAttachmentOnSuccess() {
        viewModel.attachmentsOnsuccessObserver.observe(this, Observer {
          val imagesAttachments = getOnlyImageAttachments(it)
            checkIfContractsHaveAttachments(imagesAttachments)
            setupAdapter()
        })
    }

    private fun checkIfContractsHaveAttachments(imagesAttachments: List<AttachmentResponse>) {
        contracs.forEachIndexed { indexContract, contractResponse ->
            imagesAttachments.forEachIndexed { _, attachmentResponse ->
                if (haveAttachmentInContract(contractResponse, attachmentResponse)) {
                    contracs[indexContract].haveAttachments = true
                }
            }
        }
    }

    private fun haveAttachmentInContract(
        contractResponse: ContractResponse,
        attachmentResponse: AttachmentResponse
    ) = contractResponse.code.toBigInteger() == attachmentResponse.contractCode

    private fun initObserverOnError() {
        viewModel.errorObserver.observe(this, Observer {
            showAlert(it)
        })
    }

    private fun setupAdapter() {
        adapter = ContractAdapter(contracs)
        binding.contractsRecyclerView.adapter = ScaleInAnimationAdapter(adapter).apply {
            setFirstOnly(false)
            setDuration(500)
            setInterpolator(OvershootInterpolator(.5f))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter, menu)
        val searchView = setupSearchView(menu)
        setQueryTextListener(searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun setupSearchView(menu: Menu): SearchView {
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.inputType = InputType.TYPE_CLASS_NUMBER
        return searchView
    }

    private fun setQueryTextListener(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemMenuId            = item.itemId
        val actionLogout = R.id.action_logout
        val account = R.id.action_account

        when (itemMenuId) {
            actionLogout -> {
                destroySessionAndGotoAuthScreen()
            }
            account -> {
                goToAccountScreen()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun destroySessionAndGotoAuthScreen() {
        val sessionIsDestroyed = SoluevoChallengeApplication.mSessionUseCase?.destroySession()
        if (sessionIsDestroyed!!) {
            goToAuthScreen()
        }
    }

    private fun goToAccountScreen() {
        startActivity(Intent(context, AccountActivity::class.java))
    }

    private fun goToAuthScreen() {
        startActivity(Intent(context, AuthActivity::class.java))
    }

    override fun goToSaveContract() {
        startActivity(Intent(context, AttachmentsFormActivity::class.java))
//        startActivity(Intent(context, PersonalFormActivity::class.java))
    }


    private fun initRecyclerItemClickListener() {
        binding.contractsRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                binding.contractsRecyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                          goToContractDetail(position)
                    }

                    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    }

                    override fun onLongItemClick(view: View?, position: Int) {

                    }

                }
            )
        )
    }

    fun goToContractDetail(contractPosition: Int) {
        val intent = Intent(context, ContractDetailActivity::class.java)
        val contractResponse = contracs[contractPosition]

        intent.putExtra(CONTRACT_RESPONSE_IDENTIFIER, contractResponse)
        startActivity(intent)
    }

}
