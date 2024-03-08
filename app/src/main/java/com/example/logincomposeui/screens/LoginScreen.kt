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
import com.example.logincomposeui.components.ButtonComponent
import com.example.logincomposeui.components.ClickableLoginTextComponent
import com.example.logincomposeui.components.DividerItemComponent
import com.example.logincomposeui.components.HeaderTextComponent
import com.example.logincomposeui.components.NormalTextComponent
import com.example.logincomposeui.components.PasswordFieldComponent
import com.example.logincomposeui.components.TextFieldComponent
import com.example.logincomposeui.components.UnderlineClickableTextComponent
import com.example.logincomposeui.navigation.AppRouter
import com.example.logincomposeui.navigation.Screen
import com.example.logincomposeui.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            NormalTextComponent(value = "Olá!")
            HeaderTextComponent(value = "Bem vindo de volta")
            TextFieldComponent(labelText = "Email", image = android.R.drawable.ic_dialog_email)
            PasswordFieldComponent(labelText = "Senha")
            Spacer(modifier = Modifier.height(8.dp))
            UnderlineClickableTextComponent(value = "Esqueci a senha"){
                AppRouter.navigateTo(Screen.NewPasswordScreen)
            }
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(value = "Login")
            Spacer(modifier = Modifier.height(16.dp))
            DividerItemComponent()
            ClickableLoginTextComponent(goToLogin = false) {
                AppRouter.navigateTo(Screen.SignUpScreen)
            }
        }
    }

    SystemBackButtonHandler{
        AppRouter.navigateTo(Screen.SignUpScreen)
    }
}


@Composable
@Preview
fun DefaultPreviewLoginScreen(){
    LoginScreen()
}