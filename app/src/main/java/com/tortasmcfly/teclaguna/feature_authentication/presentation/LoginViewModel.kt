package com.tortasmcfly.teclaguna.feature_authentication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tortasmcfly.teclaguna.core.util.Resource
import com.tortasmcfly.teclaguna.feature_authentication.domain.use_case.IsLoggedInUseCase
import com.tortasmcfly.teclaguna.feature_authentication.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val isLoggedInUseCase: IsLoggedInUseCase
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            isLoggedInUseCase().collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        if(result.data ?: false) {
                            _eventFlow.emit(UIEvent.NavigateToHome)
                        }
                    }
                    is Resource.Error -> { }
                    is Resource.Loading -> { }
                }
            }
        }
    }

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.LoginStudent -> {
                login(event.controlNumber, event.password)
            }
        }
    }

    private fun login(controlNumber: String, password: String) {
        viewModelScope.launch {

            loginUseCase(controlNumber, password).onEach { result ->

                when(result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            loading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackbar(result.message ?: "Error desconocido"))
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            loading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            loading = false
                        )
                        _eventFlow.emit(UIEvent.NavigateToHome)
                    }
                }

            }.launchIn(this)

        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
        object NavigateToHome: UIEvent()
    }

}