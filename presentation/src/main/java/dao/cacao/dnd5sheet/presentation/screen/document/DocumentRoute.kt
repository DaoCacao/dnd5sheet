package dao.cacao.dnd5sheet.presentation.screen.document

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.router.Routes

fun NavGraphBuilder.documentScreen(
    navController: NavController,
) = composable(
    route = Routes.documentRoutePlaceholder,
    arguments = listOf(
        navArgument(Routes.argDocumentId) { type = NavType.LongType },
    ),
) {
    val viewModel = hiltViewModel<DocumentViewModel>()
    DocumentScreen(
        state = viewModel.state,
        onNavigateUp = navController::navigateUp,
    )
}

