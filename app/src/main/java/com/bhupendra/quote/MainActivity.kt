package com.bhupendra.quote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bhupendra.quote.utils.category
import com.bhupendra.quote.utils.details
import com.bhupendra.quote.screens.CategoryScreen
import com.bhupendra.quote.screens.DetailScreen
import com.bhupendra.quote.theme.QuoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuoteTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(text = resources.getString(R.string.app_name))
                        }, backgroundColor = Color.Black, contentColor = Color.White)
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = category) {
        composable(route = category) {
            CategoryScreen {
                navController.navigate("detail/${it}")
            }
        }
        composable(route = details,
            arguments = listOf(
                navArgument(category) {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen()
        }
    }
}