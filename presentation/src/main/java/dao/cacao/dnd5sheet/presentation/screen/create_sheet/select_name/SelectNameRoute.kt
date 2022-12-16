package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dao.cacao.dnd5sheet.presentation.base.Route

object SelectNameRoute : Route(
    path = "select_name",
)

fun NavGraphBuilder.selectNameRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateNext: () -> Unit,
) = composable(
    route = SelectNameRoute.route,
) {
    val viewModel: SelectNameViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    SelectNameScreen(
        state = state,
        onNameChange = viewModel::onNameChange,
        onSaveClick = onNavigateNext,
        onNavigateUp = onNavigateUp,
    )
}