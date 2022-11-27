package dao.cacao.dnd5sheet.presentation.sceen.sheet

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.presentation.component.Toolbar
import dao.cacao.dnd5sheet.presentation.component.state.ScaffoldLoadingState
import dao.cacao.dnd5sheet.presentation.preview.previewSheet
import dao.cacao.dnd5sheet.presentation.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SheetScreen(
    state: SheetState,
    onNavigateUp: (() -> Unit)? = null,
) {
    Scaffold(
        topBar = {
            Toolbar(
                title = "Character sheet",
                onNavigateUp = onNavigateUp,
            )
        },
    ) {
        when (state) {
            SheetState.Loading -> {
                ScaffoldLoadingState(paddingValues = it)
            }
            is SheetState.Content -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                    ) {
                        Text(
                            text = state.sheet.characterName.ifBlank { "New Character" },
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewLoading() {
    AppTheme {
        SheetScreen(
            state = SheetState.Loading,
        )
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewContent() {
    AppTheme {
        SheetScreen(
            state = SheetState.Content(
                sheet = previewSheet()
            ),
        )
    }
}