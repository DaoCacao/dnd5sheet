package dao.cacao.dnd5sheet.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class StateViewModel<S>(initial: S) : ViewModel() {
    val state = MutableStateFlow(initial)
}