package dao.cacao.dnd5sheet.presentation.screen.sheet_list

data class SheetListState(
    val isLoading: Boolean,
    val items: List<Item>,
) {
    data class Item(
        val id: Long,
        val level: Int,
        val characterName: String,
        val characterRace: String,
        val characterClass: String,
    )

    companion object {
        fun loading() = SheetListState(
            isLoading = true,
            items = emptyList(),
        )

        fun content(items: List<Item>) = SheetListState(
            isLoading = false,
            items = items,
        )
    }
}

sealed class SheetListEvent {
    data class NavigateToSheet(val sheetId: Long) : SheetListEvent()
    data class NavigateToCreateSheet(val sheetId: Long) : SheetListEvent()
}