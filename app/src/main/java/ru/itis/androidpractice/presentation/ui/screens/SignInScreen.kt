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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    val errorTextHeight = 16.dp

    val state by signInViewModel.viewStates.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.logo_resized_512),
            modifier = Modifier
                .padding(top = 72.dp)
                .size(256.dp),
            contentDescription = stringResource(R.string.logo_place),
            alignment = Alignment.Center
        )

        OutlinedTextField(
            value = state.login,
            onValueChange = { signInViewModel.onLoginChanged(it) },
            placeholder = { Text(text = stringResource(R.string.login_place)) },
            isError = state.loginError != null,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 64.dp),
            singleLine = true,
        )
        Box(modifier = Modifier.height(errorTextHeight)) {
            state.loginError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        OutlinedTextField(
            value = state.textPassword,
            onValueChange = { signInViewModel.onPasswordChanged(it) },
            isError = state.passwordError != null,
            placeholder = { Text(text = stringResource(R.string.password_place)) },
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (state.passwordVisible) {
                VisualTransformation.None
            }
            else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (state.passwordVisible)
                    Icons.Default.Visibility
                else
                    Icons.Default.VisibilityOff

                IconButton(onClick = {
                    signInViewModel.togglePasswordVisibility()
                }) {
                    Icon(
                        imageVector = image,
                        contentDescription = if (state.passwordVisible) {
                            stringResource(R.string.password_visible)
                        }
                        else stringResource(R.string.password_not_visible)
                    )
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 8.dp)
        )
        Box(modifier = Modifier.height(errorTextHeight)) {
            state.passwordError?.let {
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
