package com.angelomelo.soluevochallenge.application.modules.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.main.viewHolder.ContractViewHolder
import com.angelomelo.soluevochallenge.domain.Contract

class ContractAdapter(private val contracts: List<Contract>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContractViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.contract_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding       = (holder as ContractViewHolder).binding
        binding?.contract = contracts[position]
        binding?.executePendingBindings()
    }

    override fun getItemCount() = contracts.size

}