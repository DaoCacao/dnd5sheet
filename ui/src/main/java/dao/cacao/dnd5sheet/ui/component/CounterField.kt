package dao.cacao.dnd5sheet.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CounterField(
    modifier: Modifier = Modifier,
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value.toString(),
        onValueChange = {},
        label = {
            Text(
                text = label,
            )
        },
        readOnly = true,
        leadingIcon = {
            IconButton(
                onClick = { onValueChange(value - 1) },
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowLeft),
                    contentDescription = "Decrement",
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = { onValueChange(value + 1) },
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowRight),
                    contentDescription = "Increment",
                )
            }
        },
    )
}