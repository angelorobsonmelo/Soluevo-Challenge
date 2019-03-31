package com.angelomelo.soluevochallenge.application.modules.contractdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.main.MainFragment
import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.adapter.ContractAttaschmentsAdapter
import com.angelomelo.soluevochallenge.application.utils.FragmentBase
import com.angelomelo.soluevochallenge.databinding.ContractDetailFragmentBinding
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse
import com.angelomelo.soluevochallenge.domain.response.ContractResponse
import kotlinx.android.synthetic.main.contract_detail_activity.*

class ContractDetailFragment : FragmentBase() {

    companion object {
        fun newInstance() = ContractDetailFragment()
    }

    private lateinit var viewModel: ContractDetailViewModel
    private lateinit var binding: ContractDetailFragmentBinding
    private lateinit var contractResponse: ContractResponse
    private lateinit var adapter: ContractAttaschmentsAdapter
    private var attachmentsResponse = mutableListOf<AttachmentResponse>()


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
        contractResponse = getContractResponse()
        binding.contractResponse = contractResponse
        initAdapter()
        setupRecyclerView()
        getAttachments()
        setSupportActionBar()
        initObserverOnSuccess()
        initObserverOnError()
        initObserverOnEmpty()
    }

    private fun initAdapter() {
        adapter = ContractAttaschmentsAdapter(attachmentsResponse)
    }

    private fun setupRecyclerView() {
        val linearLayoutHorizontal = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val recyclerView = binding.contractAttachmentsRecycler

        recyclerView.layoutManager = linearLayoutHorizontal
    }

    private fun getAttachments() {
        viewModel.getAttachments(contractResponse.code.toBigInteger())
    }

    private fun getContractResponse() = arguments?.get(MainFragment.CONTRACT_RESPONSE_IDENTIFIER) as ContractResponse

    private fun setSupportActionBar() {
        setHasOptionsMenu(true)
        val toolbar = activity?.tollbarContractDetail as Toolbar
        val appCompatActivity = activity as AppCompatActivity?

        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(true)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity?.supportActionBar?.title = getString(R.string.contract_details)
    }

    private fun initObserverOnSuccess() {
        viewModel.successObserver.observe(this, Observer {
            attachmentsResponse.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initObserverOnError() {
        viewModel.errorObserver.observe(this, Observer {

        })
    }

    private fun initObserverOnEmpty() {
        viewModel.emptyObserver.observe(this, Observer {

        })
    }

}