package dao.cacao.dnd5sheet.presentation.screen.sheet_list

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.presentation.R
import dao.cacao.dnd5sheet.ui.component.AlertDialog
import dao.cacao.dnd5sheet.ui.component.TopAppBar
import dao.cacao.dnd5sheet.ui.component.rememberAlertDialogState
import dao.cacao.dnd5sheet.ui.component.state.ScaffoldEmptyState
import dao.cacao.dnd5sheet.ui.component.state.ScaffoldLoadingState
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SheetListScreen(
    state: SheetList.State,
    onCreateNewSheetClick: () -> Unit = {},
    onDeleteSheetClick: (sheetId: Long) -> Unit = {},
    onSheetClick: (sheetId: Long) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(R.string.text_character_sheets),
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(stringResource(R.string.action_create_new_sheet))
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
            state.isLoading -> {
                ScaffoldLoadingState(paddingValues = it)
            }
            state.items.isEmpty() -> {
                ScaffoldEmptyState(paddingValues = it)
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                ) {
                    itemsIndexed(state.items) { index, item ->
                        val confirmDeleteDialogState = rememberAlertDialogState()
                        AlertDialog(
                            state = confirmDeleteDialogState,
                            title = stringResource(R.string.action_delete_sheet),
                            text = stringResource(R.string.confirm_delete_sheet),
                            confirmText = stringResource(R.string.action_delete),
                            dismissText = stringResource(R.string.action_cancel),
                            onConfirmClick = {
                                confirmDeleteDialogState.hide()
                                onDeleteSheetClick(item.id)
                            }
                        )
                        SheetListItem(
                            title = item.characterName.ifBlank { stringResource(R.string.text_new_character) },
                            subtitle = buildString {
                                val showRace = item.characterRace.isNotBlank()
                                val showClass = item.characterClass.isNotBlank()
                                val showLevel = item.level > 0

                                if (showRace) append(item.characterRace)
                                if (showRace && showClass) append(", ")
                                if (showClass) append(item.characterClass)
                                if ((showRace || showClass) && showLevel) append(", ")
                                if (showLevel) append(stringResource(id = R.string.text_n_level, item.level))
                            },
                            onClick = { onSheetClick(item.id) },
                            onLongClick = { confirmDeleteDialogState.show() },
                        )
                        if (index != state.items.lastIndex) {
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
        SheetListScreen(
            state = SheetList.State(isLoading = true),
        )
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewContent() {
    AppTheme {
        SheetListScreen(
            state = SheetList.State(
                items = List(5) {
                    SheetList.State.Item(
                        id = it.toLong(),
                        level = it,
                        characterName = "CharacterName",
                        characterRace = "Race",
                        characterClass = "Class",
                    )
                }
            )
        )
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun PreviewEmpty() {
    AppTheme {
        SheetListScreen(
            state = SheetList.State(
                items = emptyList(),
            ),
        )
    }
}