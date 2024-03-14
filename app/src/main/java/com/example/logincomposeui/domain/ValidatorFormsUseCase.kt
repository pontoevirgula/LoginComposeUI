package com.example.logincomposeui.domain

import android.util.Patterns

object ValidatorFormsUseCase {
    fun validateFirstName(firstName: String): ValidateResult {
        if (firstName.isBlank())
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Primeiro nome não pode estar vazio"
            )

        if (firstName.length < 4)
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Primeiro nome não pode ser menor que 4 caracteres"
            )

        return ValidateResult(
            isSuccessful = true
        )
    }

    fun validateLastName(lastName: String): ValidateResult {
        if (lastName.isBlank())
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Sobrenome não pode estar vazio"
            )

        if (lastName.length < 4)
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Sobrenome não pode ser menor que 4 caracteres"
            )

        return ValidateResult(
            isSuccessful = true
        )
    }

    fun validateEmail(email: String): ValidateResult {
        if (email.isBlank())
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "email não pode estar vazio"
            )

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "email inválido"
            )

        return ValidateResult(
            isSuccessful = true
        )
    }

    fun validatePassword(password: String): ValidateResult {
        if (password.isBlank() && password.length < 8)
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Senha não pode ser menor que 8 caracteres"
            )

        val containLettersAndDigits =
            password.any { it.isLetter() } && password.any { it.isDigit() }
        if (!containLettersAndDigits)
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Senha deve conter pelo menos uma letra e um numero"
            )

        return ValidateResult(
            isSuccessful = true
        )

    }

    fun validateRepeatPassword(repeatPassword: String, password: String): ValidateResult {
        if (repeatPassword.isBlank() && repeatPassword.length < 8)
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Senha não pode ser menor que 8 caracteres"
            )

        val containLettersAndDigits =
            repeatPassword.any { it.isLetter() } && repeatPassword.any { it.isDigit() }
        if (!containLettersAndDigits)
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Senha deve conter pelo menos uma letra e um numero"
            )

        if (repeatPassword != password){
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Campos senha e repetir senha devem ser iguais"
            )
        }

        return ValidateResult(
            isSuccessful = true
        )
    }

    fun validateTermsOfUse(termsOfUse : Boolean) : ValidateResult{
        if (!termsOfUse){
            return ValidateResult(
                isSuccessful = false,
                errorMessage = "Por favor, aceite os termos para que a conta seja criada"
            )
        }
        return ValidateResult(
            isSuccessful = true
        )
    }

}


data class ValidateResult(
    var isSuccessful: Boolean = false,
    var errorMessage: String? = ""
)