package dao.cacao.dnd5sheet.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Screen(
    title: String,
    onNavigateUp: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                onNavigateUp = onNavigateUp,
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            content = content,
        )
    }
}