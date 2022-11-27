package dao.cacao.dnd5sheet.presentation.sceen.create_sheet.select_race

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dao.cacao.dnd5sheet.presentation.router.Routes

fun NavGraphBuilder.selectRaceRoute(
    navController: NavController,
) = composable(
    route = Routes.selectRaceRoutePlaceholder,
) {
    val viewModel = hiltViewModel<SelectRaceViewModel>()
    SelectRaceScreen(
        state = viewModel.state,
        onNavigateUp = navController::navigateUp,
        onRaceClick = { navController.navigate(Routes.documentPath(it.documentId)) },
    )
}