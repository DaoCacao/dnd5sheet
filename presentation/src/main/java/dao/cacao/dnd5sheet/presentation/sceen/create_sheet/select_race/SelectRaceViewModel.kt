package dao.cacao.dnd5sheet.presentation.sceen.create_sheet.select_race

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.use_case.race.GetRacesUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectRaceViewModel @Inject constructor(
    private val getRacesUseCase: GetRacesUseCase,
) : ViewModel() {

    var state by mutableStateOf<SelectRaceState>(SelectRaceState.Loading)
        private set

    init {
        viewModelScope.launch {
            getRacesUseCase.invoke().collectLatest {
                state = SelectRaceState.Content(
                    races = it,
                )
            }
        }
    }
}