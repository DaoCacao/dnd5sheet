package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dao.cacao.dnd5sheet.domain.boundary.ClassRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectClassViewModel @Inject constructor(
    private val classRepository: ClassRepository,
) : ViewModel() {

    var state by mutableStateOf<SelectClassState>(SelectClassState.Loading)
        private set

    init {
        viewModelScope.launch {
            val classes = classRepository.getClasses().first()
            state = SelectClassState.Content(
                classes = classes,
            )
        }
    }
}