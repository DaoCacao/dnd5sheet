package dao.cacao.dnd5sheet.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<S, E>(initial: S) : ViewModel() {
    val state = MutableStateFlow(initial)
    val event = MutableSharedFlow<E>()
}