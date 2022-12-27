package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.RouteWithArgs
import dao.cacao.dnd5sheet.presentation.ext.collectAsEvent
import dao.cacao.dnd5sheet.presentation.ext.route

private const val SHEET_ID = "sheet_id"
private const val POP_BACK_STACK = "pop_back_stack"

object SelectClassRoute : RouteWithArgs<SelectClassRoute.Args>(
    path = "select_class",
    navArguments = listOf(
        navArgument(SHEET_ID) { type = NavType.LongType },
        navArgument(POP_BACK_STACK) { type = NavType.BoolType },
    ),
) {
    data class Args(
        val sheetId: Long,
        val popBackStack: Boolean,
    )

    override fun args(savedStateHandle: SavedStateHandle): Args {
        return Args(
            sheetId = checkNotNull(savedStateHandle[SHEET_ID]),
            popBackStack = checkNotNull(savedStateHandle[POP_BACK_STACK]),
        )
    }

    override fun argsToMap(args: Args): Map<String, Any> {
        return mapOf(
            SHEET_ID to args.sheetId,
            POP_BACK_STACK to args.popBackStack,
        )
    }
}

fun NavGraphBuilder.selectClassRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateBack: () -> Unit,
    onNavigateToNext: (classId: Long) -> Unit,
    onNavigateToDocument: (documentId: Long) -> Unit,
) = route(
    route = SelectClassRoute.route,
    arguments = SelectClassRoute.navArguments,
) {
    val viewModel: SelectClassViewModel = hiltViewModel()
    viewModel.event.collectAsEvent {
        when (it) {
            is SelectClass.Event.NavigateToDocument -> onNavigateToDocument(it.documentId)
            is SelectClass.Event.NavigateToNext -> onNavigateToNext(it.sheetId)
            SelectClass.Event.NavigateBack -> onNavigateBack()
        }
    }
    SelectClassScreen(
        state = viewModel.state.collectAsState().value,
        onNavigateUp = onNavigateUp,
        onClassClick = viewModel::onClassClick,
        onClassInfoClick = viewModel::onClassInfoClick,
    )
}