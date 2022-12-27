package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.RouteWithArgs
import dao.cacao.dnd5sheet.presentation.ext.collectAsEvent

private const val SHEET_ID = "sheet_id"

object SelectClassRoute : RouteWithArgs<SelectClassRoute.Args>(
    path = "select_class",
    navArguments = listOf(
        navArgument(SHEET_ID) { type = NavType.LongType },
    ),
) {
    data class Args(
        val sheetId: Long,
    )

    override fun args(savedStateHandle: SavedStateHandle): Args {
        return Args(
            sheetId = checkNotNull(savedStateHandle[SHEET_ID])
        )
    }

    override fun argsToMap(args: Args): Map<String, Any> {
        return mapOf(
            SHEET_ID to args.sheetId,
        )
    }
}

fun NavGraphBuilder.selectClassRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateToNext: (classId: Long) -> Unit,
    onNavigateToDocument: (documentId: Long) -> Unit,
) = composable(
    route = SelectClassRoute.route,
    arguments = SelectClassRoute.navArguments,
) {
    val viewModel: SelectClassViewModel = hiltViewModel()
    viewModel.event.collectAsEvent {
        when (it) {
            is SelectClass.Event.NavigateToDocument -> onNavigateToDocument(it.documentId)
            is SelectClass.Event.NavigateToNext -> onNavigateToNext(it.sheetId)
        }
    }
    SelectClassScreen(
        state = viewModel.state.collectAsState().value,
        onNavigateUp = onNavigateUp,
        onClassClick = viewModel::onClassClick,
        onClassInfoClick = viewModel::onClassInfoClick,
    )
}