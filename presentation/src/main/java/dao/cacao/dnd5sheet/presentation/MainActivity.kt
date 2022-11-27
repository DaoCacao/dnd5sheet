package dao.cacao.dnd5sheet.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dao.cacao.dnd5sheet.presentation.router.Routes
import dao.cacao.dnd5sheet.presentation.sceen.sheet.SheetScreen
import dao.cacao.dnd5sheet.presentation.sceen.sheet_list.SheetListScreen
import dao.cacao.dnd5sheet.presentation.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.SheetList.ROUTE,
                ) {
                    composable(
                        route = Routes.SheetList.ROUTE,
                    ) {
                        SheetListScreen(
                            viewModel = hiltViewModel(),
                            navController = navController,
                        )
                    }
                    composable(
                        route = Routes.Sheet.ROUTE,
                        arguments = listOf(
                            navArgument(Routes.Sheet.ARG_SHEET_ID) { type = NavType.LongType },
                        ),
                    ) {
                        SheetScreen(
                            viewModel = hiltViewModel(),
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}