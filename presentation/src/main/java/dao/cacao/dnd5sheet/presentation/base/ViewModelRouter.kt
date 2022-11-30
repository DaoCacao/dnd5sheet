package dao.cacao.dnd5sheet.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
inline fun <reified VM : BaseViewModel> ViewModelRouter(
    navController: NavController,
    viewModel: VM = hiltViewModel(),
    content: @Composable (VM) -> Unit,
) {
    LaunchedEffect("navigation") {
        viewModel.getNavigationEvent().collectLatest {
            when (it) {
                is NavigationEvent.Navigate -> {
                    navController.navigate(it.route) {
                        if (it.popUpTo != null) popUpTo(it.popUpTo)
                    }
                }
                NavigationEvent.PopBack -> {
                    navController.popBackStack()
                }
                NavigationEvent.NavigateUp -> {
                    navController.navigateUp()
                }
            }
        }
    }
    content(viewModel)
}