package dao.cacao.dnd5sheet.presentation.component.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EmptyState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "No sheets",
        )
    }
}

@Composable
fun ScaffoldEmptyState(paddingValues: PaddingValues) {
    EmptyState(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    )
}