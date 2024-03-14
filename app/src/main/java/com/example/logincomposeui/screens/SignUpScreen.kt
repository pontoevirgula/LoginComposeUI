package com.example.logincomposeui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logincomposeui.R
import com.example.logincomposeui.components.ButtonComponent
import com.example.logincomposeui.components.CheckboxComponent
import com.example.logincomposeui.components.ClickableLoginTextComponent
import com.example.logincomposeui.components.DividerItemComponent
import com.example.logincomposeui.components.ErrorComponent
import com.example.logincomposeui.components.HeaderTextComponent
import com.example.logincomposeui.components.NormalTextComponent
import com.example.logincomposeui.components.PasswordFieldComponent
import com.example.logincomposeui.components.TextFieldComponent
import com.example.logincomposeui.data.LoginViewModel
import com.example.logincomposeui.data.UIEvent
import com.example.logincomposeui.navigation.AppRouter
import com.example.logincomposeui.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpScreen(loginViewModel: LoginViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {

        val state = loginViewModel.registrationUIState
        ShowToastSuccess(loginViewModel)

        Column(Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.height(850.dp)) {
                items(1) {
                    NormalTextComponent(value = "Olá!")
                    HeaderTextComponent(value = "Crie sua conta")
                    Spacer(modifier = Modifier.height(28.dp))
                    TextFieldComponent(
                        labelText = "Primeiro Nome",
                        image = R.drawable.profile,
                        onTextChanged = {
                            loginViewModel.onEvent(UIEvent.FirstNameChanged(it))
                        },
                        errorStatus = loginViewModel.registrationUIState.firstNameError != null
                    )
                    ErrorComponent(state.firstNameError)

                    TextFieldComponent(
                        labelText = "Sobrenome",
                        image = R.drawable.profile,
                        onTextChanged = {
                            loginViewModel.onEvent(UIEvent.LastNameChanged(it))
                        },
                        errorStatus = loginViewModel.registrationUIState.lastNameError != null
                    )
                    ErrorComponent(state.lastNameError)

                    TextFieldComponent(
                        labelText = "email",
                        image = android.R.drawable.sym_action_email,
                        onTextChanged = {
                            loginViewModel.onEvent(UIEvent.EmailChanged(it))
                        },
                        errorStatus = loginViewModel.registrationUIState.emailError != null
                    )
                    ErrorComponent(state.emailError)

                    PasswordFieldComponent(
                        labelText = "senha",
                        onTextChanged = {
                            loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                        },
                        errorStatus = loginViewModel.registrationUIState.passwordError != null
                    )
                    ErrorComponent(state.passwordError)

                    PasswordFieldComponent(
                        labelText = "Repetir senha",
                        onTextChanged = {
                            loginViewModel.onEvent(UIEvent.RepeatPasswordChanged(it))
                        },
                        errorStatus = loginViewModel.registrationUIState.repeatPasswordError != null
                    )
                    ErrorComponent(state.repeatPasswordError)

                    CheckboxComponent(
                        isChecked = state.termsAccepted,
                        onChecked = {
                            loginViewModel.onEvent(UIEvent.TermsAcceptedChanged(it))
                        },
                        onTextSelected = {
                            AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                        }
                    )
                    ErrorComponent(state.termsAcceptedError)

                    Spacer(modifier = Modifier.height(80.dp))
                    ButtonComponent(
                        value = "CRIAR CONTA",
                        buttonClicked = {
                            loginViewModel.onEvent(UIEvent.ButtonClicked)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DividerItemComponent()
                    ClickableLoginTextComponent {
                        AppRouter.navigateTo(Screen.LoginScreen)
                    }
                }
            }
        }

    }
}

@Composable
private fun ShowToastSuccess(loginViewModel: LoginViewModel) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        loginViewModel.validationEvent.collectLatest { event ->
            when (event) {
                is LoginViewModel.ValidationSuccess.Success -> {
                    Toast.makeText(
                        context,
                        "Usuário criado com sucesso",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreviewSignUpScreen() {
    SignUpScreen()
}