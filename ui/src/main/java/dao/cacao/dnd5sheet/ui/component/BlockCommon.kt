package dao.cacao.dnd5sheet.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.component.field.CounterField
import dao.cacao.dnd5sheet.ui.component.field.LevelField
import dao.cacao.dnd5sheet.ui.component.field.TextField

@Composable
fun BlockCommon(
    level: Int = 0,
    characterName: String = "",
    characterRace: String = "",
    characterClass: String = "",
    proficiencyBonus: Int = 0,
    onLevelChange: (Int) -> Unit = {},
    onCharacterNameChange: (String) -> Unit = {},
    onCharacterRaceChange: (String) -> Unit = {},
    onCharacterClassChange: (String) -> Unit = {},
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
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = characterRace,
            onValueChange = onCharacterRaceChange,
            label = "Race"
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = characterClass,
            onValueChange = onCharacterClassChange,
            label = "Class"
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