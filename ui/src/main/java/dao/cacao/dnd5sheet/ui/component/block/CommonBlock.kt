package dao.cacao.dnd5sheet.ui.component.block

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.component.field.ClassField
import dao.cacao.dnd5sheet.ui.component.field.CounterField
import dao.cacao.dnd5sheet.ui.component.field.LevelField
import dao.cacao.dnd5sheet.ui.component.field.RaceField
import dao.cacao.dnd5sheet.ui.component.field.TextField

@Composable
fun CommonBlock(
    level: Int = 0,
    characterName: String = "",
    characterRace: String = "",
    characterClass: String = "",
    proficiencyBonus: Int = 0,
    onLevelChange: (Int) -> Unit = {},
    onCharacterNameChange: (String) -> Unit = {},
    onCharacterRaceClick: () -> Unit = {},
    onCharacterClassClick: () -> Unit = {},
    onProficiencyBonusChange: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = characterName,
            onValueChange = onCharacterNameChange,
            label = "Name"
        )
        RaceField(
            modifier = Modifier.fillMaxWidth(),
            value = characterRace,
            onClick = onCharacterRaceClick,
        )
        ClassField(
            modifier = Modifier.fillMaxWidth(),
            value = characterClass,
            onClick = onCharacterClassClick,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LevelField(
                level = level,
                onLevelChange = onLevelChange,
            )
            Spacer(
                modifier = Modifier.weight(2f),
            )
            CounterField(
                value = proficiencyBonus,
                onValueChange = onProficiencyBonusChange,
                label = "Proficiency bonus",
            )
        }
    }
}