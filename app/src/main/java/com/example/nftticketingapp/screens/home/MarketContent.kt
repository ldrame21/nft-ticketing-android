package com.example.nftticketingapp.screens.home
// MarketPlace with Scrolling menu of all available events

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MarketContent(
    onClick: () -> Unit,
) {
    val list = listOf(
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
        ))
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(){ item ->
        }
    }
}

@Composable
fun Event(
    event: Event
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
            .height(120.dp)
            .padding(1.dp)
            .padding(0.5.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape= RectangleShape)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
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
    MarketContent(onClick = {})
}