package com.xyron.retrofitwithmvvmhilt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xyron.retrofitwithmvvmhilt.api.TweetsyAPI
import com.xyron.retrofitwithmvvmhilt.screens.CategoryScreen
import com.xyron.retrofitwithmvvmhilt.screens.DetailScreen
import com.xyron.retrofitwithmvvmhilt.ui.theme.RetrofitWithMvvmHiltTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RetrofitWithMvvmHiltTheme {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "RetrofitWithMvvmHilt")
                            }
                        )
                    }
                ) {
                    Box(
                        modifier = Modifier.padding(it)
                    ){
                        App()
                    }
                }


            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "category"){
        composable(route = "category"){
            CategoryScreen(onClick = {
                navController.navigate("detail/${it}")
            })
        }
        composable(route = "detail/{category}", arguments = listOf(
            navArgument("category"){
                type = NavType.StringType
            }
        )){
            val email = it.arguments!!.getString("category")
            DetailScreen()
        }
    }

}