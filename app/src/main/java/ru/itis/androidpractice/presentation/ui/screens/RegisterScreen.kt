package ru.itis.androidpractice.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.itis.androidpractice.presentation.ui.uiparts.buttons.ButtonDefault
import ru.itis.androidpractice.R
import ru.itis.androidpractice.presentation.ui.uiparts.buttons.ButtonEnter
import ru.itis.androidpractice.presentation.ui.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToSignIn: () -> Unit,
    onNavigateToMainScreen: () -> Unit
) {
    var textLogin by remember { mutableStateOf(registerViewModel.textLogin) }
    var textPassword by remember { mutableStateOf(registerViewModel.textPassword) }
    var textNickname by remember { mutableStateOf(registerViewModel.textNickname) }

    val errorTextHeight = 16.dp

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(R.drawable.logo_resized_512),
            modifier = Modifier
                .padding(top = 64.dp)
                .size(256.dp),
            contentDescription = stringResource(R.string.logo_place),
            alignment = Alignment.Center
            )

        OutlinedTextField(
            value = textLogin,
            onValueChange = {
                textLogin = it
                registerViewModel.onLoginChanged(it)
                            },
            isError = registerViewModel.loginError != null,
            placeholder = { Text(text = stringResource(R.string.login_place)) },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 64.dp),
        )
        Box(modifier = Modifier.height(errorTextHeight)) {
            registerViewModel.loginError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        OutlinedTextField(
            value = textPassword,
            onValueChange = {
                textPassword = it
                registerViewModel.onPasswordChanged(it)
                            },
            placeholder = { Text(text = stringResource(R.string.password_place)) },
            isError = registerViewModel.passwordError != null,
            shape = RoundedCornerShape(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 8.dp)
        )
        Box(modifier = Modifier.height(errorTextHeight)) {
            registerViewModel.passwordError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        TextField(
            value = textNickname,
            onValueChange = {
                textNickname = it
                registerViewModel.onNicknameChanged(it)
                            },
            placeholder = {
                Text(text = stringResource(R.string.nickname_place)) },
            isError = registerViewModel.nicknameError != null,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 24.dp)
        )
        Box(modifier = Modifier.height(errorTextHeight)) {
            registerViewModel.nicknameError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        ButtonDefault(text = stringResource(R.string.sign_up),
            onClick = {
                registerViewModel.registerUser(onSuccess = onNavigateToMainScreen)
            }
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 32.dp,
                    end = 32.dp
                )
        )

        Row {
            Text(
                text = stringResource(R.string.enter_sign_in),
                modifier = Modifier
                    .padding(top = 16.dp),
                color = colorResource(R.color.default_secondary_gray_color)
            )

            ButtonEnter(onClick = onNavigateToSignIn)
        }

    }
}