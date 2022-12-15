package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_race

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.RaceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectRaceViewModel @Inject constructor(
    private val raceRepository: RaceRepository,
) : ViewModel() {

    var state by mutableStateOf<SelectRaceState>(SelectRaceState.Loading)
        private set

    init {
        viewModelScope.launch {
            val races = raceRepository.getRaces().first()
            state = SelectRaceState.Content(
                races = races,
            )
        }
    }
}