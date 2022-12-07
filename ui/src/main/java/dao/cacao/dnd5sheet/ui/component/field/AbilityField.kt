package dao.cacao.dnd5sheet.ui.component.field

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.component.ModifierText
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun AbilityField(
    modifier: Modifier = Modifier,
    score: Int,
    ability: String,
    abilityModifier: Int?,
    onScoreChange: (Int) -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(
                    onClick = { onScoreChange(score - 1) },
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowLeft),
                        contentDescription = "Decrement",
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    ModifierText(
                        value = abilityModifier,
                    )
                    Text(
                        text = "($score)",
                    )
                }
                IconButton(
                    onClick = { onScoreChange(score + 1) },
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowRight),
                        contentDescription = "Increment",
                    )
                }
            }
            Text(
                text = ability,
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        AbilityField(
            score = 20,
            ability = "Ability",
            abilityModifier = 5,
            onScoreChange = {},
        )
    }
}