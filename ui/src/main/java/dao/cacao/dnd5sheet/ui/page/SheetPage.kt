package dao.cacao.dnd5sheet.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = characterName,
            onValueChange = onCharacterNameChange,
            label = "Name"
        )
        CounterField(
            modifier = Modifier.fillMaxWidth(),
            value = level,
            onValueChange = onLevelChange,
            label = "Level",
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
    }
}

@Composable
fun <T> BlockAbilities(
    items: List<T>,
    content: @Composable (T) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        for (item in items) {
            content(item)
        }
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
            modifier = Modifier.fillMaxWidth(),
            value = strength,
            onValueChange = onStrengthChange,
            label = "Strength",
        )
        CounterField(
            modifier = Modifier.fillMaxWidth(),
            value = dexterity,
            onValueChange = onDexterityChange,
            label = "Dexterity",
        )
        CounterField(
            modifier = Modifier.fillMaxWidth(),
            value = constitution,
            onValueChange = onConstitutionChange,
            label = "Constitution",
        )
        CounterField(
            modifier = Modifier.fillMaxWidth(),
            value = intelligence,
            onValueChange = onIntelligenceChange,
            label = "Intelligence",
        )
        CounterField(
            modifier = Modifier.fillMaxWidth(),
            value = wisdom,
            onValueChange = onWisdomChange,
            label = "Wisdom",
        )
        CounterField(
            modifier = Modifier.fillMaxWidth(),
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