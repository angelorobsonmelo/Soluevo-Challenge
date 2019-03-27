package com.angelomelo.soluevochallenge.application.modules.main.viewHolder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.soluevochallenge.databinding.ContractItemBinding

class ContractViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding: ContractItemBinding? = DataBindingUtil.bind(view)
}