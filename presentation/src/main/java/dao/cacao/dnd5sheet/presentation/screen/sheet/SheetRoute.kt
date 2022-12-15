package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.RouteWithArgs

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
) = composable(
    route = SheetRoute.route,
    arguments = SheetRoute.navArguments,
) {
    val viewModel: SheetViewModel = hiltViewModel()
    SheetScreen(
        isLoading = viewModel.isLoading,
        level = viewModel.level,
        characterName = viewModel.characterName,
        characterRace = viewModel.characterRace,
        characterClass = viewModel.characterClass,
        proficiencyBonus = viewModel.proficiencyBonus,
        abilities = viewModel.abilities,
        skills = viewModel.skills,
        onNavigateUp = onNavigateUp,
        onLevelChange = viewModel::onLevelChange,
        onCharacterNameChange = viewModel::onCharacterNameChange,
        onCharacterRaceChange = viewModel::onCharacterRaceChange,
        onCharacterClassChange = viewModel::onCharacterClassChange,
        onProficiencyBonusChange = viewModel::onProficiencyBonusChange,
        onAbilityScoreChange = viewModel::onAbilityScoreChange,
        onSkillProficiencyChange = viewModel::onSkillProficiencyChange
    )
}