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
import com.example.nftticketingapp.DataClasses.Event2
import com.example.nftticketingapp.DataClasses.TicketEvent
import com.example.nftticketingapp.graphs.EventScreen

private val event_list  = mutableListOf(
    Event2(
        name =  "Kendrick Lamar Concert",
        numberOfTickets = 1,
        price = 10.0,
        artist = "Kendrick Lamar",
        address = "Rue Le-Corbusier",
        date = "21/10/2023",
        description = "Best concert of your life"
    )
)

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

            /*items(event_list) { event2 ->
                Event(event2, navController)
            }*/
        }
    }
}


@Composable
fun Event(
    event2: TicketEvent,
    navController: NavHostController
) {
    Surface(
        modifier = Modifier
            .clickable {
            navController.navigate(
                EventScreen.Event.passNameAndArtist(
                    name = event2.event.name,
                    artist = event2.event.artist
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
                text = event2.event.artist,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = event2.event.name,
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