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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.itis.androidpractice.R
import ru.itis.androidpractice.presentation.ui.uiparts.buttons.ButtonDefault
import ru.itis.androidpractice.presentation.ui.uiparts.buttons.ButtonEnter
import ru.itis.androidpractice.presentation.ui.viewmodel.SignInViewModel


@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onNavigateToMain: () -> Unit,
    onSignedIn: () -> Unit
) {
    val login = signInViewModel.login
    val password = signInViewModel.password
    val loginError = signInViewModel.loginError
    val passwordError = signInViewModel.passwordError

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
            value = login,
            onValueChange = { signInViewModel.onLoginChanged(it) },
            placeholder = { Text(text = stringResource(R.string.login_place)) },
            isError = loginError != null,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 64.dp),
        )
        Box(modifier = Modifier.height(errorTextHeight)) {
            loginError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        OutlinedTextField(
            value = password,
            onValueChange = { signInViewModel.onPasswordChanged(it) },
            placeholder = { Text(text = stringResource(R.string.password_place)) },
            isError = passwordError != null,
            shape = RoundedCornerShape(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 8.dp)
        )
        Box(modifier = Modifier.height(errorTextHeight)) {
            passwordError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        ButtonDefault(
            text = stringResource(R.string.sign_in),
            onClick = {
                signInViewModel.signIn(
                    onSuccess = {
                        onNavigateToMain()
                        onSignedIn()
                    }
                )
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
                text = stringResource(R.string.enter_sign_up),
                modifier = Modifier
                    .padding(top = 16.dp),
                color = colorResource(R.color.default_secondary_gray_color)
            )

            ButtonEnter(onClick = onNavigateToRegister)
        }
    }
}
