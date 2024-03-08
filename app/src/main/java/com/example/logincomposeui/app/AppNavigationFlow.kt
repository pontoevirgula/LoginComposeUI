package com.example.logincomposeui.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.logincomposeui.navigation.AppRouter
import com.example.logincomposeui.navigation.Screen
import com.example.logincomposeui.screens.LoginScreen
import com.example.logincomposeui.screens.NewPasswordScreen
import com.example.logincomposeui.screens.SignUpScreen
import com.example.logincomposeui.screens.TermsAndConditionsScreen

@Composable
fun AppNavigationFlow(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = AppRouter.currentScreen, label = "screens") { currentState->
            when(currentState.value){
                is Screen.SignUpScreen -> SignUpScreen()
                is Screen.TermsAndConditionsScreen -> TermsAndConditionsScreen()
                is Screen.LoginScreen -> LoginScreen()
                is Screen.NewPasswordScreen -> NewPasswordScreen()
            }
        }
    }
}