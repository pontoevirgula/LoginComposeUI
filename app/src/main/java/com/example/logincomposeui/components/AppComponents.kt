package com.example.logincomposeui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logincomposeui.R
import com.example.logincomposeui.ui.theme.componentShape

@Composable
fun NormalTextComponent(value: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(20.dp),
        text = value,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeaderTextComponent(value: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        text = value,
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@SuppressLint("PrivateResource")
@Composable
fun TextFieldComponent(labelText: String, image: Int) {
    val text = remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = text.value,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = colorResource(id = R.color.colorPrimary),
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
        ),
        onValueChange = { text.value = it },
        label = { Text(labelText) },
        leadingIcon = {
            Icon(painter = painterResource(id = image), contentDescription = "item do formulario")
        }
    )
}

@Composable
fun PasswordFieldComponent(labelText: String) {
    val localFocusManager = LocalFocusManager.current
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        value = password.value,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = colorResource(id = R.color.colorPrimary),
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
        ),
        onValueChange = { password.value = it },
        label = { Text(labelText) },
        leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.password), contentDescription = "senha")
        },
        trailingIcon = {
            val iconImage =
                if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (passwordVisible.value) "Exibir senha" else "Esconder senha"
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun CheckboxComponent(onTextSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val isChecked = remember { mutableStateOf(false) }
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value != isChecked.value })
        ClickableTextComponent(onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(onTextSelected: (String) -> Unit) {
    val initialText = "Ao selecionar checkbox, você concorda com nossas "
    val privacyPolicyText = "politicas de privacidades "
    val andText = "e nossos "
    val termsOfUseText = "termos de uso."
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_500))) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_500))) {
            pushStringAnnotation(tag = termsOfUseText, annotation = termsOfUseText)
            append(termsOfUseText)
        }
    }
    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == termsOfUseText || span.item == privacyPolicyText) {
                        onTextSelected(span.item)
                    }
                }
        })
}

@Composable
fun ButtonComponent(value: String) {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            colorResource(id = R.color.colorSecondary),
                            colorResource(id = R.color.colorPrimary)
                        )
                    ),
                    shape = RoundedCornerShape(50.dp),
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DividerItemComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorResource(id = R.color.colorGray),
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = "ou",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = colorResource(id = R.color.colorText)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorResource(id = R.color.colorGray),
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(goToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText = if (goToLogin) "Ja tem uma conta? " else "Ainda não tem uma conta? "
    val clickableText = if (goToLogin) "Clique aqui pra logar" else "Clique aqui!"

    val annotatedStringLogin = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_500))) {
            pushStringAnnotation(tag = clickableText, annotation = clickableText)
            append(clickableText)
        }
    }

    val annotatedStringNewAccount = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_500))) {
            pushStringAnnotation(tag = clickableText, annotation = clickableText)
            append(clickableText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(20.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = if (goToLogin) annotatedStringLogin else annotatedStringNewAccount,
        onClick = { offset ->
            annotatedStringNewAccount.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == clickableText) {
                        onTextSelected(span.item)
                    }
                }

            annotatedStringLogin.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == clickableText) {
                        onTextSelected(span.item)
                    }
                }
        })
}

@Composable
fun UnderlineClickableTextComponent(value: String, onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(20.dp)
            .clickable {
                onClick()
            },
        text = value,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorGray),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline,
    )
}
