package dao.cacao.dnd5sheet.presentation.screen.sheet_list

sealed class SheetListState {
    object Loading : SheetListState()
    object Empty : SheetListState()
    data class Content(
        val items: List<Item>,
    ) : SheetListState() {
        data class Item(
            val id: Long,
            val level: Int,
            val characterName: String,
            val characterRace: String,
            val characterClass: String,
        )
    }
}