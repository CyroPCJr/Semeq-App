package br.com.cpcjrdev.semeqapp.ui.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.cpcjrdev.semeqapp.R
import br.com.cpcjrdev.semeqapp.ui.theme.PrimaryPink
import br.com.cpcjrdev.semeqapp.ui.theme.SemeqAppTheme

@Composable
fun LoginScreenRoot(modifier: Modifier = Modifier) {
    LoginScreen(modifier = modifier)
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .background(PrimaryPink)
                .imePadding(),
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            tint = Color.White,
            modifier =
                Modifier
                    .size(96.dp)
                    .align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier =
                Modifier
                    .fillMaxWidth(),
            shape =
                RoundedCornerShape(
                    topStart = 50.dp,
                ),
            colors =
                CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
        ) {
            LoginContent(
                modifier = Modifier.padding(24.dp),
                onLoginClick = onLoginClick,
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun LoginContent(
    modifier: Modifier,
    onLoginClick: () -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.headlineSmall,
            color = PrimaryPink,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextField(
            label = stringResource(R.string.user),
            value = username,
            onValueChange = { username = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            label = stringResource(R.string.password),
            value = password,
            onValueChange = { password = it },
            isPassword = true,
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onLoginClick,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(52.dp),
            shape = RoundedCornerShape(26.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = PrimaryPink,
                ),
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = PrimaryPink,
            fontSize = 14.sp,
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            visualTransformation =
                if (isPassword) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
            colors =
                TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,
                ),
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    SemeqAppTheme {
        LoginScreen()
    }
}
