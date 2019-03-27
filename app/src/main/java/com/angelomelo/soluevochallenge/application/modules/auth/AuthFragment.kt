package com.angelomelo.soluevochallenge.application.modules.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.application.modules.main.MainActivity
import com.angelomelo.soluevochallenge.application.utils.FragmentBase
import com.angelomelo.soluevochallenge.databinding.AuthFragmentBinding
import com.angelomelo.soluevochallenge.domain.User

class AuthFragment : FragmentBase(), AuthHandler {

    private lateinit var binding: AuthFragmentBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var validator: Validator

    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onResume() {
        super.onResume()
        clearForm()
    }

    private fun clearForm() {
        binding.user = User()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.auth_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        initElements()
    }

    private fun initElements() {
        setupFragmentBinding()
        setupValidator()
        initObserveOnSuccess()
        initOserveOnError()
    }

    private fun setupFragmentBinding() {
        binding.lifecycleOwner = this
        binding.handler        = this
        binding.viewModel      = viewModel
        binding.user           = User()
    }

    private fun setupValidator() {
        validator = Validator(binding)
        validator.enableFormValidationMode()
    }

    override fun auth(user: User) {
        if (validator.validate()) {
            viewModel.auth(user)
        }
    }

    private fun initObserveOnSuccess() {
        viewModel.successObserver.observe(this, Observer {
            goToMainScreen()
        })
    }

    private fun goToMainScreen() {
        startActivity(Intent(context, MainActivity::class.java))
    }

    private fun initOserveOnError() {
        viewModel.errorObserver.observe(this, Observer {
            showAlert(it)
        })
    }

}
