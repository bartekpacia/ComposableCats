package com.example.composablecats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.composablecats.ui.theme.ComposableCatsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // for example "7lr" or "9vp"
            ComposableCatsTheme {
                CatScreen(catId = "9vp")
            }
        }
    }
}
