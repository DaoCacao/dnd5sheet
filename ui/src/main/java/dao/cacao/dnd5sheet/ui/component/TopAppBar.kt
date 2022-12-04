package dao.cacao.dnd5sheet.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar(
    title: String,
    onNavigateUp: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (onNavigateUp != null) {
                BackIconButton(onClick = onNavigateUp)
            }
        }
    )
}