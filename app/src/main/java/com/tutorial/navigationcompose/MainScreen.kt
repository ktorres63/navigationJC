package com.tutorial.navigationcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

//explanation: https://saurabhjadhavblogs.com/jetpack-compose-bottom-navigation-nested-navigation-solved
@Composable
fun MainScreen(navController: NavHostController) {
    // This keeps track of the navigation stack's current state
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Extract the current route from the navigation stack used to determine which nav ites should be shown as selected
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                NavigationBar {
                    bottomNavigationItems.forEach { item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = null
                                )
                            },
                            label = { Text(text = item.label) },
                            selected = currentRoute == item.route,

                            onClick = {
                                      if(currentRoute != item.route){
                                          navController.navigate(item.route){
                                              popUpTo(navController.graph.startDestinationId)
                                              launchSingleTop = true

                                          }
                                      }
                            },
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        AppNavigation(navController, Modifier.padding(innerPadding))

    }
}

data class NavigationItem(val route: String, val label: String, val icon: ImageVector)