package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import dao.cacao.dnd5sheet.domain.model.Race

data class SelectRaceState(
    val isLoading: Boolean,
    val races: List<Race>,
) {
    companion object {
        fun loading() = SelectRaceState(
            isLoading = true,
            races = emptyList(),
        )
    }
}

sealed class SelectRaceEvent {
    data class NavigateToNext(val sheetId: Long) : SelectRaceEvent()
    data class NavigateToDocument(val documentId: Long) : SelectRaceEvent()
}