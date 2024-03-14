package com.example.logincomposeui.data

data class RegistrationUIState (
    var firstName : String? = "",
    var firstNameError : String? = "",
    var lastName : String? = "",
    var lastNameError : String? = "",
    var email : String? = "",
    var emailError : String? = "",
    var password : String? = "",
    var passwordError : String? = "",
    var repeatPassword : String? = "",
    var repeatPasswordError : String? = "",
    var termsAccepted : Boolean = false,
    var termsAcceptedError : String? = ""

)