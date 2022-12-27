package dao.cacao.dnd5sheet.presentation.screen.create_sheet.select_name

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dao.cacao.dnd5sheet.presentation.R
import dao.cacao.dnd5sheet.ui.component.Button
import dao.cacao.dnd5sheet.ui.component.Screen
import dao.cacao.dnd5sheet.ui.component.field.TextField
import dao.cacao.dnd5sheet.ui.component.state.LoadingState
import dao.cacao.dnd5sheet.ui.theme.AppTheme

@Composable
fun SelectNameScreen(
    state: SelectName.State,
    onNameChange: (String) -> Unit = {},
    onSaveClick: () -> Unit = {},
    onNavigateUp: (() -> Unit)? = null,
) {
    Screen(
        title = stringResource(id = R.string.text_create_character),
        subtitle = stringResource(id = R.string.text_select_name),
        onNavigateUp = onNavigateUp,
    ) {
        when {
            state.isLoading -> {
                LoadingState()
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.End,
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.name,
                        onValueChange = onNameChange,
                        label = stringResource(id = R.string.text_character_name),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                        )
                    )
                    Button(
                        text = stringResource(R.string.action_save),
                        onClick = onSaveClick,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun PreviewLoading() {
    AppTheme {
        SelectNameScreen(
            state = SelectName.State(
                isLoading = true,
            ),
        )
    }
}

@Composable
@Preview
private fun PreviewContent() {
    AppTheme {
        SelectNameScreen(
            state = SelectName.State(
                isLoading = false,
                name = "Character name",
            ),
        )
    }
}