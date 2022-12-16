package dao.cacao.dnd5sheet.presentation.screen.document

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText
import dao.cacao.dnd5sheet.presentation.R
import dao.cacao.dnd5sheet.ui.component.Screen
import dao.cacao.dnd5sheet.ui.component.state.LoadingState
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun DocumentScreen(
    state: DocumentState,
    onNavigateUp: (() -> Unit)? = null,
) {
    Screen(
        title = stringResource(R.string.text_document),
        subtitle = state.name,
        onNavigateUp = onNavigateUp,
    ) {
        when {
            state.isLoading -> {
                LoadingState()
            }
            state.isError -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "404",
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Document not found",
                        textAlign = TextAlign.Center,
                    )
                }
            }
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                ) {
                    RichText(
                        modifier = Modifier
                            .padding(16.dp),
                    ) {
                        Markdown(
                            content = state.text,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview(@PreviewParameter(StatePreviewProvider::class) state: DocumentState) {
    AppTheme {
        DocumentScreen(
            state = state,
        )
    }
}

@Composable
@Preview
private fun PreviewLoading() {
    AppTheme {
        DocumentScreen(
            state = DocumentState.loading(),
        )
    }
}

@Composable
@Preview
private fun PreviewContent() {
    AppTheme {
        DocumentScreen(
            state = DocumentState.content(
                "Document name",
                text = "Document text",
            ),
        )
    }
}

@Composable
@Preview
private fun PreviewError() {
    AppTheme {
        DocumentScreen(
            state = DocumentState.error(),
        )
    }
}

class StatePreviewProvider : PreviewParameterProvider<DocumentState> {
    override val values = sequenceOf(
        DocumentState.loading(),
        DocumentState.error(),
        DocumentState.content(
            "Document name",
            text = "Document text",
        ),
    )
}