package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_abilities

object SelectAbilities {

    data class State(
        val isLoading: Boolean = false,
        val abilities: List<Ability> = emptyList(),
    ) {
        data class Ability(
            val id: Long,
            val name: String,
            val score: Int,
            val modifier: Int?,
        )
    }

    sealed class Event {
        data class NavigateToNext(val sheetId: Long) : Event()
        object NavigateBack : Event()
    }
}

