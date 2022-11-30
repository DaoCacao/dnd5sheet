package dao.cacao.dnd5sheet.presentation.sceen.sheet_list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dao.cacao.dnd5sheet.presentation.base.ViewModelRouter
import dao.cacao.dnd5sheet.presentation.router.Routes

fun NavGraphBuilder.sheetListRoute(
    navController: NavController,
) = composable(
    route = Routes.sheetListRoutePlaceholder,
) {
    ViewModelRouter<SheetListViewModel>(navController) {
        SheetListScreen(
            state = it.state,
            onCreateNewSheetClick = it::onCreateNewSheetClick,
            onDeleteSheetClick = it::onDeleteSheetClick,
            onSheetClick = it::onSheetClick,
        )
    }
}