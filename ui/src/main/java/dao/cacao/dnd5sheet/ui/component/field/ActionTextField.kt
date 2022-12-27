package dao.cacao.dnd5sheet.ui.component.field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ActionTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    action: Painter,
    onActionClick: () -> Unit,
    actionContentDescription: String,
    label: String,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
            )
        },
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            IconButton(
                onClick = onActionClick,
            ) {
                Icon(
                    painter = action,
                    contentDescription = actionContentDescription,
                )
            }
        }
    )
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        ActionTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "Value",
            onValueChange = {},
            label = "Label",
            action = rememberVectorPainter(image = Icons.Default.Add),
            actionContentDescription = "Action",
            onActionClick = {},
        )
    }
}