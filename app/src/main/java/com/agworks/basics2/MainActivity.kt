package com.agworks.basics2

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agworks.basics2.ui.theme.Basics2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Basics2Theme {
                MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {
    val taps = remember {
        mutableIntStateOf(0)
    }// State Hoisting
    var clicks by remember {
        mutableIntStateOf(0)
    }// If we don't use this the value will don't update in UI/ Composable
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "$ ${taps.intValue}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(
                modifier = Modifier.height(25.dp)
            )
            CreateCircle(taps = taps.intValue)
            {
                taps.intValue = it+100
            }
            Spacer(
                modifier = Modifier.height(25.dp)
            )
            // Using Button
            Button(
                onClick = {
                          clicks+=100
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ){
                Text(
                    text = "Tap",
                    color = Color.Black
                )
            }
            Spacer(
                modifier = Modifier.height(25.dp)
            )
            Text(
                text = "$ $clicks",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}


@Composable
fun CreateCircle( taps : Int  = 0, updateClicks : (Int) ->Unit) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(60.dp)
            .clickable {
                       updateClicks(taps)
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(7.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Tap",
                modifier = Modifier,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Basics2Theme {
        MyApp()
    }
}