package dao.cacao.dnd5sheet.presentation.screen.sheet_list

object SheetList {
    data class State(
        val isLoading: Boolean = false,
        val items: List<Item> = emptyList(),
    ) {
        data class Item(
            val id: Long,
            val level: Int,
            val characterName: String,
            val characterRace: String,
            val characterClass: String,
        )
    }

    sealed class Event {
        data class NavigateToSheet(val sheetId: Long) : Event()
        data class NavigateToCreateSheet(val sheetId: Long) : Event()
    }
}