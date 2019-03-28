package com.angelomelo.soluevochallenge.application.modules.main

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
import com.angelomelo.soluevochallenge.application.modules.main.adapter.ContractAdapter
import com.angelomelo.soluevochallenge.application.utils.FragmentBase
import com.angelomelo.soluevochallenge.databinding.MainFragmentBinding
import com.angelomelo.soluevochallenge.domain.Contract
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.main_activity.*

class MainFragment : FragmentBase() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ContractAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        setupElements()

        Snackbar.make(view?.rootView!!, "Seja bem vindo", Snackbar.LENGTH_LONG)
            .show()
    }

    private fun setupElements() {
        val toolbar = activity?.tollbarContracts as Toolbar
        setSupportActionBar(toolbar)

        setupRecyclerView()
        setupObserverOnSuccess()
    }

    private fun setSupportActionBar(toolbar: Toolbar) {
        val appCompatActivity = activity as AppCompatActivity?

        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(true)
        appCompatActivity?.supportActionBar?.title = "Contracts"

//        toolbar.toolbar_title.text = getString(R.string.event_detail_title)
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

    private fun setupAdapter(contracts: List<Contract>) {
        adapter = ContractAdapter(contracts as MutableList<Contract>)
        binding.contractsRecyclerView.adapter = ScaleInAnimationAdapter(adapter).apply {
            setFirstOnly(false)
            setDuration(500)
            setInterpolator(OvershootInterpolator(.5f))
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.inputType  = InputType.TYPE_CLASS_NUMBER

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)

    }

}
