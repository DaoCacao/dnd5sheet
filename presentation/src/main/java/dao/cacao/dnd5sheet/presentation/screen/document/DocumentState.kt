package dao.cacao.dnd5sheet.presentation.screen.document

data class DocumentState(
    val isLoading: Boolean,
    val isError: Boolean,
    val name: String,
    val text: String,
) {
    companion object {
        fun loading() = DocumentState(
            isLoading = true,
            isError = false,
            name = "",
            text = "",
        )

        fun error() = DocumentState(
            isLoading = false,
            isError = true,
            name = "",
            text = "",
        )

        fun content(name: String, text: String) = DocumentState(
            isLoading = false,
            isError = false,
            name = name,
            text = text,
        )
    }
}