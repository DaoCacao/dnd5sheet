package dao.cacao.dnd5sheet.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class.SelectClassRoute
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class.selectClassRoute
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name.SelectNameRoute
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name.selectNameRoute
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race.SelectRaceRoute
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race.selectRaceRoute
import dao.cacao.dnd5sheet.presentation.screen.document.DocumentRoute
import dao.cacao.dnd5sheet.presentation.screen.document.documentScreen
import dao.cacao.dnd5sheet.presentation.screen.sheet.SheetRoute
import dao.cacao.dnd5sheet.presentation.screen.sheet.sheetRoute
import dao.cacao.dnd5sheet.presentation.screen.sheet_list.SheetListRoute
import dao.cacao.dnd5sheet.presentation.screen.sheet_list.sheetListRoute


@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = SheetListRoute.route,
    ) {
        sheetListRoute(
            onNavigateToCharacter = { navController.navigate(SheetRoute.route(SheetRoute.Args(it))) },
            onNavigateToCreateCharacter = { sheetId -> navController.navigate(SelectRaceRoute.route(SelectRaceRoute.Args(sheetId))) }
        )
        sheetRoute(
            onNavigateUp = navController::navigateUp,
            onNavigateToSelectRace = { sheetId -> navController.navigate(SelectRaceRoute.route(SelectRaceRoute.Args(sheetId))) },
            onNavigateToSelectClass = { sheetId -> navController.navigate(SelectClassRoute.route(SelectClassRoute.Args(sheetId))) },
        )
        documentScreen(
            onNavigateUp = navController::navigateUp,
        )
        selectRaceRoute(
            onNavigateUp = navController::navigateUp,
            onNavigateToNext = { sheetId -> navController.navigate(SelectClassRoute.route(SelectClassRoute.Args(sheetId))) },
            onNavigateToDocument = { navController.navigate(DocumentRoute.route(DocumentRoute.Args(it))) },
        )
        selectClassRoute(
            onNavigateUp = navController::navigateUp,
            onNavigateToNext = { sheetId -> navController.navigate(SelectNameRoute.route(SelectNameRoute.Args(sheetId))) },
            onNavigateToDocument = { navController.navigate(DocumentRoute.route(DocumentRoute.Args(it))) },
        )
        selectNameRoute(
            onNavigateUp = navController::navigateUp,
            onNavigateNext = { navController.popBackStack(SheetListRoute.route(), false) },
        )
    }
}