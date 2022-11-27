package dao.cacao.dnd5sheet.presentation.sceen.sheet_list

import dao.cacao.dnd5sheet.domain.model.Sheet

sealed class SheetListState {
    object Loading : SheetListState()
    object Empty : SheetListState()
    data class Content(
        val sheets: List<Sheet>,
    ) : SheetListState()
}