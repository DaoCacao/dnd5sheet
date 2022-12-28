package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_subrace

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
private const val RACE_ID = "race_id"
private const val POP_BACK_STACK = "pop_back_stack"

object SelectSubraceRoute : RouteWithArgs<SelectSubraceRoute.Args>(
    path = "select_subrace",
    navArguments = listOf(
        navArgument(SHEET_ID) { type = NavType.LongType },
        navArgument(RACE_ID) { type = NavType.StringType },
        navArgument(POP_BACK_STACK) { type = NavType.BoolType },
    ),
) {

    data class Args(
        val sheetId: Long,
        val raceId: String,
        val popBackStack: Boolean,
    )

    override fun args(savedStateHandle: SavedStateHandle): Args {
        return Args(
            sheetId = checkNotNull(savedStateHandle[SHEET_ID]),
            raceId = checkNotNull(savedStateHandle[RACE_ID]),
            popBackStack = checkNotNull(savedStateHandle[POP_BACK_STACK]),
        )
    }

    override fun argsToMap(args: Args): Map<String, Any> {
        return mapOf(
            SHEET_ID to args.sheetId,
            RACE_ID to args.raceId,
            POP_BACK_STACK to args.popBackStack,
        )
    }
}

fun NavGraphBuilder.selectSubraceRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateBack: () -> Unit,
    onNavigateToSelectClass: (sheetId: Long) -> Unit,
    onNavigateToDocument: (documentId: Long) -> Unit,
) = route(
    route = SelectSubraceRoute.route,
    arguments = SelectSubraceRoute.navArguments,
) {
    val viewModel: SelectSubraceViewModel = hiltViewModel()
    viewModel.event.collectAsEvent {
        when (it) {
            is SelectSubrace.Event.NavigateToDocument -> onNavigateToDocument(it.documentId)
            is SelectSubrace.Event.NavigateToNext -> onNavigateToSelectClass(it.sheetId)
            SelectSubrace.Event.NavigateBack -> onNavigateBack()
        }
    }
    SelectSubraceScreen(
        state = viewModel.state.collectAsState().value,
        onNavigateUp = onNavigateUp,
        onRaceClick = viewModel::onSubraceClick,
        onRaceInfoClick = viewModel::onSubraceInfoClick,
    )
}