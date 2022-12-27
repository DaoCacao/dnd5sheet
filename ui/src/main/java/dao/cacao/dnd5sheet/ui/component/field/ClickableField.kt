package dao.cacao.dnd5sheet.ui.component.field

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClickableField(
    modifier: Modifier = Modifier,
    value: String,
    onClick: () -> Unit,
    label: String = "",
) {
    Column(
        modifier = modifier,
    ) {
        if (label.isNotBlank())
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline,
            )
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraSmall,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
            ),
            onClick = onClick,
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = value,
                )
                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                        .weight(1f),
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    AppTheme {
        ClickableField(
            label = "Label",
            value = "Value",
            onClick = {},
        )
    }
}