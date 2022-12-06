package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.ViewModelRouter
import dao.cacao.dnd5sheet.presentation.router.Routes

fun NavGraphBuilder.sheetRoute(
    navController: NavController,
) = composable(
    route = Routes.sheetRoutePlaceholder,
    arguments = listOf(
        navArgument(Routes.argSheetId) { type = NavType.LongType },
    ),
) {
    ViewModelRouter<SheetViewModel>(navController) {
        SheetScreen(
            state = it.state,
            onNavigateUp = it::onNavigateUpClick,
            onLevelChange = it::onLevelChange,
            onCharacterNameChange = it::onCharacterNameChange,
            onCharacterRaceChange = it::onCharacterRaceChange,
            onCharacterClassChange = it::onCharacterClassChange,
            onAbilityChange = it::onAbilityChange,
        )
    }
}