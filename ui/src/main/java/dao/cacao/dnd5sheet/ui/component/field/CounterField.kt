package dao.cacao.dnd5sheet.ui.component.field

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun CounterField(
    modifier: Modifier = Modifier,
    value: Int,
    label: String,
    onValueChange: (Int) -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onValueChange(value - 1) },
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowLeft),
                        contentDescription = "Decrement",
                    )
                }
                Text(
                    text = value.toString(),
                )
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onValueChange(value + 1) },
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowRight),
                        contentDescription = "Increment",
                    )
                }
            }
            Text(
                text = label,
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        CounterField(
            value = 0,
            label = "Counter",
            onValueChange = {},
        )
    }
}