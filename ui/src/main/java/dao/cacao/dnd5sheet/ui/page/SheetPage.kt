package dao.cacao.dnd5sheet.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.component.CounterField
import dao.cacao.dnd5sheet.ui.component.TextField
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun SheetPage(
    common: @Composable LazyItemScope.() -> Unit,
    abilities: @Composable LazyItemScope.() -> Unit,
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
fun <T> LazyItemScope.BlockAbilities(
    items: List<T>,
    content: @Composable (T) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillParentMaxHeight()
            .padding(16.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items) { content(it) }
    }
//    Column(
//        modifier = Modifier
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//    ) {
//        for (item in items) {
//            content(item)
//        }
//    }
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
                    CounterField(
                        value = it,
                        label = "Ability #$it",
                        onValueChange = {},
                    )
                }
            },
        )
    }
}