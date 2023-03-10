package dao.cacao.dnd5sheet.presentation.screen.document

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.RouteWithArgs
import dao.cacao.dnd5sheet.presentation.ext.route

private const val documentId = "document_id"

object DocumentRoute : RouteWithArgs<DocumentRoute.Args>(
    path = "document",
    navArguments = listOf(
        navArgument(documentId) { type = NavType.LongType }
    )
) {
    data class Args(
        val documentId: Long,
    )

    override fun args(savedStateHandle: SavedStateHandle) = Args(
        documentId = checkNotNull(savedStateHandle[documentId])
    )

    override fun argsToMap(args: Args) = mapOf(
        documentId to args.documentId
    )
}

fun NavGraphBuilder.documentScreen(
    onNavigateUp: (() -> Unit)?,
) = route(
    route = DocumentRoute.route,
    arguments = DocumentRoute.navArguments,
) {
    val viewModel: DocumentViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    DocumentScreen(
        state = state,
        onNavigateUp = onNavigateUp,
    )
}

