package dao.cacao.dnd5sheet.presentation.screen.sheet_list

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dao.cacao.dnd5sheet.presentation.base.Route
import kotlinx.coroutines.flow.collectLatest

object SheetListRoute : Route(
    path = "sheet_list",
)

fun NavGraphBuilder.sheetListRoute(
    onNavigateToCharacter: (sheetId: Long) -> Unit,
    onNavigateToCreateCharacter: (sheetId: Long) -> Unit,
) = composable(
    route = SheetListRoute.route,
) {
    val viewModel: SheetListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collectLatest {
            when (it) {
                is SheetListEvent.NavigateToCreateSheet -> {
                    onNavigateToCreateCharacter(it.sheetId)
                }
                is SheetListEvent.NavigateToSheet -> {
                    onNavigateToCharacter(it.sheetId)
                }
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