package dao.cacao.dnd5sheet.presentation.screen.sheet_list

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import dao.cacao.dnd5sheet.presentation.base.Route
import dao.cacao.dnd5sheet.presentation.ext.collectAsEvent
import dao.cacao.dnd5sheet.presentation.ext.route

object SheetListRoute : Route(
    path = "sheet_list",
)

fun NavGraphBuilder.sheetListRoute(
    onNavigateToCharacter: (sheetId: Long) -> Unit,
    onNavigateToCreateCharacter: (sheetId: Long) -> Unit,
) = route(
    route = SheetListRoute.route,
) {
    val viewModel: SheetListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    viewModel.event.collectAsEvent {
        when (it) {
            is SheetList.Event.NavigateToCreateSheet -> {
                onNavigateToCreateCharacter(it.sheetId)
            }
            is SheetList.Event.NavigateToSheet -> {
                onNavigateToCharacter(it.sheetId)
            }
        }
    }
    SheetListScreen(
        state = state,
        onCreateNewSheetClick = viewModel::onCreateNewSheetClick,
        onDeleteSheetClick = viewModel::onDeleteSheetClick,
        onSheetClick = viewModel::onSheetClick,
    )
}