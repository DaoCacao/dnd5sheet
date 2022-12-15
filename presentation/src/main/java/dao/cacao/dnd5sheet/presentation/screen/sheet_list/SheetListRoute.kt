package dao.cacao.dnd5sheet.presentation.screen.sheet_list

import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dao.cacao.dnd5sheet.presentation.base.Route

object SheetListRoute : Route(
    path = "sheet_list",
)

fun NavGraphBuilder.sheetListRoute(
    onNavigateToCharacter: (sheetId: Long) -> Unit,
    onNavigateToCreateCharacter: () -> Unit,
) = composable(
    route = SheetListRoute.route,
) {
    val viewModel: SheetListViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    SheetListScreen(
        state = viewModel.state,
        onCreateNewSheetClick = { onNavigateToCreateCharacter() },
        onDeleteSheetClick = viewModel::onDeleteSheetClick,
        onSheetClick = { onNavigateToCharacter(it.id) },
    )
}