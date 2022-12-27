package dao.cacao.dnd5sheet.ui.component.field

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun RaceField(
    modifier: Modifier = Modifier,
    value: String,
    onClick: () -> Unit,
) {
    ClickableField(
        modifier = modifier,
        label = "Race",
        value = value,
        onClick = onClick,
    )
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        RaceField(
            value = "Human",
            onClick = {},
        )
    }
}