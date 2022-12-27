package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
private const val NAME = "name"

object SelectNameRoute : RouteWithArgs<SelectNameRoute.Args>(
    path = "select_name",
    navArguments = listOf(
        navArgument(SHEET_ID) { type = NavType.LongType },
        navArgument(POP_BACK_STACK) { type = NavType.BoolType },
        navArgument(NAME) { type = NavType.StringType },
    ),
) {
    data class Args(
        val sheetId: Long,
        val popBackStack: Boolean,
        val name: String = "",
    )

    override fun args(savedStateHandle: SavedStateHandle): Args {
        return Args(
            sheetId = checkNotNull(savedStateHandle[SHEET_ID]),
            popBackStack = checkNotNull(savedStateHandle[POP_BACK_STACK]),
            name = checkNotNull(savedStateHandle[NAME]),
        )
    }

    override fun argsToMap(args: Args): Map<String, Any> {
        return mapOf(
            SHEET_ID to args.sheetId,
            POP_BACK_STACK to args.popBackStack,
            NAME to args.name,
        )
    }
}

fun NavGraphBuilder.selectNameRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
) = route(
    route = SelectNameRoute.route,
    arguments = SelectNameRoute.navArguments,
) {
    val viewModel: SelectNameViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    viewModel.event.collectAsEvent {
        when (it) {
            SelectName.Event.NavigateToNext -> onNavigateNext()
            SelectName.Event.NavigateBack -> onNavigateBack()
        }
    }
    SelectNameScreen(
        state = state,
        onNameChange = viewModel::onNameChange,
        onSaveClick = viewModel::onSaveClick,
        onNavigateUp = onNavigateUp,
    )
}