package dao.cacao.dnd5sheet.presentation.base

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument

abstract class Route(
    private val path: String,
) {
    val route = buildRoute(path, emptyList())
    fun route() = buildRoute(path, emptyMap())
}

abstract class RouteWithArgs<A>(
    private val path: String,
    val navArguments: List<NamedNavArgument>,
) {
    val route = buildRoute(path, navArguments.map { it.name })
    fun route(args: A) = buildRoute(path, argsToMap(args))

    abstract fun args(savedStateHandle: SavedStateHandle): A
    abstract fun argsToMap(args: A): Map<String, Any>
}

private fun buildRoute(path: String, args: List<String> = emptyList()) = buildString {
    append(path)
    if (args.isNotEmpty()) {
        append("?")
        append(args.joinToString("&") { it.asArgumentPlaceholder })
    }
}

private fun buildRoute(path: String, args: Map<String, Any> = emptyMap()) = buildString {
    append(path)
    if (args.isNotEmpty()) {
        append("?")
        append(args.toList().joinToString("&") { it.asArgumentQuery })
    }
}

private val String.asArgumentPlaceholder get() = "$this={$this}"
private val Pair<String, Any>.asArgumentQuery get() = "${first}=${second}"