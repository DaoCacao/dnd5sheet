package dao.cacao.dnd5sheet.presentation.base

sealed class NavigationEvent {
    data class Navigate(
        val route: String,
        val popUpTo: String? = null,
    ) : NavigationEvent()

    object PopBack : NavigationEvent()

    object NavigateUp : NavigationEvent()
}