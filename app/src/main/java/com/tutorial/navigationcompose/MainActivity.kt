package com.tutorial.navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tutorial.navigationcompose.ui.theme.NavigationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationComposeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}

//Explanation: https://saurabhjadhavblogs.com/ultimate-guide-to-jetpack-compose-navigation
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen1") {
        composable(route = "screen1") { Screen1(navController) }
        composable(route = "screen2") { Screen2(navController) }
        composable(route = "screen3") { Screen3(navController) }
        composable(
            route = "screen4/{data}",
            arguments = listOf(navArgument("data") { type = NavType.StringType })
        ) { navBackStackEntry ->
            Screen4(navController = navController, data = navBackStackEntry.arguments?.getString("data")?: "" )
        }
    }
}


@Composable
fun Screen1(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("This is Screen 1")
        Button(onClick = {
            navController.navigate("screen2")
        }) {
            Text(text = "Go to Screen 2")
        }
    }
}

@Composable
fun Screen2(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("This is Screen 2")
        Button(onClick = {
            navController.navigate("screen3") {
                //This will remove Screen 2 from the stack
                popUpTo("screen2") { inclusive = true }
            }
        }) {
            Text(text = "Go to Screen 3")
        }
    }
}

@Composable
fun Screen3(navController: NavController) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter something") }
        )

        Button(onClick = { navController.navigate("screen4/$text") }) {
            Text("Go to Screen 4 with data")
        }
    }
}

@Composable
fun Screen4(navController: NavController, data: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Received data: $data")
    }
}
