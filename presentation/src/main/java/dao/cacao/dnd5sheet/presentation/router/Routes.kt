package dao.cacao.dnd5sheet.presentation.router

object Routes {
    //Paths
    private const val sheetPath = "/sheet"
    private const val sheetListPath = "/sheet_list"
    private const val selectRacePath = "/select_race"

    //Args
    const val argSheetId = "sheet_id"
    const val argRaceId = "race_id"

    //Route placeholders
    val sheetRoutePlaceholder = buildRoutePlaceholder(sheetPath, listOf(argSheetId))
    val sheetListRoutePlaceholder = buildRoutePlaceholder(sheetListPath)
    val selectRacePathPlaceholder = buildRoutePlaceholder(selectRacePath)

    //Routes
    fun sheetRoute(sheetId: Long) = buildRoute(sheetPath, mapOf(argSheetId to sheetId))
    fun sheetListRoute() = buildRoute(sheetListPath)
    fun selectRacePath() = buildRoute(selectRacePath)

    private fun buildRoutePlaceholder(path: String, args: List<String> = emptyList()) = buildString {
        append(path)
        if (args.isNotEmpty()) {
            append("?")
            append(args.map { "$it={$it}" }.joinToString("&"))
        }
    }

    private fun buildRoute(path: String, args: Map<String, Any> = emptyMap()) = buildString {
        append(path)
        if (args.isNotEmpty()) {
            append("?")
            append(args.map { (key, value) -> "$key=$value" }.joinToString("&"))
        }
    }
}