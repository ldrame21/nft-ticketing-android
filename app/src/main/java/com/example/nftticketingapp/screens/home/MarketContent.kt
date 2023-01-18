package com.example.nftticketingapp.screens.home
// MarketPlace with Scrolling menu of all available events

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nftticketingapp.DataClasses.TicketEvent
import com.example.nftticketingapp.ViewModel.MarketViewModel
import com.example.nftticketingapp.graphs.EventScreen


@Composable
fun MarketContent(
    navController: NavHostController
) {

    val marketViewModel = MarketViewModel()
    val ticketEvent = marketViewModel.ticketEventData.observeAsState()

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

            ticketEvent.value?.forEach { event ->
                item {
                    Event(event, navController)
                }
            }
        }
    }
}


@Composable
fun Event(
    event: TicketEvent?,
    navController: NavHostController
) {
    Surface(
        modifier = Modifier
            .clickable {
                if (event != null) {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "event",
                        value = event
                    )
                    navController.navigate(
                        EventScreen.Event.route
                    )
                }
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
            if (event != null) {
                event.event?.let {
                    Text(
                        text = it.artist,
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row() {
                Row(
                    horizontalArrangement = Arrangement.Start){
                    if (event != null) {
                        event.event?.let {
                            Text(
                                text = it.name,
                                fontSize = MaterialTheme.typography.h5.fontSize,
                            )
                        }
                    }
                }
                Row(modifier= Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End){
                    if (event != null) {
                        event.event?.let {
                            Text(
                                text = "${it.price} chf",
                                fontSize = MaterialTheme.typography.h5.fontSize,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun Event() {
    MarketContent(navController = rememberNavController())
}