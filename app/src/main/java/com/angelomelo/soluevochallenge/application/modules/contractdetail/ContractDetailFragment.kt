package com.angelomelo.soluevochallenge.application.modules.contractdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.utils.FragmentBase

class ContractDetailFragment : FragmentBase() {

    companion object {
        fun newInstance() = ContractDetailFragment()
    }

    private lateinit var viewModel: ContractDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.contract_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContractDetailViewModel::class.java)

    }

}
