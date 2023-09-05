package com.musfick.playground.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.musfick.playground.SampleViewModel
import com.musfick.playground.UIState
import org.koin.androidx.compose.getViewModel

@Composable
fun DefaultTextView(viewModel: SampleViewModel = getViewModel()) {
    val uiState by viewModel.uiState.collectAsState(initial = UIState.Loading)
    when(uiState){
        is UIState.Data -> {
            Text(text = (uiState as UIState.Data<String>).value)
        }
        is UIState.Empty -> {
            Text(text = "Empty")
        }
        is UIState.Error -> {
            Text(text = (uiState as UIState.Error).throwable.message.toString())
        }
        is UIState.Loading -> {
            CircularProgressIndicator()
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    DefaultTextView()
                }
            }
        }
    }
}