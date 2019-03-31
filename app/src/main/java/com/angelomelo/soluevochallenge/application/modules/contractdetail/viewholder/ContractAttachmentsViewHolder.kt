package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.angelomelo.soluevochallenge.databinding.ContractAttachmentItemBinding

class ContractAttachmentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ContractAttachmentItemBinding? = DataBindingUtil.bind(view)
}