package dao.cacao.dnd5sheet.presentation.router

object Routes {
    //Paths
    private const val sheetPath = "/sheet"
    private const val sheetListPath = "/sheet_list"
    private const val selectRacePath = "/select_race"
    private const val documentPath = "/document"

    //Args
    const val argSheetId = "sheet_id"
    const val argRaceId = "race_id"
    const val argDocumentId = "document_id"

    //Route placeholders
    val sheetRoutePlaceholder = buildRoutePlaceholder(sheetPath, listOf(argSheetId))
    val sheetListRoutePlaceholder = buildRoutePlaceholder(sheetListPath)
    val selectRaceRoutePlaceholder = buildRoutePlaceholder(selectRacePath, listOf(argSheetId))
    val documentRoutePlaceholder = buildRoutePlaceholder(documentPath, listOf(argDocumentId))

    //Routes
    fun sheetRoute(sheetId: Long) = buildRoute(sheetPath, mapOf(argSheetId to sheetId))
    fun sheetListRoute() = buildRoute(sheetListPath)
    fun selectRaceRoute(sheetId: Long) = buildRoute(selectRacePath, mapOf(argSheetId to sheetId))
    fun documentPath(documentId: Long) = buildRoute(documentPath, mapOf(argDocumentId to documentId))

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