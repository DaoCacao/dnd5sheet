package dao.cacao.dnd5sheet.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = "",
    onNavigateUp: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
                if (subtitle.isNotBlank()) {
                    Text(
                        text = subtitle,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        },
        navigationIcon = {
            if (onNavigateUp != null) {
                BackIconButton(onClick = onNavigateUp)
            }
        },
    )
}


@Composable
@Preview
private fun Preview() {
    AppTheme {
        TopAppBar(
            title = "Title",
            subtitle = "Subtitle",
            onNavigateUp = {},
        )
    }
}