package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_class

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dao.cacao.dnd5sheet.domain.model.CharacterClass
import dao.cacao.dnd5sheet.presentation.R
import dao.cacao.dnd5sheet.ui.component.TopAppBar
import dao.cacao.dnd5sheet.ui.component.list_item.ClassListItem
import dao.cacao.dnd5sheet.ui.component.state.ScaffoldLoadingState
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SelectClassScreen(
    state: SelectClassState,
    onNavigateUp: (() -> Unit)? = null,
    onClassClick: (CharacterClass) -> Unit = {},
    onClassInfoClick: (CharacterClass) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(R.string.text_create_character),
                subtitle = stringResource(R.string.text_select_class),
                onNavigateUp = onNavigateUp,
            )
        }
    ) {
        when (state) {
            SelectClassState.Loading -> {
                ScaffoldLoadingState(paddingValues = it)
            }
            is SelectClassState.Content -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = it),
                ) {
                    items(state.classes) {
                        ClassListItem(
                            name = it.name,
                            onClassClick = { onClassClick(it) },
                            onInfoClick = { onClassInfoClick(it) },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview1() {
    AppTheme {
        SelectClassScreen(
            state = SelectClassState.Loading
        )
    }
}

@Preview
@Composable
private fun Preview2() {
    AppTheme {
        SelectClassScreen(
            state = SelectClassState.Content(
                classes = List(5) {
                    CharacterClass(0, 0, "Class $it")
                },
            ),
        )
    }
}