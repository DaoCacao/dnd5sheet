package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_abilities

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dao.cacao.dnd5sheet.presentation.base.RouteWithArgs
import dao.cacao.dnd5sheet.presentation.ext.route

private const val SHEET_ID = "sheet_id"
private const val NEXT_ROUTE = "next_route"

object SelectAbilitiesRoute : RouteWithArgs<SelectAbilitiesRoute.Args>(
    path = "select_abilities",
    navArguments = listOf(
        navArgument(SHEET_ID) { type = NavType.LongType },
        navArgument(NEXT_ROUTE) { type = NavType.EnumType(Args.NextRoute::class.java) },
    ),
) {
    data class Args(
        val sheetId: Long,
        val nextRoute: NextRoute,
    ) {
        enum class NextRoute {
            Back,
        }
    }

    override fun args(savedStateHandle: SavedStateHandle): Args {
        return Args(
            sheetId = checkNotNull(savedStateHandle[SHEET_ID]),
            nextRoute = checkNotNull(savedStateHandle[NEXT_ROUTE]),
        )
    }

    override fun argsToMap(args: Args): Map<String, Any> {
        return mapOf(
            SHEET_ID to args.sheetId,
            NEXT_ROUTE to args.nextRoute,
        )
    }
}

fun NavGraphBuilder.selectAbilities(
    onNavigateUp: (() -> Unit)?,
    onNavigateNext: () -> Unit,
) = route(
    route = SelectAbilitiesRoute.route,
    arguments = SelectAbilitiesRoute.navArguments,
) {

}