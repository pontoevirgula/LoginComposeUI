package com.example.logincomposeui.data

sealed class UIEvent {
    data class FirstNameChanged(var firstName : String) : UIEvent()
    data class LastNameChanged(var lastName : String) : UIEvent()
    data class EmailChanged(var email : String) : UIEvent()
    data class PasswordChanged(var password : String) : UIEvent()
    object ButtonClicked : UIEvent()

}