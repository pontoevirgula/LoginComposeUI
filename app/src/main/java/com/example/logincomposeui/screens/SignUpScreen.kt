package com.example.logincomposeui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logincomposeui.R
import com.example.logincomposeui.components.ButtonComponent
import com.example.logincomposeui.components.HeaderTextComponent
import com.example.logincomposeui.components.CheckboxComponent
import com.example.logincomposeui.components.ClickableLoginTextComponent
import com.example.logincomposeui.components.DividerItemComponent
import com.example.logincomposeui.components.PasswordFieldComponent
import com.example.logincomposeui.components.TextFieldComponent
import com.example.logincomposeui.components.NormalTextComponent
import com.example.logincomposeui.data.LoginViewModel
import com.example.logincomposeui.data.UIEvent
import com.example.logincomposeui.navigation.AppRouter
import com.example.logincomposeui.navigation.Screen

@Composable
fun SignUpScreen(loginViewModel: LoginViewModel = viewModel()){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column( Modifier.fillMaxSize()) {
            NormalTextComponent(value = "Olá!")
            HeaderTextComponent(value = "Crie sua conta")
            Spacer(modifier = Modifier.height(28.dp))
            TextFieldComponent(
                labelText = "Primeiro Nome",
                image = R.drawable.profile,
                onTextChanged = {
                    loginViewModel.onEvent(UIEvent.FirstNameChanged(it))
                }
            )
            TextFieldComponent(
                labelText = "Sobrenome",
                image = R.drawable.profile,
                onTextChanged = {
                    loginViewModel.onEvent(UIEvent.LastNameChanged(it))
                }
            )
            TextFieldComponent(
                labelText = "email",
                image = android.R.drawable.sym_action_email,
                onTextChanged = {
                    loginViewModel.onEvent(UIEvent.EmailChanged(it))
                }
            )
            PasswordFieldComponent(
                labelText = "senha",
                onTextChanged = {
                    loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                }
            )
            CheckboxComponent{
                AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
            }
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(
                value = "CRIAR CONTA",
                buttonClicked = {
                    loginViewModel.onEvent(UIEvent.ButtonClicked)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            DividerItemComponent()
            ClickableLoginTextComponent{
                AppRouter.navigateTo(Screen.LoginScreen)
            }
        }

    }
}

@Preview
@Composable
fun DefaultPreviewSignUpScreen(){
    SignUpScreen()
}