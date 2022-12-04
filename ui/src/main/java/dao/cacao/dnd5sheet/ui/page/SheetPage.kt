package dao.cacao.dnd5sheet.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.component.CounterField
import dao.cacao.dnd5sheet.ui.component.TextField
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun SheetPage(
    common: @Composable () -> Unit,
    abilities: @Composable () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item { common() }
        item { abilities() }
    }
}

@Composable
fun BlockCommon(
    level: Int = 0,
    characterName: String = "",
    characterRace: String = "",
    characterClass: String = "",
    onLevelChange: (Int) -> Unit = {},
    onCharacterNameChange: (String) -> Unit = {},
    onCharacterRaceChange: (String) -> Unit = {},
    onCharacterClassChange: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CounterField(
            value = level,
            onValueChange = onLevelChange,
            label = "Level",
        )
        TextField(
            value = characterName,
            onValueChange = onCharacterNameChange,
            label = "Name"
        )
        TextField(
            value = characterRace,
            onValueChange = onCharacterRaceChange,
            label = "Race"
        )
        TextField(
            value = characterClass,
            onValueChange = onCharacterClassChange,
            label = "Class"
        )
    }
}

@Composable
fun BlockAbilities(
    strength: Int = 0,
    dexterity: Int = 0,
    constitution: Int = 0,
    intelligence: Int = 0,
    wisdom: Int = 0,
    charisma: Int = 0,
    onStrengthChange: (Int) -> Unit = {},
    onDexterityChange: (Int) -> Unit = {},
    onConstitutionChange: (Int) -> Unit = {},
    onIntelligenceChange: (Int) -> Unit = {},
    onWisdomChange: (Int) -> Unit = {},
    onCharismaChange: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CounterField(
            value = strength,
            onValueChange = onStrengthChange,
            label = "Strength",
        )
        CounterField(
            value = dexterity,
            onValueChange = onDexterityChange,
            label = "Dexterity",
        )
        CounterField(
            value = constitution,
            onValueChange = onConstitutionChange,
            label = "Constitution",
        )
        CounterField(
            value = intelligence,
            onValueChange = onIntelligenceChange,
            label = "Intelligence",
        )
        CounterField(
            value = wisdom,
            onValueChange = onWisdomChange,
            label = "Wisdom",
        )
        CounterField(
            value = charisma,
            onValueChange = onCharismaChange,
            label = "Charisma",
        )
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
                BlockAbilities()
            },
        )
    }
}