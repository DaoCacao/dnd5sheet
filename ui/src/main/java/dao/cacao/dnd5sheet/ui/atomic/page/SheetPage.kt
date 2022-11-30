package dao.cacao.dnd5sheet.ui.atomic.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.atomic.atom.BackIconButton
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SheetPage(
    title: String,
    level: String = "",
    characterName: String = "",
    characterRace: String = "",
    characterClass: String = "",
    onNavigateUp: (() -> Unit)? = null,
    onLevelChange: (String) -> Unit = {},
    onCharacterNameChange: (String) -> Unit = {},
    onCharacterRaceChange: (String) -> Unit = {},
    onCharacterClassChange: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = {
                    if (onNavigateUp != null) {
                        BackIconButton(onClick = onNavigateUp)
                    }
                }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = level,
                    onValueChange = onLevelChange,
                    label = { Text(text = "Level") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = characterName,
                    onValueChange = onCharacterNameChange,
                    label = { Text(text = "Name") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = characterRace,
                    onValueChange = onCharacterRaceChange,
                    label = { Text(text = "Race") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = characterClass,
                    onValueChange = onCharacterClassChange,
                    label = { Text(text = "Class") }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        SheetPage(
            title = "Title",
        )
    }
}