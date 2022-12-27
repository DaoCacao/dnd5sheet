package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_abilities

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.AbilityRepository
import dao.cacao.dnd5sheet.presentation.ext.args
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state
import javax.inject.Inject

@HiltViewModel
class SelectAbilitiesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val args = args(SelectAbilitiesRoute, savedStateHandle)
    val state = state(SelectAbilities.State(isLoading = true))
    val event = event<SelectAbilities.Event>()

    init {
    }
}