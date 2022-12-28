package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.RaceRepository
import dao.cacao.dnd5sheet.domain.boundary.SheetRepository
import dao.cacao.dnd5sheet.domain.model.Race
import dao.cacao.dnd5sheet.presentation.ext.args
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectRaceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val raceRepository: RaceRepository,
    private val sheetRepository: SheetRepository,
) : ViewModel() {

    val args = args(SelectRaceRoute, savedStateHandle)
    val state = state(SelectRace.State(isLoading = true))
    val event = event<SelectRace.Event>()

    init {
        viewModelScope.launch {
            val races = raceRepository.getRaces().first()
            state.update {
                it.copy(
                    isLoading = false,
                    races = races,
                )
            }
        }
    }

    fun onRaceClick(race: Race) {
        viewModelScope.launch {
            sheetRepository.updateCharacterRace(sheetId = args.sheetId, raceId = race.id)
            if (args.popBackStack)
                event.emit(SelectRace.Event.NavigateBack)
            else
                event.emit(SelectRace.Event.NavigateToNext(sheetId = args.sheetId))
        }
    }

    fun onRaceInfoClick(race: Race) {
        viewModelScope.launch {
            event.emit(SelectRace.Event.NavigateToDocument(documentId = 0))
        }
    }
}