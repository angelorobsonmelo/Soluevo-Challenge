package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.viewholder.AttachmentsViewHolder
import com.angelomelo.soluevochallenge.domain.Attachment

class AttachmentsAdapter(private val attachments: MutableList<Attachment>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AttachmentsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.attachment_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding       = (holder as AttachmentsViewHolder).binding
        binding?.image = attachments[position]
        binding?.executePendingBindings()
    }

    override fun getItemCount() = attachments.size

}