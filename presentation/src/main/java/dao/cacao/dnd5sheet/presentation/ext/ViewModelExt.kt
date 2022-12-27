package dao.cacao.dnd5sheet.presentation.ext

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dao.cacao.dnd5sheet.presentation.base.RouteWithArgs
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

fun <R : RouteWithArgs<A>, A> ViewModel.args(route: R, savedStateHandle: SavedStateHandle) = route.args(savedStateHandle)
fun <S> ViewModel.state(initial: S) = MutableStateFlow(initial)
fun <E> ViewModel.event() = MutableSharedFlow<E>()