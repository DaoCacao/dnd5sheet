package dao.cacao.dnd5sheet.ui.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.ui.component.BlockAbilities
import dao.cacao.dnd5sheet.ui.component.BlockCommon
import dao.cacao.dnd5sheet.ui.component.BlockSkills
import dao.cacao.dnd5sheet.ui.component.field.AbilityField
import dao.cacao.dnd5sheet.ui.component.field.SkillField
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun SheetPage(
    common: @Composable LazyItemScope.() -> Unit,
    abilities: @Composable LazyItemScope.() -> Unit,
    skills: @Composable LazyItemScope.() -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item { common() }
        item { abilities() }
        item { skills() }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        SheetPage(
            common = {
                BlockCommon()
            },
            abilities = {
                BlockAbilities(
                    items = List(6) { it },
                ) {
                    AbilityField(
                        modifier = Modifier.fillMaxWidth(),
                        score = it,
                        ability = "Ability #$it",
                        abilityModifier = it,
                        onScoreChange = {},
                    )
                }
            },
            skills = {
                BlockSkills(
                    items = List(6) { it },
                ) {
                    SkillField(
                        modifier = Modifier.fillMaxWidth(),
                        skill = "Skill #$it",
                        ability = "Ab.",
                        proficiency = true,
                        skillModifier = it,
                        onProficiencyChange = {},
                    )
                }
            }
        )
    }
}