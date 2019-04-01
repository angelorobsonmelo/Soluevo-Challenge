package com.angelomelo.soluevochallenge.application.modules.savecontract

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.SoluevoChallengeApplication
import com.angelomelo.soluevochallenge.application.modules.main.MainActivity
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.state_progress_bar_base.*
import java.math.BigInteger

abstract class StateProgressBarBaseActivity : AppCompatActivity(), View.OnClickListener {

     private val personal = SoluevoChallengeApplication.instance.resources.getString(R.string.personal)
     private val vehicle = SoluevoChallengeApplication.instance.resources.getString(R.string.vehicle)
     private val creditor = SoluevoChallengeApplication.instance.resources.getString(R.string.creditor)
     private val contract = SoluevoChallengeApplication.instance.resources.getString(R.string.contract)
     private val attachments = SoluevoChallengeApplication.instance.resources.getString(R.string.attachments)
     private var descriptionData = arrayOf(personal, vehicle, creditor, contract, attachments)

     lateinit var nextBtn: Button
     lateinit var backBtn: Button
     lateinit var stateprogressbar: StateProgressBar


    protected fun setupToolbar(title: String) {
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_forms, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        goToContractsCreen()
        return super.onOptionsItemSelected(item)
    }

    private fun goToContractsCreen() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    protected fun injectCommonViews() {
        nextBtn = findViewById(R.id.btnNext)
        nextBtn.setOnClickListener(this)

        stateprogressbar = findViewById(R.id.usage_stateprogressbar)
        stateprogressbar.setStateDescriptionData(descriptionData)
    }

    protected fun injectBackView() {
        backBtn = findViewById(R.id.btnBack)
        backBtn.setOnClickListener(this)
    }

    fun showAlertSuccess(code: BigInteger) {
        val builder = AlertDialog.Builder(this)

        builder
            .setMessage(getString(R.string.contract_saved_code, code))
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->  }

        val alert = builder.create()
        alert.show()
    }

    fun showAlertSentImages() {
        val builder = AlertDialog.Builder(this)

        builder
            .setMessage(getString(R.string.all_selected_images_already_sent))
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->  }

        val alert = builder.create()
        alert.show()
    }

    fun showAlertSuccessAttachments(code: BigInteger) {
        val builder = AlertDialog.Builder(this)

        builder
            .setMessage(getString(R.string.attachments_save_successfully, code))
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->  }

        val alert = builder.create()
        alert.show()
    }

    fun showAlertErrorSubmittingImages() {
        val builder = AlertDialog.Builder(this)

        builder
            .setMessage(R.string.error_submiting_images)
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->  }

        val alert = builder.create()
        alert.show()
    }

    fun showAlertContractCodeNotEmpty() {
        val builder = AlertDialog.Builder(this)

        builder
            .setMessage(getString(R.string.fill_contract_code_field))
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->  }

        val alert = builder.create()
        alert.show()
    }


}