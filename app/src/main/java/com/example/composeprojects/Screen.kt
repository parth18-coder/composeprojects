package com.example.composeprojects

// Here we can specify the single screen of our app.
/*
sealed classes is the class that allow only classes that are inside the screen class or this file to inherit from the screen class.
 */
sealed class Screen(val route: String){
    object MainScreen: Screen("main_screen")
    object DetailScreen: Screen("detail_screen")

    fun withArgs(vararg args:String):String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}

