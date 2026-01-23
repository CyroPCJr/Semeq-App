package br.com.cpcjrdev.semeqapp.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface SemeqAppRoutes : NavKey {
    @Serializable
    data object Login : SemeqAppRoutes

    @Serializable
    data class Gears(
        val userName: String,
    ) : SemeqAppRoutes
}
