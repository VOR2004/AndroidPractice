package ru.itis.androidpractice.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import ru.itis.androidpractice.presentation.ui.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToSignIn: () -> Unit,
) {
    val errorTextHeight = 16.dp

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.logo_resized_512),
            contentDescription = stringResource(R.string.logo_place),
            modifier = Modifier
                .padding(top = 64.dp)
                .size(256.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = registerViewModel.textLogin,
            onValueChange = { registerViewModel.onLoginChanged(it) },
            isError = registerViewModel.loginError != null,
            placeholder = { Text(text = stringResource(R.string.login_place)) },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 64.dp)
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
            value = registerViewModel.textPassword,
            onValueChange = { registerViewModel.onPasswordChanged(it) },
            isError = registerViewModel.passwordError != null,
            placeholder = { Text(text = stringResource(R.string.password_place)) },
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
            value = registerViewModel.textNickname,
            onValueChange = { registerViewModel.onNicknameChanged(it) },
            isError = registerViewModel.nicknameError != null,
            placeholder = { Text(text = stringResource(R.string.nickname_place)) },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp)
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

        ButtonDefault(
            text = stringResource(R.string.sign_up),
            onClick = { registerViewModel.registerUser(onSuccess = onNavigateToSignIn) }
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(top = 24.dp, start = 32.dp, end = 32.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.enter_sign_in),
                color = colorResource(R.color.default_secondary_gray_color)
            )

            ButtonEnter(onClick = onNavigateToSignIn)
        }
    }
}
