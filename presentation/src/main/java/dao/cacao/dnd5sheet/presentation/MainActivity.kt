package dao.cacao.dnd5sheet.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.CreateSheetRoute
import dao.cacao.dnd5sheet.presentation.screen.create_sheet.createSheetGraph
import dao.cacao.dnd5sheet.presentation.screen.document.documentScreen
import dao.cacao.dnd5sheet.presentation.screen.sheet.SheetRoute
import dao.cacao.dnd5sheet.presentation.screen.sheet.sheetRoute
import dao.cacao.dnd5sheet.presentation.screen.sheet_list.SheetListRoute
import dao.cacao.dnd5sheet.presentation.screen.sheet_list.sheetListRoute
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppTheme {
                MainNavHost(
                    navController = rememberNavController()
                )
            }
        }
    }
}

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = SheetListRoute.route,
    ) {
        sheetListRoute(
            onNavigateToCharacter = { navController.navigate(SheetRoute.route(SheetRoute.Args(it))) },
            onNavigateToCreateCharacter = { navController.navigate(CreateSheetRoute.route()) }
        )
        sheetRoute(
            onNavigateUp = navController::navigateUp,
        )
        documentScreen(
            onNavigateUp = navController::navigateUp,
        )
        createSheetGraph(
            navController = navController,
        )
    }
}