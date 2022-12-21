package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import dao.cacao.dnd5sheet.domain.model.CharacterClass

data class SelectClassState(
    val isLoading: Boolean,
    val classes: List<CharacterClass>,
) {
    companion object {
        fun loading() = SelectClassState(
            isLoading = true,
            classes = emptyList(),
        )
    }
}

sealed class SelectClassEvent {
    data class NavigateToNext(val sheetId: Long) : SelectClassEvent()
    data class NavigateToDocument(val documentId: Long) : SelectClassEvent()
}