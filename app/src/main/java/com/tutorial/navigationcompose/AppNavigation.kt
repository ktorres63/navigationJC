package com.tutorial.navigationcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

//Explanation navigation: https://saurabhjadhavblogs.com/ultimate-guide-to-jetpack-compose-navigation
@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
      NavHost(navController = navController, startDestination = "bottom_screen1") {
        // Define composable screens for bottom navigation
        composable("bottom_screen1") { BottomScreen1(navController) }
        composable("bottom_screen2") { BottomScreen2(navController) }


        composable(route = "screen1") { Screen1(navController) }
        composable(route = "screen2") { Screen2(navController) }
        composable(route = "screen3") { Screen3(navController) }
        composable(
            route = "screen4/{data}",
            arguments = listOf(navArgument("data") { type = NavType.StringType })
        ) { navBackStackEntry ->
            Screen4(
                navController = navController,
                data = navBackStackEntry.arguments?.getString("data") ?: ""
            )
        }
    }
}

val bottomNavigationItems = listOf(
    NavigationItem("bottom_screen1", "Screen 1", Icons.Filled.Home),
    NavigationItem("bottom_screen2", "Screen 2", Icons.Filled.Settings)
)

val bottomBarRoutes = setOf("bottom_screen1", "bottom_screen2")

