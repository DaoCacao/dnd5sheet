package dao.cacao.dnd5sheet.presentation.screen.sheet

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.ViewModelRouter
import dao.cacao.dnd5sheet.presentation.router.Routes

fun NavGraphBuilder.sheetRoute(
    navController: NavController,
) = composable(
    route = Routes.sheetRoutePlaceholder,
    arguments = listOf(
        navArgument(Routes.argSheetId) { type = NavType.LongType },
    ),
) {
    ViewModelRouter<SheetViewModel>(navController) {
        SheetScreen(
            isLoading = it.isLoading,
            level = it.level,
            characterName = it.characterName,
            characterRace = it.characterRace,
            characterClass = it.characterClass,
            proficiencyBonus = it.proficiencyBonus,
            abilities = it.abilities,
            skills = it.skills,
            onNavigateUp = it::onNavigateUpClick,
            onLevelChange = it::onLevelChange,
            onCharacterNameChange = it::onCharacterNameChange,
            onCharacterRaceChange = it::onCharacterRaceChange,
            onCharacterClassChange = it::onCharacterClassChange,
            onProficiencyBonusChange = it::onProficiencyBonusChange,
            onAbilityScoreChange = it::onAbilityScoreChange,
            onSkillProficiencyChange = it::onSkillProficiencyChange
        )
    }
}