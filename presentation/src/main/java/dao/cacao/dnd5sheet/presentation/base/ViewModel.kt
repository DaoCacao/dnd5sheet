package dao.cacao.dnd5sheet.presentation.base

import androidx.lifecycle.ViewModel
import dao.cacao.dnd5sheet.presentation.ext.event
import dao.cacao.dnd5sheet.presentation.ext.state

abstract class BaseViewModel<S, E>(initial: S) : ViewModel() {
    val state = state(initial)
    val event = event<E>()
}