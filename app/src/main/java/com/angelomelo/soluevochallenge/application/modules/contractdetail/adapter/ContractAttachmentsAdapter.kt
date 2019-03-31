package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.viewholder.ContractAttachmentsViewHolder
import com.angelomelo.soluevochallenge.domain.response.AttachmentResponse

class ContractAttaschmentsAdapter(
    private val attachmentsResponse: MutableList<AttachmentResponse> )
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContractAttachmentsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.contract_attachment_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding       = (holder as ContractAttachmentsViewHolder).binding
        binding?.attachmentResponse = attachmentsResponse[position]
        binding?.executePendingBindings()
    }

    override fun getItemCount() = attachmentsResponse.size

}