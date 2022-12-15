package dao.cacao.dnd5sheet.ui.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.ui.component.block.AbilitiesBlock
import dao.cacao.dnd5sheet.ui.component.block.CommonBlock
import dao.cacao.dnd5sheet.ui.component.block.SkillsBlock
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
                CommonBlock()
            },
            abilities = {
                AbilitiesBlock(
                    items = List(6) { it },
                ) {
                    AbilityField(
                        score = it,
                        ability = "Ability #$it",
                        abilityModifier = it,
                        onScoreChange = {},
                    )
                }
            },
            skills = {
                SkillsBlock(
                    items = List(6) { it },
                ) {
                    SkillField(
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