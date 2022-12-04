package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.domain.model.Race
import dao.cacao.dnd5sheet.presentation.component.Toolbar
import dao.cacao.dnd5sheet.presentation.component.state.ScaffoldLoadingState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SelectRaceScreen(
    state: SelectRaceState,
    onNavigateUp: (() -> Unit)? = null,
    onRaceClick: (Race) -> Unit = {},
    onRaceInfoClick: (Race) -> Unit = {},
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
                            onClick = { onRaceClick(race) },
                            onIconClick = { onRaceInfoClick(race) },
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
    onIconClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
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
            IconButton(
                onClick = onIconClick,
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.Info),
                    contentDescription = "Race info",
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}