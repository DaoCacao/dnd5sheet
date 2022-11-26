package dao.cacao.dnd5sheet.presentation.sceen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dao.cacao.dnd5sheet.domain.model.Sheet
import dao.cacao.dnd5sheet.presentation.component.EmptyState
import dao.cacao.dnd5sheet.presentation.component.LoadingState
import dao.cacao.dnd5sheet.presentation.theme.AppTheme

@Composable
fun SheetListScreen(
    viewModel: SheetListViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {
    Content(
        sheets = viewModel.state,
        onCreateNewSheetClick = viewModel::onCreateNewSheetClick,
        onDeleteSheetClick = viewModel::onDeleteSheetClick,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Content(
    sheets: List<Sheet>?,
    onCreateNewSheetClick: () -> Unit = {},
    onDeleteSheetClick: (Long) -> Unit = {},
    onSheetClick: (Long) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Sheets")
                },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text("Create new sheet")
                },
                icon = {
                    Icon(
                        painter = rememberVectorPainter(Icons.Default.Add),
                        contentDescription = null,
                    )
                },
                onClick = onCreateNewSheetClick,
            )
        }
    ) {
        when {
            sheets == null -> {
                LoadingState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                )
            }
            sheets.isEmpty() -> {
                EmptyState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                ) {
                    itemsIndexed(sheets) { index, sheet ->
                        SheetListItem(
                            title = sheet.characterName,
                            onClick = { onSheetClick(sheet.id) },
                            onLongClick = { onDeleteSheetClick(sheet.id) },
                        )
                        if (index != sheets.lastIndex) {
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun SheetListItem(
    title: String,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewContent() {
    AppTheme {
        Content(
            sheets = buildSheets(5),
        )
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewEmpty() {
    AppTheme {
        Content(
            sheets = buildSheets(0),
        )
    }
}

private fun buildSheets(count: Int) = List(count) {
    Sheet(
        id = it.toLong(),
        characterName = "Character #$it",
    )
}