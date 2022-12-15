package dao.cacao.dnd5sheet.presentation.screen.create_sheet

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dao.cacao.dnd5sheet.presentation.base.Route
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class.SelectClassRoute
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class.selectClassRoute
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race.SelectRaceRoute
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race.selectRaceRoute
import dao.cacao.dnd5sheet.presentation.screen.document.DocumentRoute
import dao.cacao.dnd5sheet.presentation.screen.sheet_list.SheetListRoute

object CreateSheetRoute : Route(
    path = "create_sheet",
)

fun NavGraphBuilder.createSheetGraph(
    navController: NavController,
) = navigation(
    route = CreateSheetRoute.route,
    startDestination = SelectRaceRoute.route,
) {
    selectRaceRoute(
        onNavigateUp = navController::navigateUp,
        onNavigateToNext = { navController.navigate(SelectClassRoute.route()) },
        onNavigateToDocument = { navController.navigate(DocumentRoute.route(DocumentRoute.Args(it))) },
    )
    selectClassRoute(
        onNavigateUp = navController::navigateUp,
        onNavigateToNext = { navController.popBackStack(SheetListRoute.route, false) },
        onNavigateToDocument = { navController.navigate(DocumentRoute.route(DocumentRoute.Args(it))) },
    )
}