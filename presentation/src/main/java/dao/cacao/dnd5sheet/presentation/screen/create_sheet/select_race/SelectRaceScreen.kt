package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.domain.model.Race
import dao.cacao.dnd5sheet.presentation.R
import dao.cacao.dnd5sheet.ui.component.TopAppBar
import dao.cacao.dnd5sheet.ui.component.list_item.RaceListItem
import dao.cacao.dnd5sheet.ui.component.state.ScaffoldLoadingState
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SelectRaceScreen(
    state: SelectRace.State,
    onNavigateUp: (() -> Unit)? = null,
    onRaceClick: (Race) -> Unit = {},
    onRaceInfoClick: (Race) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(id = R.string.text_create_character),
                subtitle = stringResource(R.string.text_select_race),
                onNavigateUp = onNavigateUp,
            )
        }
    ) {
        when {
            state.isLoading -> {
                ScaffoldLoadingState(paddingValues = it)
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = it),
                ) {
                    items(state.races) {
                        RaceListItem(
                            name = it.name,
                            onRaceClick = { onRaceClick(it) },
                            onInfoClick = { onRaceInfoClick(it) },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview1() {
    AppTheme {
        SelectRaceScreen(
            state = SelectRace.State(
                isLoading = true,
                races = emptyList(),
            ),
        )
    }
}

@Preview
@Composable
private fun Preview2() {
    AppTheme {
        SelectRaceScreen(
            state = SelectRace.State(
                isLoading = false,
                races = List(5) {
                    Race("0", "Race $it")
                },
            ),
        )
    }
}