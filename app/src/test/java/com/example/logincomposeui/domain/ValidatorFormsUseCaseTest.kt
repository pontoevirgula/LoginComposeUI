package com.example.logincomposeui.domain

import org.junit.Assert
import org.junit.Test


class ValidatorFormsUseCaseTest {

    @Test
    fun `password contains read only , must return error`() {
        val result = ValidatorFormsUseCase.validatePassword("sdfsfs")
        Assert.assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `name size is smaller than 4, must return error`() {
        val result = ValidatorFormsUseCase.validateFirstName("car")
        Assert.assertEquals(result.isSuccessful, false)
    }
}