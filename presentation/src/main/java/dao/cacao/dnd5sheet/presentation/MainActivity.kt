package dao.cacao.dnd5sheet.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dao.cacao.dnd5sheet.presentation.sceen.SheetListScreen
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
                    startDestination = Routes.ROUTE_SHEET_LIST,
                ) {
                    composable(Routes.ROUTE_SHEET_LIST) {
                        SheetListScreen(
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}