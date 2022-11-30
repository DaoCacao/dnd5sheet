package dao.cacao.dnd5sheet.presentation.sceen.sheet

sealed class SheetState {
    object Loading : SheetState()
    data class Content(
        val level: String,
        val characterName: String,
        val characterRace: String,
        val characterClass: String,
    ) : SheetState()
}