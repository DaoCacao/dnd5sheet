package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.RouteWithArgs
import kotlinx.coroutines.flow.collectLatest

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
) = composable(
    route = SheetRoute.route,
    arguments = SheetRoute.navArguments,
) {
    val viewModel: SheetViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    LaunchedEffect(viewModel.event) {
        viewModel.event.collectLatest {
            when (it) {
                is SheetEvent.NavigateToSelectRace -> onNavigateToSelectRace(it.sheetId)
                is SheetEvent.NavigateToSelectClass -> onNavigateToSelectClass(it.sheetId)
            }
        }
    }
    SheetScreen(
        state = state,
        onNavigateUp = onNavigateUp,
        onPageChange = viewModel::onPageChange,
        onLevelChange = viewModel::onLevelChange,
        onCharacterNameChange = viewModel::onCharacterNameChange,
        onCharacterRaceClick = viewModel::onCharacterRaceClick,
        onCharacterClassClick = viewModel::onCharacterClassClick,
        onProficiencyBonusChange = viewModel::onProficiencyBonusChange,
        onAbilityScoreChange = viewModel::onAbilityScoreChange,
        onSkillProficiencyChange = viewModel::onSkillProficiencyChange
    )
}