package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dao.cacao.dnd5sheet.presentation.base.Route

object SelectRaceRoute : Route(
    path = "select_race",
)

fun NavGraphBuilder.selectRaceRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateToNext: () -> Unit,
    onNavigateToDocument: (documentId: Long) -> Unit,
) = composable(
    route = SelectRaceRoute.route,
) {
    val viewModel: SelectRaceViewModel = hiltViewModel()
    SelectRaceScreen(
        state = viewModel.state,
        onNavigateUp = onNavigateUp,
        onRaceClick = { onNavigateToNext() },
        onRaceInfoClick = { onNavigateToDocument(it.documentId) },
    )
}