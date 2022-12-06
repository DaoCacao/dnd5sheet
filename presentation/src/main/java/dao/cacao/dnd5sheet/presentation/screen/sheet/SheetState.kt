package dao.cacao.dnd5sheet.presentation.screen.sheet

sealed class SheetState {
    object Loading : SheetState()
    data class Content(
        val level: Int,
        val characterName: String,
        val characterRace: String,
        val characterClass: String,
        val abilities: List<Ability>,
    ) : SheetState() {
        data class Ability(
            val id: Long,
            val name: String,
            val value: Int,
        )
    }
}