package dao.cacao.dnd5sheet.presentation.screen.sheet

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

private const val sheetId = "sheet_id"

object SheetRoute : RouteWithArgs<SheetRoute.Args>(
    path = "sheet",
    navArguments = listOf(
        navArgument(sheetId) { type = NavType.LongType },
    ),
) {
    class Args(
        val sheetId: Long,
    )

    override fun args(savedStateHandle: SavedStateHandle) = Args(
        sheetId = checkNotNull(savedStateHandle[sheetId]),
    )

    override fun argsToMap(args: Args) = mapOf(
        sheetId to args.sheetId,
    )
}

fun NavGraphBuilder.sheetRoute(
    onNavigateUp: (() -> Unit)?,
    onNavigateToSelectRace: (sheetId: Long) -> Unit,
    onNavigateToSelectClass: (sheetId: Long) -> Unit,
    onNavigateToSelectName: (sheetId: Long, name: String) -> Unit,
) = route(
    route = SheetRoute.route,
    arguments = SheetRoute.navArguments,
) {
    val viewModel: SheetViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    viewModel.event.collectAsEvent {
        when (it) {
            is Sheet.Event.NavigateToSelectRace -> onNavigateToSelectRace(it.sheetId)
            is Sheet.Event.NavigateToSelectClass -> onNavigateToSelectClass(it.sheetId)
            is Sheet.Event.NavigateToSelectName -> onNavigateToSelectName(it.sheetId, it.name)
        }
    }
    SheetScreen(
        state = state,
        onNavigateUp = onNavigateUp,
        onPageChange = viewModel::onPageChange,
        onLevelChange = viewModel::onLevelChange,
        onCharacterNameClick = viewModel::onCharacterNameClick,
        onCharacterRaceClick = viewModel::onCharacterRaceClick,
        onCharacterClassClick = viewModel::onCharacterClassClick,
        onProficiencyBonusChange = viewModel::onProficiencyBonusChange,
        onAbilityScoreChange = viewModel::onAbilityScoreChange,
        onSkillProficiencyChange = viewModel::onSkillProficiencyChange
    )
}