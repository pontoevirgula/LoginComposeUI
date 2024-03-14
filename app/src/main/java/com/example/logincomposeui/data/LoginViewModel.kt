package com.example.logincomposeui.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logincomposeui.domain.ValidatorFormsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var registrationUIState by mutableStateOf(RegistrationUIState())

    private val validationEventChannel = Channel<ValidationSuccess>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    private val TAG = LoginViewModel::class.simpleName

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.FirstNameChanged -> {
                registrationUIState = registrationUIState.copy(
                    firstName = event.firstName
                )
            }

            is UIEvent.LastNameChanged -> {
                registrationUIState = registrationUIState.copy(
                    lastName = event.lastName
                )
            }

            is UIEvent.EmailChanged -> {
                registrationUIState = registrationUIState.copy(
                    email = event.email
                )
            }

            is UIEvent.PasswordChanged -> {
                registrationUIState = registrationUIState.copy(
                    password = event.password
                )
            }

            is UIEvent.RepeatPasswordChanged -> {
                registrationUIState = registrationUIState.copy(
                    repeatPassword = event.repeatPassword
                )
            }

            is UIEvent.TermsAcceptedChanged -> {
                registrationUIState = registrationUIState.copy(
                    termsAccepted = event.termsAccepted
                )
            }

            is UIEvent.ButtonClicked -> {
                Log.d(TAG, "Inside_signUp/Login")
                submit()
                Log.d(TAG, registrationUIState.toString())
            }
        }
    }

    private fun submit() {
        val firstNameValid = ValidatorFormsUseCase.validateFirstName(
            firstName = registrationUIState.firstName ?: ""
        )

        val lastNameValid = ValidatorFormsUseCase.validateLastName(
            lastName = registrationUIState.lastName ?: ""
        )

        val emailValid = ValidatorFormsUseCase.validateEmail(
            email = registrationUIState.email ?: ""
        )

        val passwordValid = ValidatorFormsUseCase.validatePassword(
            password = registrationUIState.password ?: ""
        )

        val repeatPasswordValid = ValidatorFormsUseCase.validateRepeatPassword(
            password = registrationUIState.password ?: "",
            repeatPassword = registrationUIState.repeatPassword ?: ""
        )

        val termsAcceptedValid = ValidatorFormsUseCase.validateTermsOfUse(
            termsOfUse = registrationUIState.termsAccepted
        )

        val hasError = listOf(
            firstNameValid,
            lastNameValid,
            emailValid,
            passwordValid,
            repeatPasswordValid,
            termsAcceptedValid
        ).any { !it.isSuccessful }

        registrationUIState = registrationUIState.copy(
            firstNameError = firstNameValid.errorMessage,
            lastNameError = lastNameValid.errorMessage,
            emailError = emailValid.errorMessage,
            passwordError = passwordValid.errorMessage,
            repeatPasswordError = repeatPasswordValid.errorMessage,
            termsAcceptedError = termsAcceptedValid.errorMessage
        )
        if (hasError) return

        viewModelScope.launch {
            validationEventChannel.send(ValidationSuccess.Success)
        }
    }

    sealed class ValidationSuccess {
        object Success : ValidationSuccess()
    }


}