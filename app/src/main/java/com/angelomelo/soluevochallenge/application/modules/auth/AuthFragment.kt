package com.angelomelo.soluevochallenge.application.modules.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.ilhasoft.support.validation.Validator
import com.angelomelo.soluevochallenge.R
import com.angelomelo.soluevochallenge.databinding.AuthFragmentBinding
import com.angelomelo.soluevochallenge.domain.User

class AuthFragment : Fragment() {

    private lateinit var binding: AuthFragmentBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var validator: Validator

    companion object {
        fun newInstance() = AuthFragment()
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

    fun auth(user: User) {
        if (validator.validate()) {
//            viewModel.auth(user)
        }
    }

    fun getDetrans() : ArrayList<String> {
        return arrayListOf("RJ", "CE")
    }

}
