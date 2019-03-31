package com.angelomelo.soluevochallenge.application.modules.savecontract.attachmentsform

import com.angelomelo.soluevochallenge.domain.Attachment

interface AttachmentsHandler {

    fun onPressOpenImagePicker()
    fun onPressRemoveImage(attachment: Attachment)
    fun onPressBackToContractListScreen()
}