package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.presentation.component.state.LoadingState
import dao.cacao.dnd5sheet.ui.component.CounterField
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
    onAbilityChange: (abilityId: Long, value: Int) -> Unit = { _, _ -> },
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
                            items = state.abilities,
                            content = { ability ->
                                CounterField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = ability.value,
                                    label = ability.name,
                                    onValueChange = { onAbilityChange(ability.id, it) },
                                )
                            }
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
                abilities = List(6) {
                    SheetState.Content.Ability(
                        id = it.toLong(),
                        name = "Ability",
                        value = 20,
                    )
                }
            ),
        )
    }
}