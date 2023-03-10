package com.example.nftticketingapp.screens.home
// Scrolling menu of all of the user's Tickets
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
import com.example.nftticketingapp.ViewModel.MyTicketsViewModel
import com.example.nftticketingapp.graphs.TicketScreen


@Composable
fun MyTicketsContent(
    navController: NavHostController
) {
    val myTicketsViewModel = MyTicketsViewModel()
    val ticketEvent2 = myTicketsViewModel.ticketEventData2.observeAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentAlignment = Alignment.Center

        ) {
            Text(text = "My Tickets",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
            //.padding(10.dp)
        )
        {

            ticketEvent2.value?.forEach { event ->
                item {
                    Ticket(event, navController)
                }
            }
        }
    }
}


@Composable
fun Ticket(
    event2: TicketEvent?,
    navController: NavHostController
) {
    Surface(
        modifier = Modifier
            .clickable {
                if (event2 != null) {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "ticket",
                        value = event2
                    )
                    navController.navigate(
                        TicketScreen.Ticket.route
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
            if (event2 != null) {
                event2.event?.let {
                    Text(
                        text = it.artist,
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            if (event2 != null) {
                event2.event?.let {
                    Text(
                        text = it.name,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TicketPreview() {
    MyTicketsContent(navController = rememberNavController())
}