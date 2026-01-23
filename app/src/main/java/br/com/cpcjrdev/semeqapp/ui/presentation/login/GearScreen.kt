package br.com.cpcjrdev.semeqapp.ui.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.cpcjrdev.semeqapp.ui.theme.PrimaryPink
import br.com.cpcjrdev.semeqapp.ui.theme.SemeqAppTheme

@Composable
fun GearScreenRoot(
    modifier: Modifier = Modifier,
    userName: String,
) {
    GearScreen(modifier = modifier, userName = userName)
}

@Composable
fun GearScreen(
    modifier: Modifier = Modifier,
    userName: String,
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
        Header(userName = userName)

        Card(
            modifier =
                Modifier
                    .fillMaxWidth(),
            shape =
                RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp,
                ),
            colors =
                CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
        ) {
            // conteudo do card

        }
    }
}

@Composable
private fun Header(userName: String) {
    Spacer(modifier = Modifier.height(32.dp))

    Text(
        text = "Hello",
        style = MaterialTheme.typography.headlineSmall,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 10.dp),
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(
        text = userName,
        style = MaterialTheme.typography.headlineSmall,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 10.dp),
    )

    Spacer(modifier = Modifier.height(32.dp))
}

@Preview
@Composable
private fun GearScreenPreview() {
    SemeqAppTheme {
        GearScreen(userName = "Username")
    }
}
