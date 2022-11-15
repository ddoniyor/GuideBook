package com.example.guidebook.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.guidebook.presentation.guide_detail.GuideWebView
import com.example.guidebook.presentation.guides_list.GuidesList
import com.google.gson.Gson

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = GuideBookScreen.Main.screen_route
    ) {
        composable(route = GuideBookScreen.Main.screen_route) {
            GuidesList(navController)
        }
        composable(
            route = GuideBookScreen.Detail.screen_route + "/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
             GuideWebView(backStackEntry.arguments?.getString("url")!!)
            /*backStackEntry.arguments?.getString("url")?.let { jsonString ->
                val url = jsonString.fromJson(Guide::class.java)
                GuideWebView(url)
            }*/
        }
    }
}
fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}