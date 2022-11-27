package dao.cacao.dnd5sheet.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <VM : BaseViewModel> ViewModelRouter(
    viewModel: VM,
    navController: NavController,
    content: @Composable () -> Unit,
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
    content()
}