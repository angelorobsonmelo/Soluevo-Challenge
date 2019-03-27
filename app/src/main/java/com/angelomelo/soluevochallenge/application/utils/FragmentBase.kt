package com.angelomelo.soluevochallenge.application.utils

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

open class FragmentBase: Fragment() {

    fun showAlert(message: String) {
        val builder = AlertDialog.Builder(context!!)

        builder
            .setMessage(message.toInt())
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->  }

        val alert = builder.create()
        alert.show()
    }

}