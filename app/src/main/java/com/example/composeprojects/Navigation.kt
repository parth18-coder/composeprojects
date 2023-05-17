package com.example.composeprojects

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun navigation(){
    // we want to control the navHost so we use the nav controller.
    // rememberNavController return the NavHostController.
    var navController=rememberNavController()

    // Now this NavHost listen commands from this 'navController'.
    /*
    Here we don't specify the graph, instead we specify the start destination.Here we specify the routes. These routes are basically the
    strings. This work similar to the browser url. It is same like we visit the website using the url.

     */
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        // here we define different composable that we have. this tell navHost what are different screens we have to navigate.
        composable(route=Screen.MainScreen.route){
            MainScreen(navController = navController)
        }
        // here we are linking the route with the composable that we want to navigate to. when we use navController.navigate(route) the
        // corresponding composable function get invoke.

        /*
        The back stack in Jetpack Compose Navigation is managed automatically by the navigation library, allowing you to focus on
        defining the navigation graph and handling navigation events.
        It's worth noting that the back stack can be modified programmatically as well, using navigation actions or methods provided by
        the navigation library, in addition to the user's back button interactions.
         */

        composable(route=Screen.DetailScreen.route+"/{name}",
            arguments= listOf(
                navArgument("name"){
                    type= NavType.StringType
                    defaultValue="Parth"
                    nullable=true
                }
            )
        ){entry->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }
}

//Modifier.Align() is a modifier that is used for the alignment.
@Composable
fun MainScreen(navController:NavController){
    var textState by remember{mutableStateOf("")}
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = textState,
            onValueChange = {
            textState = it
        })
        Spacer(modifier=Modifier.height(8.dp))
        Button(onClick = {
                         navController.navigate(Screen.DetailScreen.withArgs(textState))
        },
        modifier=Modifier.align(Alignment.End)) {
            Text(text = "To DetailScreen")
        }

    }
}

// '?' in String? make the string nullable. But at present I don't know the what is nullable string
@Composable
fun DetailScreen(name:String?){
    Box(contentAlignment = Alignment.Center,modifier=Modifier.fillMaxSize()) {
        Text(text = "Hello $name")
    }

}