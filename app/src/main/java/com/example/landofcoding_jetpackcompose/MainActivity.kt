package com.example.landofcoding_jetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.landofcoding_jetpackcompose.ui.theme.LandOfCodingJetpackComposeTheme
import com.example.landofcoding_jetpackcompose.ui.theme.Purple40
import com.example.landofcoding_jetpackcompose.ui.theme.Purple80
import com.example.landofcoding_jetpackcompose.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {

    val viewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    @SuppressLint("UnrememberedMutableState", "RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val state = viewModel.state.value

        LandOfCodingJetpackComposeTheme {

            Column(modifier = Modifier.fillMaxSize()) {

               /* //But the state will reset after we rotate the screen. So we will use rememberSaveable instead of remember
                //BUt it will only work with mutableStateOf()
                var textState by rememberSaveable {
                    mutableStateOf("")
                }

                //Because rememberSaveable cannot work with mutableListListOf()
                //=> We will use ViewModel
                var nameListState = remember {
                    mutableStateListOf<String>()
                }*/

                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f))
                {
                    items(state.namesList.size) {
                        Text(text = state.namesList[it])
                    }
                }

                StateLess(
                    textValue =  state.text,
                    onValueChange = {viewModel.updateText(it)},
                    onAddClick = {
                    viewModel.updateNameList(state.text)
                    viewModel.updateText("")
                })
            }

        } }
    }
}

//DAY4: Jetpack Compose State, stateful, stateless, save the ui state
//State is anything in your UI could change

@Preview
@Composable
fun StatePreview() {
//    StateFull()
   /* var textState by remember {
        mutableStateOf("")
    }*/
    //StateLess(textState, onValueChange = {textState = it})
}

//Stateless is when Composable does not create, holds and modify its own State.
@Composable
fun StateLess(
    textValue: String,
    onValueChange: (String) -> Unit,
    onAddClick:() -> Unit
) {

    TextField(
        value = textValue,
        onValueChange = {
            onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Icon(imageVector = Icons.Default.Add, contentDescription = null, Modifier.clickable {
                onAddClick()
            })
        }
    )


}


//THIS IS STATEFUL
//Stateful is when Composable create, holds and modifies its own State.
//WHEN YOU CALL StateUI() in the main function, it doesn't care about state => Stateful
//The weakness of Stateful is that it tends to be less reusable and difficult to test.
@Composable
fun StateFull() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        /*val textState = remember {
            mutableStateOf("")
        }

        TextField(
            value = textState.value, onValueChange = {
                textState.value = it
        }, Modifier.fillMaxWidth())
         */

        //After the recomposition, the value return back to ""
        //So we need to use the keyword remember for the variable to store data

        var textState by remember {
            mutableStateOf("")
        }

        TextField(
            value = textState, onValueChange = {
                textState = it
            }, Modifier.fillMaxWidth())
    }
}



//DAY3: Modifiers

//@Preview
@Composable
fun ModifierPreview() {
    //modifierFunc()
//    ModifierFunc1()
//    Test()
    testRow()
}

@Composable
fun testRow() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .background(Color.Red)
            .height(100.dp)
            .weight(2f))

        Spacer(Modifier.width(10.dp))

        Box(modifier = Modifier
            .background(Color.Red)
            .height(100.dp)
            .weight(1f))

    }
}

@Composable
fun ModifierFunc1() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            //Use clip when you want the shape change to other
            .border(width = 3.dp, color = Color.Green, shape = CircleShape)
            .border(width = 6.dp, color = Color.Black, shape = CircleShape)
            .background(Color.Red)
        )

        Box(modifier = Modifier
            .size(52.dp)
            .clip(RoundedCornerShape(30))
            //Control the radius of corner
            .background(Color.Cyan)
        )

        Box(modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(30))
            //Control the radius of corner
            .background(Color.Green)
        )


        Box(modifier = Modifier
            .size(25.dp)
            //.clip()
            .border(width = 2.dp, color = Color.White, shape = RectangleShape)
            .background(Color.Blue))
    }
}

@Composable
fun Test() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .size(150.dp, 100.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = Purple40)
            .clickable {
                Log.d("Tag", "Click")
            },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Button", color = Color.White, fontSize = 24.sp)
        }
    }
}

@Composable
fun modifierFunc() {
    Box(
        modifier = Modifier
            //.fillMaxSize(0.75f)
            //put the padding func above
            //because this will run sequentially, if you put it bellow the fillmax, it will forgot padding
            //.padding(start = 10.dp)
            .padding(horizontal = 10.dp, vertical = 20.dp)
            //horizon: Start and End
            //vertical: Top and Bot

            .fillMaxWidth(1f)
            .fillMaxHeight(0.5f)
            .background(color = Color.Red, shape = RectangleShape)
            //Actually, you just need provide 1 of the size property for modifier: .size(); .width(), .height(); fillMaxSize(); fillMaxWidth(); fillMaxHeight(); ....
            //.size(100.dp, 200.dp) => If you provide 2 number (W, H), it will define a rectangle
            //.size(100.dp) => If you just provide 1 number, width = height = number => Square
            //And remember, if you provide many size property, it will only take in the first declare property
            //.width(13330.dp)
            //.height(250.dp)
            //.fillMaxHeight()
            //.fillMaxWidth()

    ) {

    }
}





//DAY2: LAYOUTS IN JETPACK COMPOSE (Box, Column, Row)
//@Preview
@Composable
fun preview() {
    BoxLayout()
    ColumnLayout()
    RowLayout()
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

//DAY 1: BASIC COMPONENTS IN KOTLIN ANDROID + JETPACK COMPOSE

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name", color = Color.Red, fontSize = 22.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
}

@Composable
fun day1() {
    Greeting(name = "Thien")
    Button(onClick = { println("onClick")}, content = {
        Text(text = "Button")
    })

    Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null, tint = Color.Black)

    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)

    TextField(value = "", onValueChange = {}, label = {
        Text(text = "Text")
    })

    FloatingActionButton(onClick = {  }) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null, tint = Color.Red)
            }


    ExtendedFloatingActionButton(
        text = { Text(text = "Add") },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
            )
        },
        onClick = { println("Hello") },
        Modifier.background(Color.Red)
    )

}

