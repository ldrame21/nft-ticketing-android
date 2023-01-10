package com.example.nftticketingapp.screens.home.mytickets
// Display a Ticket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TicketContent(
    name: String,
    artist: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = MaterialTheme.typography.h1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = artist,
            fontSize = MaterialTheme.typography.h2.fontSize,
        )

    }
}

@Composable
@Preview(showBackground = true)
fun TicketContentPreview() {
    TicketContent("fiesta", "Bob")
}