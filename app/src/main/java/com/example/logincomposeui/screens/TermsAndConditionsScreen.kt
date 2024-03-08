package com.example.logincomposeui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logincomposeui.components.HeaderTextComponent
import com.example.logincomposeui.navigation.AppRouter
import com.example.logincomposeui.navigation.Screen
import com.example.logincomposeui.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        HeaderTextComponent(value = "Termos de Uso")

        SystemBackButtonHandler{
            AppRouter.navigateTo(Screen.SignUpScreen)
        }
    }
}

@Composable
@Preview
fun TermsAndConditionsScreenPreview() {
    TermsAndConditionsScreen()
}