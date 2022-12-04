package dao.cacao.dnd5sheet.presentation.screen.document

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText
import dao.cacao.dnd5sheet.presentation.component.Toolbar
import dao.cacao.dnd5sheet.presentation.component.state.ScaffoldLoadingState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DocumentScreen(
    state: DocumentState,
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            Toolbar(
                title = when (state) {
                    DocumentState.Loading -> "Document"
                    is DocumentState.Content -> state.document.name
                },
                onNavigateUp = onNavigateUp,
            )
        }
    ) {
        when (state) {
            DocumentState.Loading -> {
                ScaffoldLoadingState(paddingValues = it)
            }
            is DocumentState.Content -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .verticalScroll(rememberScrollState()),
                ) {
                    RichText(
                        modifier = Modifier
                            .padding(16.dp),
                    ) {
                        Markdown(content = state.document.text)
                    }
                }
            }
        }
    }
}