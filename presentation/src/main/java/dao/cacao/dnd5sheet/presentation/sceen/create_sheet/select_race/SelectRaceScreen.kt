package dao.cacao.dnd5sheet.presentation.sceen.create_sheet.select_race

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dao.cacao.dnd5sheet.domain.model.race.Race
import dao.cacao.dnd5sheet.presentation.component.Toolbar
import dao.cacao.dnd5sheet.presentation.component.state.ScaffoldLoadingState

@Composable
fun SelectRaceScreen(
    viewModel: SelectRaceViewModel,
    navController: NavController,
) {
    Content(
        state = viewModel.state,
        onNavigateUp = navController::navigateUp,
        onRaceClick = { navController.navigateUp() },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Content(
    state: SelectRaceState,
    onNavigateUp: (() -> Unit)? = null,
    onRaceClick: (Race) -> Unit = {},
) {
    Scaffold(
        topBar = {
            Toolbar(
                title = "Choose race",
                onNavigateUp = onNavigateUp,
            )
        }
    ) {
        when (state) {
            SelectRaceState.Loading -> {
                ScaffoldLoadingState(paddingValues = it)
            }
            is SelectRaceState.Content -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = it),
                ) {
                    itemsIndexed(state.races) { index, race ->
                        RaceListItem(
                            title = race.name,
                            onClick = { onRaceClick(race) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun RaceListItem(
    title: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            if (title.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}