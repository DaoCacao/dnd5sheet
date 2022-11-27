package dao.cacao.dnd5sheet.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected val navigateEvent = MutableSharedFlow<NavigationEvent>()

    fun getNavigationEvent(): Flow<NavigationEvent> = navigateEvent

    fun onNavigateUpClick() {
        viewModelScope.launch {
            navigateEvent.emit(NavigationEvent.NavigateUp)
        }
    }

    protected fun navigateTo(route: String) {
        viewModelScope.launch {
            navigateEvent.emit(NavigationEvent.Navigate(route))
        }
    }
}



