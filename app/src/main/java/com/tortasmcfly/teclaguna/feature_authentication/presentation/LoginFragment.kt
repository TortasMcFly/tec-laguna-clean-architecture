package com.tortasmcfly.teclaguna.feature_authentication.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.tortasmcfly.teclaguna.core.presentation.main_activities.HomeActivity
import com.tortasmcfly.teclaguna.core.presentation.util.LoadingScreen
import com.tortasmcfly.teclaguna.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater)

        setUpEventFlow()
        setUpStateObservers()
        setUpOnClickListener()

        return binding.root
    }

    private fun setUpEventFlow() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        loginViewModel.eventFlow.collectLatest { event ->

            when(event) {
                is LoginViewModel.UIEvent.NavigateToHome -> {
                    val intent = Intent(context, HomeActivity::class.java)
                    context?.startActivity(intent)
                }
                is LoginViewModel.UIEvent.ShowSnackbar -> {
                    Snackbar.make(
                        binding.root,
                        event.message,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setUpStateObservers() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        loginViewModel.state.collectLatest { loginState ->
            binding.btnIniciarSesion.isEnabled = !loginState.loading

            if(loginState.loading) {
                LoadingScreen.displayLoading(binding.root.context)
            }
            else {
                LoadingScreen.hideLoading()
            }
        }
    }

    private fun setUpOnClickListener() {
        binding.btnIniciarSesion.setOnClickListener {
            binding.numeroControl.clearFocus()
            binding.password.clearFocus()

            loginViewModel.onEvent(LoginEvent.LoginStudent(
                binding.editTextNumeroControl.text.toString(),
                binding.editTextPassword.text.toString()
            ))
        }
    }

}