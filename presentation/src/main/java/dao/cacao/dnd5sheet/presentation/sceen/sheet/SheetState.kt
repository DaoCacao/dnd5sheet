package dao.cacao.dnd5sheet.presentation.sceen.sheet

import dao.cacao.dnd5sheet.domain.model.Sheet

sealed class SheetState {
    object Loading : SheetState()
    data class Content(
        val sheet: Sheet,
    ) : SheetState()
}