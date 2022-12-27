package dao.cacao.dnd5sheet.presentation.screen.document

object Document {

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val name: String = "",
        val text: String = "",
    )
}