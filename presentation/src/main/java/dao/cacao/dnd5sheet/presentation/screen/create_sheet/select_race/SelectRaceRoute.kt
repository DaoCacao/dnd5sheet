package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.ViewModelRouter
import dao.cacao.dnd5sheet.presentation.router.Routes

fun NavGraphBuilder.selectRaceRoute(
    navController: NavController,
) = composable(
    route = Routes.selectRaceRoutePlaceholder,
    arguments = listOf(
        navArgument(Routes.argSheetId) { type = NavType.LongType },
    ),
) {
    ViewModelRouter<SelectRaceViewModel>(navController) {
        SelectRaceScreen(
            state = it.state,
            onNavigateUp = it::onNavigateUpClick,
            onRaceClick = it::onRaceClick,
            onRaceInfoClick = it::onRaceInfoClick,
        )
    }
}