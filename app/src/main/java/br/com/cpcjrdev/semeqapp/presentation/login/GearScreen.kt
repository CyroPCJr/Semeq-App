package br.com.cpcjrdev.semeqapp.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.cpcjrdev.semeqapp.R
import br.com.cpcjrdev.semeqapp.domain.GearItem
import br.com.cpcjrdev.semeqapp.ui.theme.LightGrayBackground
import br.com.cpcjrdev.semeqapp.ui.theme.Pink80
import br.com.cpcjrdev.semeqapp.ui.theme.PrimaryPink
import br.com.cpcjrdev.semeqapp.ui.theme.SemeqAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GearScreenRoot(
    modifier: Modifier = Modifier,
    userName: String,
    onNavigateBack: () -> Unit,
) {
    val gearItems =
        remember {
            mutableListOf(
                GearItem("Sector", 0),
                GearItem("Area 1", 1),
                GearItem("Motor 1", 2),
                GearItem("Sensor 1", 3),
                GearItem("Sensor 2", 3),
                GearItem("Motor 2", 2),
                GearItem("Sensor 1", 3),
                GearItem("Sensor 2", 3),
                GearItem("Area 2", 1),
                GearItem("Motor 1", 2),
                GearItem("Sensor 1", 3),
                GearItem("Sensor 2", 3),
            )
        }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = PrimaryPink,
                        titleContentColor = PrimaryPink,
                    ),
            )
        },
    ) { innerPadding ->
        GearScreen(
            modifier = modifier.padding(innerPadding),
            userName = userName,
            gearItems = gearItems,
        )
    }
}

@Composable
fun GearScreen(
    modifier: Modifier = Modifier,
    userName: String,
    gearItems: List<GearItem> = emptyList(),
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
            HierarchycalContent(gearItems = gearItems)
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

@Composable
private fun HierarchycalContent(
    modifier: Modifier = Modifier,
    gearItems: List<GearItem> = emptyList(),
) {
    var editingText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        EditItemDialog(
            currentText = editingText,
            onConfirm = {
                showDialog = false
            },
            onDismiss = { showDialog = false },
        )
    }

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(16.dp),
    ) {
        gearItems.forEach { gearItems ->
            val icons =
                if (gearItems.type in 0..2) {
                    Icons.Outlined.Folder
                } else {
                    Icons.Outlined.Settings
                }
            HierarchyLabels(
                icon = icons,
                label = gearItems.name,
                level = gearItems.type,
                onEditClick = {
                    editingText = gearItems.name
                    showDialog = true
                },
            )
        }
    }
}

@Composable
private fun HierarchyLabels(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    level: Int,
    onEditClick: () -> Unit = {},
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(
                    start = (level * 24).dp,
                    top = 12.dp,
                    bottom = 12.dp,
                ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f),
        )

        if (level == 2) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = Pink80,
                modifier =
                    Modifier
                        .weight(.2f)
                        .clickable { onEditClick() },
            )
        }
    }
}

@Composable
fun EditItemDialog(
    currentText: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var text by remember { mutableStateOf(currentText) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(stringResource(R.string.dialog_edit_title))
        },
        text = {
            TextField(
                value = text,
                onValueChange = { text = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color.Gray,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(text) },
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = PrimaryPink,
                    ),
            ) {
                Text(
                    stringResource(R.string.dialog_confirm),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = LightGrayBackground,
                    ),
            ) {
                Text(
                    stringResource(R.string.dialog_cancel),
                    color = PrimaryPink,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
    )
}

@Preview
@Composable
private fun EditDialogPreview() {
    SemeqAppTheme {
        EditItemDialog("test", onConfirm = {}, onDismiss = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun HierarchyLabelsPreview() {
    SemeqAppTheme {
        HierarchyLabels(icon = Icons.Outlined.Folder, label = "Sector", level = 0)
    }
}

@Preview(showBackground = true)
@Composable
private fun HierarchicalContent() {
    SemeqAppTheme {
        HierarchycalContent(modifier = Modifier)
    }
}

@Preview
@Composable
private fun GearScreenPreview() {
    SemeqAppTheme {
        GearScreen(userName = "Username")
    }
}
