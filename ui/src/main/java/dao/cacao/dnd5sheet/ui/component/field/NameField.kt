package dao.cacao.dnd5sheet.ui.component.field

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun NameField(
    modifier: Modifier = Modifier,
    value: String,
    onClick: () -> Unit,
) {
    ClickableField(
        modifier = modifier,
        label = "Name",
        value = value,
        onClick = onClick,
    )
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        NameField(
            value = "Alokozay",
            onClick = {},
        )
    }
}