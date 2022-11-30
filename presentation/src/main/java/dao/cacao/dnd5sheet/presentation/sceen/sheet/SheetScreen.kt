package dao.cacao.dnd5sheet.presentation.sceen.sheet

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.presentation.component.Toolbar
import dao.cacao.dnd5sheet.presentation.component.state.ScaffoldLoadingState
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SheetScreen(
    state: SheetState,
//    level: String = "",
//    characterName: String = "",
//    characterRace: String = "",
//    characterClass: String = "",
    onNavigateUp: (() -> Unit)? = null,
    onLevelChange: (String) -> Unit = {},
    onCharacterNameChange: (String) -> Unit = {},
    onCharacterRaceChange: (String) -> Unit = {},
    onCharacterClassChange: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            Toolbar(
                title = "Character sheet",
                onNavigateUp = onNavigateUp,
            )
        },
    ) {
        when (state) {
            SheetState.Loading -> {
                ScaffoldLoadingState(paddingValues = it)
            }
            is SheetState.Content -> {
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
                            value = state.level,
                            onValueChange = onLevelChange,
                            label = { Text(text = "Level") }
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = state.characterName,
                            onValueChange = onCharacterNameChange,
                            label = { Text(text = "Name") }
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = state.characterRace,
                            onValueChange = onCharacterRaceChange,
                            label = { Text(text = "Race") }
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = state.characterClass,
                            onValueChange = onCharacterClassChange,
                            label = { Text(text = "Class") }
                        )
                    }
                }
            }
//            is SheetState.Loaded -> {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(it),
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .padding(16.dp),
//                        verticalArrangement = Arrangement.spacedBy(16.dp),
//                    ) {
//                        OutlinedTextField(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            value = level,
//                            onValueChange = onLevelChange,
//                            label = { Text(text = "Level") }
//                        )
//                        OutlinedTextField(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            value = characterName,
//                            onValueChange = onCharacterNameChange,
//                            label = { Text(text = "Name") }
//                        )
//                        OutlinedTextField(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            value = characterRace,
//                            onValueChange = onCharacterRaceChange,
//                            label = { Text(text = "Race") }
//                        )
//                        OutlinedTextField(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            value = characterClass,
//                            onValueChange = onCharacterClassChange,
//                            label = { Text(text = "Class") }
//                        )
//                    }
//                }
//            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewLoading() {
    AppTheme {
        SheetScreen(
            state = SheetState.Loading,
//            level = "1",
//            characterName = "name",
//            characterRace = "race",
//            characterClass = "class",
        )
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewContent() {
    AppTheme {
        SheetScreen(
            state = SheetState.Content(
                level = "1",
                characterName = "name",
                characterRace = "race",
                characterClass = "class",
            ),
//            level = "1",
//            characterName = "name",
//            characterRace = "race",
//            characterClass = "class",
        )
    }
}