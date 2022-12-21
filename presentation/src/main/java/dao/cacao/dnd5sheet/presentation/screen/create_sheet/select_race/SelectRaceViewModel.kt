package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.RaceRepository
import dao.cacao.dnd5sheet.domain.model.Race
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectRaceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val raceRepository: RaceRepository,
) : ViewModel() {

    private val args = SelectRaceRoute.args(savedStateHandle)
    val state = MutableStateFlow(SelectRaceState.loading())
    val event = MutableSharedFlow<SelectRaceEvent>()

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
            raceRepository.updateCharacterRace(sheetId = args.sheetId, raceId = race.id)
            event.emit(SelectRaceEvent.NavigateToNext(sheetId = args.sheetId))
        }
    }

    fun onRaceInfoClick(race: Race) {
        viewModelScope.launch {
            event.emit(SelectRaceEvent.NavigateToDocument(documentId = race.documentId))
        }
    }
}