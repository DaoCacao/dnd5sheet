package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import dao.cacao.dnd5sheet.domain.model.Race

object SelectRace {

    data class State(
        val isLoading: Boolean = false,
        val races: List<Race> = emptyList(),
    )

    sealed class Event {
        data class NavigateToSelectClass(val sheetId: Long) : Event()
        data class NavigateToSelectSubrace(val sheetId: Long, val raceId: String) : Event()
        data class NavigateToDocument(val documentId: Long) : Event()
        object NavigateBack : Event()
    }
}