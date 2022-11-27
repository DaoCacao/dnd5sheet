package dao.cacao.dnd5sheet.presentation.sceen.document

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import dao.cacao.dnd5sheet.presentation.component.Toolbar
import dao.cacao.dnd5sheet.presentation.component.state.ScaffoldLoadingState
import io.noties.markwon.Markwon

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DocumentScreen(
    state: DocumentState,
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current
    val markwon = remember { Markwon.create(context) }

    Scaffold(
        topBar = {
            Toolbar(
                title = "",
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
                    Text(
                        text = buildAnnotatedString {
                            append(markwon.toMarkdown(state.document.text))
                        },
                    )
                }
            }
        }
    }
}