package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.presentation.component.state.LoadingState
import dao.cacao.dnd5sheet.ui.component.Screen
import dao.cacao.dnd5sheet.ui.page.BlockAbilities
import dao.cacao.dnd5sheet.ui.page.BlockCommon
import dao.cacao.dnd5sheet.ui.page.SheetPage
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun SheetScreen(
    state: SheetState,
    onNavigateUp: (() -> Unit)? = null,
    onLevelChange: (Int) -> Unit = {},
    onCharacterNameChange: (String) -> Unit = {},
    onCharacterRaceChange: (String) -> Unit = {},
    onCharacterClassChange: (String) -> Unit = {},
    onStrengthChange: (Int) -> Unit = {},
    onDexterityChange: (Int) -> Unit = {},
    onConstitutionChange: (Int) -> Unit = {},
    onIntelligenceChange: (Int) -> Unit = {},
    onWisdomChange: (Int) -> Unit = {},
    onCharismaChange: (Int) -> Unit = {},
) {
    Screen(
        title = "Character sheet",
        onNavigateUp = onNavigateUp,
    ) {
        when (state) {
            SheetState.Loading -> {
                LoadingState()
            }
            is SheetState.Content -> {
                SheetPage(
                    common = {
                        BlockCommon(
                            level = state.level,
                            characterName = state.characterName,
                            characterRace = state.characterRace,
                            characterClass = state.characterClass,
                            onLevelChange = onLevelChange,
                            onCharacterNameChange = onCharacterNameChange,
                            onCharacterRaceChange = onCharacterRaceChange,
                            onCharacterClassChange = onCharacterClassChange,
                        )
                    },
                    abilities = {
                        BlockAbilities(
                            strength = state.strength,
                            dexterity = state.dexterity,
                            constitution = state.constitution,
                            intelligence = state.intelligence,
                            wisdom = state.wisdom,
                            charisma = state.charisma,
                            onStrengthChange = onStrengthChange,
                            onDexterityChange = onDexterityChange,
                            onConstitutionChange = onConstitutionChange,
                            onIntelligenceChange = onIntelligenceChange,
                            onWisdomChange = onWisdomChange,
                            onCharismaChange = onCharismaChange,
                        )
                    },
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        SheetScreen(
            state = SheetState.Content(
                level = 1,
                characterName = "name",
                characterRace = "race",
                characterClass = "class",
                strength = 20,
                dexterity = 20,
                constitution = 20,
                intelligence = 20,
                wisdom = 20,
                charisma = 20,
            ),
        )
    }
}