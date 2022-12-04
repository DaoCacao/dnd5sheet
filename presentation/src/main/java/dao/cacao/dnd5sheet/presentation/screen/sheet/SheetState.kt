package dao.cacao.dnd5sheet.presentation.screen.sheet

sealed class SheetState {
    object Loading : SheetState()
    data class Content(
        val level: Int,
        val characterName: String,
        val characterRace: String,
        val characterClass: String,
        val strength: Int,
        val dexterity: Int,
        val constitution: Int,
        val intelligence: Int,
        val wisdom: Int,
        val charisma: Int,
    ) : SheetState()
}