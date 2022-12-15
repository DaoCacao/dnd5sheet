package dao.cacao.dnd5sheet.ui.component.list_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassListItem(
    modifier: Modifier = Modifier,
    name: String,
    onClassClick: () -> Unit = {},
    onInfoClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier,
        onClick = onClassClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = name,
            )
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = onInfoClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Information",
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        ClassListItem(
            modifier = Modifier.fillMaxWidth(),
            name = "Class",
        )
    }
}