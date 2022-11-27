package dao.cacao.dnd5sheet.presentation.sceen.sheet_list

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dao.cacao.dnd5sheet.presentation.router.Routes

fun NavGraphBuilder.sheetListRoute(
    navController: NavController,
) = composable(Routes.sheetListRoutePlaceholder) {
    val viewModel = hiltViewModel<SheetListViewModel>()
    SheetListScreen(
        state = viewModel.state,
        onCreateNewSheetClick = { navController.navigate(Routes.selectRaceRoute()) },
        onDeleteSheetClick = viewModel::onDeleteSheetClick,
        onSheetClick = { navController.navigate(Routes.sheetRoute(it.id)) }
    )
}