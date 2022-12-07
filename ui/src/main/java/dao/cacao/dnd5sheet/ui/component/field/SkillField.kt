package dao.cacao.dnd5sheet.ui.component.field

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.component.ModifierText
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun SkillField(
    modifier: Modifier = Modifier,
    skill: String,
    ability: String,
    skillModifier: Int?,
    proficiency: Boolean,
    onProficiencyChange: (Boolean) -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
        ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        ) {
            Checkbox(
                checked = proficiency,
                onCheckedChange = onProficiencyChange,
            )
            ModifierText(
                value = skillModifier,
            )
            Text(
                text = skill,
            )
            Text(
                text = "($ability)",
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        SkillField(
            skillModifier = 5,
            skill = "Skill",
            ability = "Ability",
            proficiency = true,
            onProficiencyChange = {},
        )
    }
}