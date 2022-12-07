package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.presentation.component.state.LoadingState
import dao.cacao.dnd5sheet.ui.component.BlockAbilities
import dao.cacao.dnd5sheet.ui.component.BlockCommon
import dao.cacao.dnd5sheet.ui.component.BlockSkills
import dao.cacao.dnd5sheet.ui.component.Screen
import dao.cacao.dnd5sheet.ui.component.field.AbilityField
import dao.cacao.dnd5sheet.ui.component.field.SkillField
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
    onProficiencyBonusChange: (Int) -> Unit = {},
    onAbilityScoreChange: (abilityId: Long, value: Int) -> Unit = { _, _ -> },
    onSkillProficiencyChange: (skillId: Long, proficiency: Boolean) -> Unit = { _, _ -> },
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
                            proficiencyBonus = state.proficiencyBonus,
                            onLevelChange = onLevelChange,
                            onCharacterNameChange = onCharacterNameChange,
                            onCharacterRaceChange = onCharacterRaceChange,
                            onCharacterClassChange = onCharacterClassChange,
                            onProficiencyBonusChange = onProficiencyBonusChange,
                        )
                    },
                    abilities = {
                        BlockAbilities(
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
                    },
                    skills = {
                        BlockSkills(
                            items = state.skills,
                            content = { skill ->
                                SkillField(
                                    modifier = Modifier.fillMaxWidth(),
                                    skill = skill.name,
                                    ability = skill.ability,
                                    proficiency = skill.proficiency,
                                    skillModifier = skill.modifier,
                                    onProficiencyChange = { onSkillProficiencyChange(skill.id, it) }
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
                proficiencyBonus = 2,
                abilities = List(6) {
                    SheetState.Content.Ability(
                        id = it.toLong(),
                        name = "Ability #$it",
                        score = 10,
                        modifier = 0,
                    )
                },
                skills = List(6) {
                    SheetState.Content.Skill(
                        id = it.toLong(),
                        abilityId = it.toLong(),
                        name = "Skill #$it",
                        ability = "Ability #$it",
                        modifier = 20,
                        proficiency = true,
                    )
                }
            ),
        )
    }
}