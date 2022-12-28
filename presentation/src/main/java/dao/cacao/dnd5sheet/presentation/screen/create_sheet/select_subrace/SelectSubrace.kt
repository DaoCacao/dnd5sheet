package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_subrace

import dao.cacao.dnd5sheet.domain.model.Subrace

object SelectSubrace {

    data class State(
        val isLoading: Boolean = false,
        val races: List<Subrace> = emptyList(),
    )

    sealed class Event {
        data class NavigateToNext(val sheetId: Long) : Event()
        data class NavigateToDocument(val documentId: Long) : Event()
        object NavigateBack : Event()
    }
}