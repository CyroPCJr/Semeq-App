package br.com.cpcjrdev.semeqapp.ui.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface SemeqAppRoutes {
    @Serializable
    data object Login : SemeqAppRoutes

    @Serializable
    data class Gears(
        val userName: String,
    ) : SemeqAppRoutes
}
