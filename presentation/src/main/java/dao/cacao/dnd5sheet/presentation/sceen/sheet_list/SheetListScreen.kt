package dao.cacao.dnd5sheet.presentation.sceen.sheet_list

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dao.cacao.dnd5sheet.domain.model.Sheet
import dao.cacao.dnd5sheet.presentation.component.Toolbar
import dao.cacao.dnd5sheet.presentation.component.state.ScaffoldEmptyState
import dao.cacao.dnd5sheet.presentation.component.state.ScaffoldLoadingState
import dao.cacao.dnd5sheet.presentation.preview.previewSheets
import dao.cacao.dnd5sheet.presentation.router.Routes
import dao.cacao.dnd5sheet.presentation.theme.AppTheme

@Composable
fun SheetListScreen(
    viewModel: SheetListViewModel,
    navController: NavController,
) {
    Content(
        state = viewModel.state,
        onCreateNewSheetClick = viewModel::onCreateNewSheetClick,
        onDeleteSheetClick = viewModel::onDeleteSheetClick,
        onSheetClick = { navController.navigate(Routes.Sheet.route(it.id)) },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Content(
    state: SheetListState,
    onCreateNewSheetClick: () -> Unit = {},
    onDeleteSheetClick: (Sheet) -> Unit = {},
    onSheetClick: (Sheet) -> Unit = {},
) {
    Scaffold(
        topBar = {
            Toolbar(
                title = "Character sheets",
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
        when (state) {
            SheetListState.Loading -> {
                ScaffoldLoadingState(paddingValues = it)
            }
            SheetListState.Empty -> {
                ScaffoldEmptyState(paddingValues = it)
            }
            is SheetListState.Content -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                ) {
                    itemsIndexed(state.sheets) { index, sheet ->
                        SheetListItem(
                            title = sheet.characterName.ifBlank { "New Character" },
                            subtitle = buildString {
                                if (sheet.race.isNotBlank()) append(sheet.race)
                                if (sheet.race.isNotBlank() && sheet.clazz.isNotBlank()) append("-")
                                if (sheet.clazz.isNotBlank()) append(sheet.clazz)
                            },
                            onClick = { onSheetClick(sheet) },
                            onLongClick = { onDeleteSheetClick(sheet) },
                        )
                        if (index != state.sheets.lastIndex) {
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
    subtitle: String,
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
            if (title.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            if (subtitle.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = subtitle,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewLoading() {
    AppTheme {
        Content(
            state = SheetListState.Loading,
        )
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewContent() {
    AppTheme {
        Content(
            state = SheetListState.Content(
                sheets = previewSheets(5),
            )
        )
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewEmpty() {
    AppTheme {
        Content(
            state = SheetListState.Empty,
        )
    }
}