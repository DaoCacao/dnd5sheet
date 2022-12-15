package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import dao.cacao.dnd5sheet.domain.model.CharacterClass

sealed class SelectClassState {
    object Loading : SelectClassState()
    data class Content(
        val classes: List<CharacterClass>,
    ) : SelectClassState()
}