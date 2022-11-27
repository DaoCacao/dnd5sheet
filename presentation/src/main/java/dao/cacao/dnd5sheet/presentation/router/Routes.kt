package dao.cacao.dnd5sheet.presentation.router

object Routes {
    object SheetList {
        const val ROUTE = "/sheet_list"
        fun route() = "/sheet_list"
    }

    object Sheet {
        const val ARG_SHEET_ID = "sheet_id"

        private const val PATH = "/sheet"
        private const val ARGS = "?$ARG_SHEET_ID={$ARG_SHEET_ID}"

        const val ROUTE = PATH + ARGS
        fun route(sheetId: Long) = buildRoute(PATH, mapOf(
            ARG_SHEET_ID to sheetId,
        ))
    }

    object SelectRace {
        const val PATH = "/select_race"
        const val ROUTE = PATH
        fun route() = buildRoute(PATH)
    }

    private fun buildRoute(path: String, args: Map<String, Any> = emptyMap()) = buildString {
        append(path)
        if (args.isNotEmpty()) {
            append("?")
            append(args.map { "${it.key}=${it.value}" }.joinToString("&"))
        }
    }
}