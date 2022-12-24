package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.RouteWithArgs
import dao.cacao.dnd5sheet.presentation.ext.collectAsEvent

private const val SHEET_ID = "sheet_id"

object SelectNameRoute : RouteWithArgs<SelectNameRoute.Args>(
    path = "select_name",
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

fun NavGraphBuilder.selectNameRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateNext: () -> Unit,
) = composable(
    route = SelectNameRoute.route,
    arguments = SelectNameRoute.navArguments,
) {
    val viewModel: SelectNameViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    viewModel.event.collectAsEvent {
        when (it) {
            SelectName.Event.NavigateToNext -> onNavigateNext()
        }
    }
    SelectNameScreen(
        state = state,
        onNameChange = viewModel::onNameChange,
        onSaveClick = viewModel::onSaveClick,
        onNavigateUp = onNavigateUp,
    )
}