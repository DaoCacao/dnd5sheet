package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import dao.cacao.dnd5sheet.domain.model.CharacterClass

object SelectClass {

    data class State(
        val isLoading: Boolean = false,
        val classes: List<CharacterClass> = emptyList(),
    )

    sealed class Event {
        data class NavigateToNext(val sheetId: Long) : Event()
        data class NavigateToDocument(val documentId: Long) : Event()
        object NavigateBack : Event()
    }
}