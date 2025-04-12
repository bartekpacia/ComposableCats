package com.example.composablecats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composablecats.ui.theme.ComposableCatsTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatScreen(
    catId: String,
    onBackClick: () -> Unit = {},
) {
    val apiClient = remember { CatApiClient() }
    val cat = remember { mutableStateOf<CatImageData?>(null) }

    LaunchedEffect(Unit) {
        delay(1_000) // Internet is too fast! Let's slow it down to see the progress indicator.
        cat.value = apiClient.getCatById(catId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cat Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            val catValue = cat.value
            if (catValue != null) {

                AsyncImage(
                    model = catValue.url,
                    contentDescription = "Cat image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Cat Image Information",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("ID: ${catValue.id}")
                Text("Width: ${catValue.width}")
                Text("Height: ${catValue.height}")
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "loading data..."
                )
            }
        }
    }
}

@Preview
@Composable
fun CatScreenPreview() {
    ComposableCatsTheme {
        CatScreen(catId = "4")
    }
}
