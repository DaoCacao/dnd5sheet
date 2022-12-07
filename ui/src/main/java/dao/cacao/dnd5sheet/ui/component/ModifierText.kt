package dao.cacao.dnd5sheet.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun ModifierText(
    value: Int?,
) {
    val formattedValue = when {
        value == null -> "  "
        value > 0 -> "+$value"
        else -> "$value"
    }
    Text(
        text = formattedValue,
        textDecoration = TextDecoration.Underline,
    )
}