package com.example.nftticketingapp.screens.home.market
// Display an Event

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nftticketingapp.R
import com.example.nftticketingapp.ui.theme.Purple500


fun BuyTicket() {
    // TODO do something
}

@Composable
fun EventContent(
    name: String,
    artist: String
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Login",
            modifier = Modifier
                //.clickable { onLogInSucessful() }
                .fillMaxSize()
                .blur(6.dp),
            contentScale = ContentScale.Crop
        )
        // Transparent white box
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.6f)
                .clip(
                    CutCornerShape(
                        topStart = 8.dp,
                        topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 8.dp
                    )
                )
                .background(MaterialTheme.colors.background)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            item {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    Arrangement.Top,
                    Alignment.CenterHorizontally)
                {
                    Image(
                        painter = painterResource(R.drawable.event),
                        contentDescription = "event",
                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                        modifier = Modifier
                            .padding(40.dp)
                            .size(150.dp)
                            .clip(RectangleShape)                       // clip to the circle shape
                            .border(2.dp, Color.Gray, RectangleShape)
                    )//add a border (optional)
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = name,
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = artist,
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    Arrangement.Top,
                    Alignment.Start)
                {
                    Text(
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray,
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor."
                    )

                    Row( modifier = Modifier.padding(top =40.dp)
                    ) {

                        Text(
                            text = "18.01.2023",
                            fontSize = MaterialTheme.typography.h5.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = "EPFL",
                            fontSize = MaterialTheme.typography.h5.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Price : 60 CHF",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                OutlinedButton(
                    onClick = { BuyTicket()},
                    modifier = Modifier
                        .height(60.dp)
                        .width(290.dp)
                        .clip(RoundedCornerShape(50)),
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Purple500)
                ) {
                    Text(
                        text = "Buy Ticket",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun EventContentPreview() {
    EventContent("Super Concert", "Kendrick Lamar")
}