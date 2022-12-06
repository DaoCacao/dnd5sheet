package dao.cacao.dnd5sheet.ui.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun AlertDialog(
    state: AlertDialogState = rememberAlertDialogState(),
    title: String,
    text: String,
    confirmText: String,
    dismissText: String,
    onConfirmClick: () -> Unit = { state.hide() },
    onDismissClick: () -> Unit = { state.hide() },
    onDismissRequest: () -> Unit = { state.hide() },
) {
    if (state.show) {
        androidx.compose.material3.AlertDialog(
            title = {
                Text(text = title)
            },
            text = {
                Text(text = text)
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmClick,
                ) {
                    Text(text = confirmText)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissClick,
                ) {
                    Text(text = dismissText)
                }
            },
            onDismissRequest = onDismissRequest,
        )
    }
}

@Stable
class AlertDialogState(show: Boolean) {

    var show by mutableStateOf(show)
        private set

    fun show() {
        show = true
    }

    fun hide() {
        show = false
    }

    companion object {
        /**
         * The default [Saver] implementation for [AlertDialogState].
         */
        val Saver: Saver<AlertDialogState, *> = Saver(
            save = { it.show },
            restore = {
                AlertDialogState(
                    show = it,
                )
            }
        )
    }
}

@Composable
fun rememberAlertDialogState(show: Boolean = false): AlertDialogState {
    return rememberSaveable(saver = AlertDialogState.Saver) {
        AlertDialogState(show)
    }
}