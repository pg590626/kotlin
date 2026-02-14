package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Start(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Start(modifier: Modifier = Modifier) {
    var pic by remember { mutableIntStateOf(1) }
    when (pic) {
        1 -> {
            Greeting(
                foto = R.drawable.foto1,
                title = R.string.Foto1T,
                author = R.string.Foto1A,
                onNextClick = { pic = 2 },
                onPrevClick = { pic = 3 }
            )
        }
        2 -> {
            Greeting(
                foto = R.drawable.foto2,
                title = R.string.Foto2T,
                author = R.string.Foto2A,
                onNextClick = { pic = 3 },
                onPrevClick = { pic = 1 },
            )
        }
        3 -> {
            Greeting(
                foto = R.drawable.foto3,
                title = R.string.Foto3T,
                author = R.string.Foto3A,
                onNextClick = { pic = 1 },
                onPrevClick = { pic = 2 },
            )
        }
    }
}


@Composable
fun Greeting(
    foto: Int, title: Int, author : Int,
    onNextClick: () -> Unit, onPrevClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        //Spacer(modifier = Modifier.height(60.dp))
        //Surface(
        //    modifier.fillMaxWidth().padding(top = 100.dp),
        //    // color = Color(0x00dadada),
        //    shadowElevation = 20.dp
        //) {
            Image(
                painter = painterResource(foto),
                contentDescription = null,
                modifier = modifier
                    .padding(top = 150.dp)
                    .size(300.dp)
                )
        // }
        // Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .height(70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(title),
                //modifier = modifier,
                //    //.background(Color(0xffE0E0E0))
                //    //.fillMaxWidth(),
                //textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(author),
                //modifier = modifier,
                    //.background(Color(0xffE0E0E0))
                    //.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        // Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = modifier.padding(bottom = 20.dp)
        ) {
            Button(
                onClick = onPrevClick,
                modifier = modifier.size(width = 150.dp, height = 35.dp),
            ) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.size(15.dp))
            Button(
                onClick = onNextClick,
                modifier = modifier.size(width = 150.dp, height = 35.dp),
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting(
            foto = R.drawable.foto1,
            title = R.string.Foto1T,
            author = R.string.Foto1A,
            onNextClick = { },
            onPrevClick = { },
        )
    }
}


