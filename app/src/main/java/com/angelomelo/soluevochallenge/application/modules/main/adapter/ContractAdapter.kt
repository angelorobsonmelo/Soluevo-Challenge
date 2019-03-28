package com.angelomelo.soluevochallenge.application.modules.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.main.viewHolder.ContractViewHolder
import com.angelomelo.soluevochallenge.domain.Contract


class ContractAdapter(private val contracts: MutableList<Contract>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    private var exampleListFull: List<Contract> = ArrayList(contracts)

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

    override fun getFilter(): Filter {
        return exampleFilter
    }

    private val exampleFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val filteredList = ArrayList<Contract>()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(exampleListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }

                for (item in exampleListFull) {
                    if (item.code.toString().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }

            val results = Filter.FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
            contracts.clear()
            contracts.addAll(results.values as List<Contract>)
            notifyDataSetChanged()
        }
    }

}