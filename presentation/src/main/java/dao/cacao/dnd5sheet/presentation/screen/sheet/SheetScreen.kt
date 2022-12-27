package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dao.cacao.dnd5sheet.presentation.R
import dao.cacao.dnd5sheet.ui.component.Screen
import dao.cacao.dnd5sheet.ui.component.block.AbilitiesBlock
import dao.cacao.dnd5sheet.ui.component.block.CommonBlock
import dao.cacao.dnd5sheet.ui.component.block.SkillsBlock
import dao.cacao.dnd5sheet.ui.component.field.AbilityField
import dao.cacao.dnd5sheet.ui.component.field.SkillField
import dao.cacao.dnd5sheet.ui.component.state.LoadingState
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun SheetScreen(
    state: Sheet.State,
    onNavigateUp: (() -> Unit)? = null,
    onPageChange: (Sheet.State.SheetScreenPages) -> Unit = {},
    onLevelChange: (Int) -> Unit = {},
    onCharacterNameClick: () -> Unit = {},
    onCharacterRaceClick: () -> Unit = {},
    onCharacterClassClick: () -> Unit = {},
    onProficiencyBonusChange: (Int) -> Unit = {},
    onAbilityScoreChange: (abilityId: Long, value: Int) -> Unit = { _, _ -> },
    onSkillProficiencyChange: (skillId: Long, proficiency: Boolean) -> Unit = { _, _ -> },
) {
    Screen(
        title = stringResource(R.string.text_character_sheet),
        onNavigateUp = onNavigateUp,
    ) {
        when {
            state.isLoading -> {
                LoadingState()
            }
            else -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                    ) {
                        when (state.page) {
                            Sheet.State.SheetScreenPages.Common -> {
                                CommonBlock(
                                    level = state.level,
                                    characterName = state.name,
                                    characterRace = state.characterRace,
                                    characterClass = state.characterClass,
                                    proficiencyBonus = state.proficiencyBonus,
                                    onLevelChange = onLevelChange,
                                    onCharacterNameClick = onCharacterNameClick,
                                    onCharacterRaceClick = onCharacterRaceClick,
                                    onCharacterClassClick = onCharacterClassClick,
                                    onProficiencyBonusChange = onProficiencyBonusChange,
                                )
                            }
                            Sheet.State.SheetScreenPages.Abilities -> {
                                AbilitiesBlock(
                                    items = state.abilities,
                                    content = { ability ->
                                        AbilityField(
                                            modifier = Modifier.fillMaxWidth(),
                                            score = ability.score,
                                            ability = ability.name,
                                            abilityModifier = ability.modifier,
                                            onScoreChange = { onAbilityScoreChange(ability.id, it) },
                                        )
                                    }
                                )
                            }
                            Sheet.State.SheetScreenPages.Skills -> {
                                SkillsBlock(
                                    items = state.skills,
                                    content = { skill ->
                                        SkillField(
                                            modifier = Modifier.fillMaxWidth(),
                                            skill = skill.name,
                                            ability = skill.abilityName,
                                            proficiency = skill.proficiency,
                                            skillModifier = skill.modifier,
                                            onProficiencyChange = { onSkillProficiencyChange(skill.id, it) }
                                        )
                                    }
                                )
                            }
                        }
                    }
                    NavigationBar {
                        for (page in Sheet.State.SheetScreenPages.values()) {
                            NavigationBarItem(
                                selected = state.page == page,
                                onClick = { onPageChange(page) },
                                icon = {
                                    val icon = when (page) {
                                        Sheet.State.SheetScreenPages.Common -> Icons.Default.Person
                                        Sheet.State.SheetScreenPages.Abilities -> Icons.Default.Abc
                                        Sheet.State.SheetScreenPages.Skills -> Icons.Default.SoupKitchen
                                    }
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                    )
                                },
                                label = {
                                    val text = when (page) {
                                        Sheet.State.SheetScreenPages.Common -> stringResource(R.string.text_common)
                                        Sheet.State.SheetScreenPages.Abilities -> stringResource(R.string.text_abilities)
                                        Sheet.State.SheetScreenPages.Skills -> stringResource(R.string.text_skills)
                                    }
                                    Text(
                                        text = text,
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview(@PreviewParameter(StatePreviewProvider::class) state: Sheet.State) {
    AppTheme {
        SheetScreen(
            state = state,
        )
    }
}

class StatePreviewProvider : PreviewParameterProvider<Sheet.State> {
    override val values = sequenceOf(
        Sheet.State(isLoading = true),
    ) + Sheet.State.SheetScreenPages.values().map {
        Sheet.State(
            page = it,
            name = "name",
            level = 1,
            characterRace = "race",
            characterClass = "class",
            proficiencyBonus = 2,
            abilities = List(3) {
                Sheet.State.AbilityItem(
                    id = 0,
                    name = "Ability #$it",
                    score = 10,
                    modifier = 0,
                )
            },
            skills = List(3) {
                Sheet.State.SkillItem(
                    id = 0,
                    abilityId = 0,
                    name = "Skill #$it",
                    abilityName = "Ability #$it",
                    modifier = 20,
                    proficiency = true,
                )
            },
        )
    }.asSequence()
}