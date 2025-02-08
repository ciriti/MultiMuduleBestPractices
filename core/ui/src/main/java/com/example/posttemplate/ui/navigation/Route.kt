package com.example.posttemplate.ui.navigation

sealed class Route(val route: String) {
    object Authentication : Route(route = "authentication_screen")
    object Posts : Route("home")
    object Account : Route("account")
    object Profile : Route("profile") {
        fun passUserId(userId: Int): String {
            return "profile/$userId"
        }
    }
}
