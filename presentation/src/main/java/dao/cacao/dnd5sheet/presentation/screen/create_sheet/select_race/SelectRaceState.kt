package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import dao.cacao.dnd5sheet.domain.model.Race

sealed class SelectRaceState {
    object Loading : SelectRaceState()
    data class Content(
        val races: List<Race>,
    ) : SelectRaceState()
}