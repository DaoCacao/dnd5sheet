package dao.cacao.dnd5sheet.presentation.router

object Routes {
    object SheetList {
        const val ROUTE = "/sheet_list"
        fun route() = "/sheet_list"
    }

    object Sheet {
        const val ARG_SHEET_ID = "sheet_id"
        const val ROUTE = "/sheet/{$ARG_SHEET_ID}"
        fun route(sheetId: Long) = "/sheet/$sheetId"
    }
}