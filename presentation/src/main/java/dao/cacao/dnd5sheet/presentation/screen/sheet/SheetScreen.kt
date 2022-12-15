package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.ui.component.state.LoadingState
import dao.cacao.dnd5sheet.ui.component.block.AbilitiesBlock
import dao.cacao.dnd5sheet.ui.component.block.CommonBlock
import dao.cacao.dnd5sheet.ui.component.block.SkillsBlock
import dao.cacao.dnd5sheet.ui.component.Screen
import dao.cacao.dnd5sheet.ui.component.field.AbilityField
import dao.cacao.dnd5sheet.ui.component.field.SkillField
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun SheetScreen(
    isLoading: Boolean,
    level: Int,
    characterName: String,
    characterRace: String,
    characterClass: String,
    proficiencyBonus: Int,
    abilities: List<Ability>,
    skills: List<Skill>,
    initialPage: SheetScreenPages = SheetScreenPages.Common,
    onNavigateUp: (() -> Unit)? = null,
    onLevelChange: (Int) -> Unit = {},
    onCharacterNameChange: (String) -> Unit = {},
    onCharacterRaceChange: (String) -> Unit = {},
    onCharacterClassChange: (String) -> Unit = {},
    onProficiencyBonusChange: (Int) -> Unit = {},
    onAbilityScoreChange: (abilityId: Long, value: Int) -> Unit = { _, _ -> },
    onSkillProficiencyChange: (skillId: Long, proficiency: Boolean) -> Unit = { _, _ -> },
) {
    var selectedPage by remember { mutableStateOf(initialPage) }

    Screen(
        title = "Character sheet",
        onNavigateUp = onNavigateUp,
    ) {
        if (isLoading) {
            LoadingState()
        } else {
            Row {
                NavigationRail {
                    for (page in SheetScreenPages.values()) {
                        NavigationRailItem(
                            selected = selectedPage == page,
                            onClick = { selectedPage = page },
                            icon = {
                                val icon = when (page) {
                                    SheetScreenPages.Common -> Icons.Default.Person
                                    SheetScreenPages.Abilities -> Icons.Default.Abc
                                    SheetScreenPages.Skills -> Icons.Default.SoupKitchen
                                }
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                )
                            },
                            label = {
                                val text = when (page) {
                                    SheetScreenPages.Common -> "Common"
                                    SheetScreenPages.Abilities -> "Abilities"
                                    SheetScreenPages.Skills -> "Skills"
                                }
                                Text(
                                    text = text,
                                )
                            },
                        )
                    }
                }
                when (selectedPage) {
                    SheetScreenPages.Common -> {
                        CommonBlock(
                            level = level,
                            characterName = characterName,
                            characterRace = characterRace,
                            characterClass = characterClass,
                            proficiencyBonus = proficiencyBonus,
                            onLevelChange = onLevelChange,
                            onCharacterNameChange = onCharacterNameChange,
                            onCharacterRaceChange = onCharacterRaceChange,
                            onCharacterClassChange = onCharacterClassChange,
                            onProficiencyBonusChange = onProficiencyBonusChange,
                        )
                    }
                    SheetScreenPages.Abilities -> {
                        AbilitiesBlock(
                            items = abilities,
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
                    SheetScreenPages.Skills -> {
                        SkillsBlock(
                            items = skills,
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
                    }
                }
            }
        }
    }
}

enum class SheetScreenPages {
    Common,
    Abilities,
    Skills,
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        SheetScreen(
            isLoading = false,
            level = 1,
            characterName = "name",
            characterRace = "race",
            characterClass = "class",
            proficiencyBonus = 2,
            abilities = List(6) {
                Ability(
                    id = it.toLong(),
                    name = "Ability #$it",
                    score = 10,
                    modifier = 0,
                )
            },
            skills = List(6) {
                Skill(
                    id = it.toLong(),
                    abilityId = it.toLong(),
                    name = "Skill #$it",
                    ability = "Ability #$it",
                    modifier = 20,
                    proficiency = true,
                )
            }
        )
    }
}