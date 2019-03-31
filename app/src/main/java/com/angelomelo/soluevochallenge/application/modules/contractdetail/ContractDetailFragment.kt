package com.angelomelo.soluevochallenge.application.modules.contractdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.ViewModelProviders
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.utils.FragmentBase
import com.angelomelo.soluevochallenge.databinding.ContractDetailFragmentBinding
import kotlinx.android.synthetic.main.contract_detail_activity.*

class ContractDetailFragment : FragmentBase() {

    companion object {
        fun newInstance() = ContractDetailFragment()
    }

    private lateinit var viewModel: ContractDetailViewModel
    private lateinit var binding: ContractDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.contract_detail_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContractDetailViewModel::class.java)
        setSupportActionBar()
    }

    private fun setSupportActionBar() {
        setHasOptionsMenu(true)
        val toolbar = activity?.tollbarContractDetail as Toolbar
        val appCompatActivity = activity as AppCompatActivity?

        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(true)
        appCompatActivity?.supportActionBar?.title = getString(R.string.contract_details)
    }
}