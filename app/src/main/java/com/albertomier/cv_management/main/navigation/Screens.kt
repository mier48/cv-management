package com.albertomier.cv_management.main.navigation

sealed class Screens(val route: String) {
    object Splash : Screens("splash")
    object Home : Screens("home")
    object Main : Screens("main")
    object Profile : Screens("profile")
    object AddCompany : Screens("add_company")
    object Followers : Screens("followers")
    object Following : Screens("following")
    object MessageList : Screens("message_list")
    object MessageDetail : Screens("message_detail")
    object Search : Screens("search")
    object Settings : Screens("settings")
    object Notifications : Screens("notifications")
    object Login : Screens("login")
    object Register : Screens("register")
    object AuthorDetail : Screens("author_detail")
    object PostDetail : Screens("post_detail")
}