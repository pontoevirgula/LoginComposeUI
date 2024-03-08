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
import com.example.logincomposeui.R
import com.example.logincomposeui.components.ButtonComponent
import com.example.logincomposeui.components.HeaderTextComponent
import com.example.logincomposeui.components.CheckboxComponent
import com.example.logincomposeui.components.ClickableLoginTextComponent
import com.example.logincomposeui.components.DividerItemComponent
import com.example.logincomposeui.components.PasswordFieldComponent
import com.example.logincomposeui.components.TextFieldComponent
import com.example.logincomposeui.components.NormalTextComponent
import com.example.logincomposeui.navigation.AppRouter
import com.example.logincomposeui.navigation.Screen

@Composable
fun SignUpScreen(){
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
            TextFieldComponent(labelText = "Primeiro Nome", R.drawable.profile)
            TextFieldComponent(labelText = "Sobrenome", R.drawable.profile)
            TextFieldComponent(labelText = "email", android.R.drawable.sym_action_email)
            PasswordFieldComponent(labelText = "senha")
            CheckboxComponent{
                AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
            }
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(value = "CRIAR CONTA")
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