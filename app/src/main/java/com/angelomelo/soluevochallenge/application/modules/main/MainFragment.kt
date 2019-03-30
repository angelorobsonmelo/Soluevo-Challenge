package com.angelomelo.soluevochallenge.application.modules.main

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import com.angelomelo.soluevochallenge.application.modules.auth.AuthActivity
import com.angelomelo.soluevochallenge.application.modules.main.adapter.ContractAdapter
import com.angelomelo.soluevochallenge.application.modules.savecontract.personalform.PersonalFormActivity
import com.angelomelo.soluevochallenge.application.utils.FragmentBase
import com.angelomelo.soluevochallenge.databinding.MainFragmentBinding
import com.angelomelo.soluevochallenge.domain.response.ContractResponse
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.main_activity.*

class MainFragment : FragmentBase(), MainHandler {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ContractAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getContracts()
    }

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
        initAdapter()
        setupElements()
        showWelcomeMessage()
    }

    private fun initAdapter() {
        adapter = ContractAdapter(mutableListOf())
    }

    private fun showWelcomeMessage() {
        Snackbar.make(view?.rootView!!, getString(R.string.welcome), Snackbar.LENGTH_LONG)
            .show()
    }

    private fun setupElements() {
        setupBinding()
        setSupportActionBar()
        setupRecyclerView()
        setupObserverOnSuccess()
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
        appCompatActivity?.supportActionBar?.title = getString(R.string.contract)
    }

    private fun setupRecyclerView() {
        val gridLayout = GridLayoutManager(context, 2)
        val recyclerView = binding.contractsRecyclerView

        recyclerView.layoutManager = gridLayout
    }

    private fun setupObserverOnSuccess() {
        viewModel.successObserver.observe(this, Observer {
            setupAdapter(it)
        })
    }

    private fun setupAdapter(contractResponses: List<ContractResponse>) {
        adapter = ContractAdapter(contractResponses as MutableList<ContractResponse>)
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

        when (itemMenuId) {
            actionLogout -> {
                destroySessionAndGotoAuthScreen()
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

    private fun goToAuthScreen() {
        startActivity(Intent(context, AuthActivity::class.java))
    }

    override fun goToSaveContract() {
        startActivity(Intent(context, PersonalFormActivity::class.java))
    }

}
