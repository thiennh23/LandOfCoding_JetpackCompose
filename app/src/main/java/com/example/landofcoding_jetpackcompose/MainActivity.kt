package com.example.landofcoding_jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.landofcoding_jetpackcompose.ui.theme.LandOfCodingJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            Greeting(name = "Thien")
//            Button(onClick = { println("onClick")}, content = {
//                Text(text = "Button")
//            })
            //Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null, tint = Color.Black)
            //Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
            /*TextField(value = "", onValueChange = {}, label = {
                Text(text = "Text")
            })*/
            
            /*FloatingActionButton(onClick = { *//*TODO*//* }) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null, tint = Color.Red)
            }*/

            /*ExtendedFloatingActionButton(
                text = { Text(text = "Add") },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                    )
                },
                onClick = { println("Hello") },
                Modifier.background(Color.Red)
            )*/

            //BoxLayout()
            ColumnLayout()

        }
    }
}

@Composable
fun RowLayout() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        //verticalArrangement: Top Center Bottom, SpaceBetween, SpaceAround, SpaceEvenly
        //SpaceEvenly: Every space equal
        //SpaceAround: First space and last space equal, and equal 2times all other space
        //spaceBy(x.dp): Use when want to space fixed x dp between components

        verticalAlignment = Alignment.CenterVertically
        //CenterVertically: Center
        //Top, Bottom
    ) {
        Text(text = "Hello")
        Text(text = "World")
        Text(text = "World1")
        Text(text = "Hello")
        Text(text = "World")
        Text(text = "World1")

    }
}

@Composable
fun ColumnLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        //verticalArrangement: Top Center Bottom, SpaceBetween, SpaceAround, SpaceEvenly
        //SpaceEvenly: Every space equal
        //SpaceAround: First space and last space equal, and equal 2times all other space
        //spaceBy(x.dp): Use when want to space fixed x dp between components

        horizontalAlignment = Alignment.Start
        //CenterHorizontally: Center
        //Start, End
    ) {
        Text(text = "Hello")
        Text(text = "World")
        Text(text = "World1")
        Text(text = "Hello")
        Text(text = "World")
        Text(text = "World1")

    }
}

@Preview
@Composable
fun preview() {
    //BoxLayout()
    //ColumnLayout()
    RowLayout()
}

@Composable
fun BoxLayout() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart
        ) {
        Text(text = "Hello", modifier = Modifier.align(Alignment.TopStart))
        Text(text = "Hello", modifier = Modifier.align(Alignment.TopCenter))
        Text(text = "Hello", modifier = Modifier.align(Alignment.TopEnd))
        Text(text = "Hello", modifier = Modifier.align(Alignment.CenterStart))
        Text(text = "Hello", modifier = Modifier.align(Alignment.Center))
        Text(text = "Hello", modifier = Modifier.align(Alignment.CenterEnd))
        Text(text = "Hello", modifier = Modifier.align(Alignment.BottomStart))
        Text(text = "Hello", modifier = Modifier.align(Alignment.BottomCenter))
        Text(text = "Hello", modifier = Modifier.align(Alignment.BottomEnd))



    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name", color = Color.Red, fontSize = 22.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
}

