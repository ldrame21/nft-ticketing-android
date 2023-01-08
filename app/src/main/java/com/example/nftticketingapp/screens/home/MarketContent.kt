package com.example.nftticketingapp.screens.home
// MarketPlace with Scrolling menu of all available events

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nftticketingapp.graphs.EventScreen

private val event_list  = mutableListOf(
    Event(
        id=1,
        artist = "1",
        name = "Super1 Concert"
    ),
    Event(
        id=2,
        artist = "Bebou2",
        name = "Super Concert2"
    ),
    Event(
        id=3,
        artist = "Bebou3",
        name = "Super Concert3"
    ),Event(
        id=1,
        artist = "1",
        name = "Super1 Concert"
    ),
    Event(
        id=2,
        artist = "Bebou2",
        name = "Super Concert2"
    ),
    Event(
        id=3,
        artist = "Bebou3",
        name = "Super Concert3"
    ),Event(
        id=1,
        artist = "1",
        name = "Super1 Concert"
    ),
    Event(
        id=2,
        artist = "Bebou2",
        name = "Super Concert2"
    ),
    Event(
        id=3,
        artist = "Bebou3",
        name = "Super Concert3"
    ))

@Composable
fun MarketContent(
    navController: NavHostController
) {
    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentAlignment = Alignment.Center

        ) {
            Text(text = "MarketPlace",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        )
        {

            items(event_list) { event ->
                Event(event, navController)
            }
        }
    }
}


@Composable
fun Event(
    event: Event,
    navController: NavHostController
) {
    Surface(
        modifier = Modifier
            .clickable {
            navController.navigate(
                EventScreen.Event.passNameAndArtist(
                    name = event.name,
                    artist = event.artist
                )
            )
        }
            .fillMaxWidth()
            .height(120.dp)
            .border(width = 0.1.dp, color = Color.Gray)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = event.artist,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = event.name,
                fontSize = MaterialTheme.typography.h5.fontSize,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EventPreview() {
    MarketContent(navController = rememberNavController())
}