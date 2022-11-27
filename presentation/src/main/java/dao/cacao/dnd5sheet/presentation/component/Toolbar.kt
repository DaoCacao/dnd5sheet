package dao.cacao.dnd5sheet.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Toolbar(
    onNavigateUp: (() -> Unit)? = null,
    title: String,
) {
    TopAppBar(
        navigationIcon = {
            if (onNavigateUp != null) {
                IconButton(
                    onClick = onNavigateUp,
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                        contentDescription = "Back",
                    )
                }
            }
        },
        title = {
            Text(title)
        },
    )
}