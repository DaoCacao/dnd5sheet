package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dao.cacao.dnd5sheet.presentation.base.Route

object SelectClassRoute : Route(
    path = "select_class",
)

fun NavGraphBuilder.selectClassRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateToNext: () -> Unit,
    onNavigateToDocument: (documentId: Long) -> Unit,
) = composable(
    route = SelectClassRoute.route,
) {
    val viewModel: SelectClassViewModel = hiltViewModel()
    SelectClassScreen(
        state = viewModel.state,
        onNavigateUp = onNavigateUp,
        onClassClick = { onNavigateToNext() },
        onClassInfoClick = { onNavigateToDocument(it.documentId) },
    )
}