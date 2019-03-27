package com.angelomelo.soluevochallenge.application.modules.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.main.adapter.ContractAdapter
import com.angelomelo.soluevochallenge.application.utils.FragmentBase
import com.angelomelo.soluevochallenge.databinding.MainFragmentBinding
import com.angelomelo.soluevochallenge.domain.Contract
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import androidx.lifecycle.Observer

class MainFragment : FragmentBase() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        setupElements()

        Snackbar.make(view?.rootView!!, "Seja bem vindo", Snackbar.LENGTH_LONG)
            .show()
    }

    private fun setupElements() {
        setupRecyclerView()
        setupObserverOnSuccess()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.contractsRecyclerView
        val layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager
    }

    private fun setupObserverOnSuccess() {
        viewModel.successObserver.observe(this, Observer {
            setupAdapter(it)
        })
    }

    private fun setupAdapter(contracts: List<Contract>) {
        val adapter = ContractAdapter(contracts)
        binding.contractsRecyclerView.adapter = ScaleInAnimationAdapter(adapter).apply {
            setFirstOnly(false)
            setDuration(500)
            setInterpolator(OvershootInterpolator(.5f))
        }
    }

}
