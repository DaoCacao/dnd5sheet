package dao.cacao.dnd5sheet.presentation.sceen.sheet

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.router.Routes

fun NavGraphBuilder.sheetRoute(
    navController: NavController,
) = composable(
    route = Routes.sheetRoutePlaceholder,
    arguments = listOf(
        navArgument(Routes.argSheetId) { type = NavType.LongType },
    ),
) {
    val viewModel = hiltViewModel<SheetViewModel>()
    SheetScreen(
        state = viewModel.state,
        onNavigateUp = navController::navigateUp
    )
}