package dao.cacao.dnd5sheet.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
) {
    androidx.compose.material3.Button(
        onClick = onClick,
    ) {
        Text(
            text = text,
        )
    }
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        Button(
            text = "Button",
            onClick = {},
        )
    }
}