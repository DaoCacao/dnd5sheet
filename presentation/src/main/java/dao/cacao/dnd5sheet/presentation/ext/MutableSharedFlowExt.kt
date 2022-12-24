package dao.cacao.dnd5sheet.presentation.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <T> MutableSharedFlow<T>.collectAsEvent(onEvent: (T) -> Unit) {
    LaunchedEffect(this) {
        collectLatest {
            onEvent(it)
        }
    }
}