package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name

data class SelectNameState(
    val isLoading: Boolean,
    val name: String,
) {
    companion object {
        fun loading() = SelectNameState(
            isLoading = true,
            name = "",
        )

        fun content(
            name: String,
        ) = SelectNameState(
            isLoading = false,
            name = name,
        )
    }
}