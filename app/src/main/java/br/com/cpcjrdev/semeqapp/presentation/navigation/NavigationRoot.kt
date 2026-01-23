package br.com.cpcjrdev.semeqapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import br.com.cpcjrdev.semeqapp.presentation.login.GearScreenRoot
import br.com.cpcjrdev.semeqapp.presentation.login.LoginScreenRoot

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<NavKey>(SemeqAppRoutes.Login) }

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { route ->
            when (route) {
                is SemeqAppRoutes.Login -> {
                    NavEntry(route) {
                        LoginScreenRoot(onLoginClick = {
                            backStack.add(
                                SemeqAppRoutes.Gears(it),
                            )
                        })
                    }
                }

                is SemeqAppRoutes.Gears -> {
                    NavEntry(route) {
                        GearScreenRoot(
                            userName = route.userName,
                            onNavigateBack = { backStack.removeLastOrNull() },
                        )
                    }
                }

                else -> {
                    error("Wrong NavRoute: $route")
                }
            }
        },
    )
}
