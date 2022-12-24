package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name

object SelectName {

    data class State(
        val isLoading: Boolean = true,
        val name: String = "",
    )

    sealed class Event {
        object NavigateToNext : Event()
    }
}