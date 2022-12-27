package dao.cacao.dnd5sheet.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
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
@OptIn(ExperimentalAnimationApi::class)
fun MainScreen(
    navController: NavHostController = rememberAnimatedNavController(),
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = SheetListRoute.route,
    ) {
        sheetListRoute(
            onNavigateToCharacter = {
                navController.navigate(SheetRoute.route(SheetRoute.Args(it)))
            },
            onNavigateToCreateCharacter = { sheetId ->
                navController.navigate(SelectRaceRoute.route(SelectRaceRoute.Args(sheetId, false)))
            }
        )
        sheetRoute(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToSelectRace = { sheetId ->
                navController.navigate(SelectRaceRoute.route(SelectRaceRoute.Args(sheetId, true)))
            },
            onNavigateToSelectClass = { sheetId ->
                navController.navigate(SelectClassRoute.route(SelectClassRoute.Args(sheetId, true)))
            },
            onNavigateToSelectName = { sheetId, name ->
                navController.navigate(
                    route = SelectNameRoute.route(
                        args = SelectNameRoute.Args(
                            sheetId = sheetId,
                            popBackStack = true,
                            name = name,
                        ),
                    ),
                )
            }
        )
        documentScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
        )
        selectRaceRoute(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateBack = {
                navController.popBackStack()
            },
            onNavigateToNext = { sheetId ->
                navController.navigate(SelectClassRoute.route(SelectClassRoute.Args(sheetId, false)))
            },
            onNavigateToDocument = {
                navController.navigate(DocumentRoute.route(DocumentRoute.Args(it)))
            },
        )
        selectClassRoute(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateBack = {
                navController.popBackStack()
            },
            onNavigateToNext = { sheetId ->
                navController.navigate(SelectNameRoute.route(SelectNameRoute.Args(sheetId, false)))
            },
            onNavigateToDocument = {
                navController.navigate(DocumentRoute.route(DocumentRoute.Args(it)))
            },
        )
        selectNameRoute(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateBack = {
                navController.popBackStack()
            },
            onNavigateNext = {
                navController.popBackStack(SheetListRoute.route(), false)
            },
        )
    }
}