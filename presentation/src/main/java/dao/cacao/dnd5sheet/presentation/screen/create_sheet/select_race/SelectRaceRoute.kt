package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.RouteWithArgs
import kotlinx.coroutines.flow.collectLatest

private const val SHEET_ID = "sheet_id"

object SelectRaceRoute : RouteWithArgs<SelectRaceRoute.Args>(
    path = "select_race",
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

fun NavGraphBuilder.selectRaceRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateToNext: (sheetId: Long) -> Unit,
    onNavigateToDocument: (documentId: Long) -> Unit,
) = composable(
    route = SelectRaceRoute.route,
    arguments = SelectRaceRoute.navArguments,
) {
    val viewModel: SelectRaceViewModel = hiltViewModel()
    LaunchedEffect(viewModel.event) {
        viewModel.event.collectLatest {
            when (it) {
                is SelectRaceEvent.NavigateToDocument -> onNavigateToDocument(it.documentId)
                is SelectRaceEvent.NavigateToNext -> onNavigateToNext(it.sheetId)
            }
        }
    }
    SelectRaceScreen(
        state = viewModel.state.collectAsState().value,
        onNavigateUp = onNavigateUp,
        onRaceClick = viewModel::onRaceClick,
        onRaceInfoClick = viewModel::onRaceInfoClick,
    )
}