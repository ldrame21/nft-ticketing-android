package com.example.nftticketingapp.screens.home

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth()
            .height(90.dp),
            color = MaterialTheme.colors.primary
        ){
            Text(
                modifier = Modifier.clickable { onClick() }
                    .wrapContentHeight(),
                text = "MarketPlace",
                fontSize = MaterialTheme.typography.h3.fontSize,
                textAlign = TextAlign.Center

            )
        }

        Event(
            artist = "Bebou",
            name = "Super Concert"
        )
        Event(
            artist = "Passe-Partout",
            name = "Fort Boyard"
        )
        Event(
            artist = "Amadou et Mariam",
            name = "Bon Dimanche à Bamako"
        )
    }
}

@Composable
fun Event(
    artist: String,
    name: String
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
                text = artist,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = name,
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