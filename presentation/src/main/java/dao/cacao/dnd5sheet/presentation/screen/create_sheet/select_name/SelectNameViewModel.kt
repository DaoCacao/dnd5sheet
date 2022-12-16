package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name

import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectNameViewModel @Inject constructor() : BaseViewModel<SelectNameState, Unit>(SelectNameState.loading()) {

    init {
        state.update { it.copy(isLoading = false) }
    }

    fun onNameChange(name: String) {
        state.update { it.copy(name = name) }
    }
}