package com.example.nftticketingapp.screens.home.mytickets
// Display a Ticket

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.nftticketingapp.DataClasses.TicketEvent
import com.example.nftticketingapp.R
import com.example.nftticketingapp.ViewModel.DisplayWatchViewModel
import com.example.nftticketingapp.ViewModel.SellTicketViewModel
import com.example.nftticketingapp.graphs.Graph
import com.example.nftticketingapp.ui.theme.Purple500
import com.google.android.gms.tasks.Task
import com.google.android.gms.wearable.*
import java.io.ByteArrayOutputStream

var isDialogShown by mutableStateOf(false)
    private set
fun onSellTicketClick(){
    isDialogShown = true
}
fun onDismissDialog(){
    isDialogShown = false
}

@Composable
fun DisplayTicket() {
    val dataClient: DataClient = Wearable.getDataClient(LocalContext.current)
    val bitmap = BitmapFactory.decodeResource(
        LocalContext.current.resources,
        R.drawable.user
    ).asImageBitmap()
    val asset: Asset = BitmapFactory.decodeResource(
        LocalContext.current.resources, R.drawable.user
    ).let { bitmap ->
        createAssetFromBitmap(bitmap)
    }
    val request: PutDataRequest = PutDataRequest.create("/image").apply {
        putAsset("profileImage", asset)
    }
    val putTask: Task<DataItem> = Wearable.getDataClient(LocalContext.current).putDataItem(request)
}

@Composable
fun TicketContent(
    ticketEvent: TicketEvent?,
    navController: NavHostController
) {
    var displayWatchViewModel = DisplayWatchViewModel()
    var context = LocalContext.current
    DisplayTicket()


    Box(modifier = Modifier.fillMaxSize()) {
        var bitmap = ImageBitmap.imageResource(id = R.drawable.user).asAndroidBitmap()
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

                    if (ticketEvent != null) {
                        ticketEvent.event?.let {
                            Text(
                                text = it.name,
                                fontSize = MaterialTheme.typography.h4.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }

                    if (ticketEvent != null) {
                        ticketEvent.event?.let {
                            Text(
                                text = it.artist,
                                fontSize = MaterialTheme.typography.h4.fontSize,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    Arrangement.Top,
                    Alignment.Start)
                {
                    if (ticketEvent != null) {
                        ticketEvent.event?.let {
                            Text(
                                fontSize = MaterialTheme.typography.h5.fontSize,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                text = it.description
                            )
                        }
                    }

                    Row( modifier = Modifier.padding(top =30.dp)
                    ) {

                        if (ticketEvent != null) {
                            ticketEvent.event?.let {
                                Text(
                                    text =  it.date,
                                    fontSize = MaterialTheme.typography.h5.fontSize,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(50.dp))
                        if (ticketEvent != null) {
                            ticketEvent.event?.let {
                                Text(
                                    text =  it.address,
                                    fontSize = MaterialTheme.typography.h5.fontSize,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Transaction list :",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    val sortedTransactions = ticketEvent?.ticket?.transactions?.toList()?.
                    sortedBy { (_, transaction) -> transaction?.time }?.toMap()



                    sortedTransactions?.forEach {
                        Text(
                            text = "From : ",
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            lineHeight = 10.sp
                        )
                        Text(
                            text = "${it.value?.from}",
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            lineHeight = 10.sp
                        )
                        Text(
                            text = "To : ",
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            lineHeight = 10.sp
                        )
                        Text(
                            text = "${it.value?.to}",
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            lineHeight = 10.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedButton(
                    onClick = {
                        val dataClient: DataClient = Wearable.getDataClient(context)
                        displayWatchViewModel.sendDataToWear(context, dataClient, bitmap)
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .width(290.dp)
                        .clip(RoundedCornerShape(50)),
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Purple500)
                ) {
                    Text(
                        text = "Display on Watch",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))

                OutlinedButton(
                    onClick = { onSellTicketClick()},
                    modifier = Modifier
                        .height(60.dp)
                        .width(290.dp)
                        .clip(RoundedCornerShape(50))
                        .border(2.dp, Purple500, RoundedCornerShape(50)),
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent)
                ) {
                    Text(
                        text = "Sell Ticket",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Purple500,
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
    if(isDialogShown){
        CustomDialog(
            onDismiss = {
                onDismissDialog()
            },
            ticketEvent = ticketEvent,
            navController = navController
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    onDismiss:()->Unit,
    ticketEvent: TicketEvent?,
    navController: NavHostController
) {
    val context = LocalContext.current
    val sellTicketViewModel = SellTicketViewModel()
    var ticket_price: Double = 0.0
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(2.dp, color = Color.LightGray, shape = RoundedCornerShape(15.dp))
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ){

                Text(
                    text = "Set the price you want to sell your ticket for :",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        val textState = remember { mutableStateOf(TextFieldValue()) }
                        TextField(
                            value = textState.value,
                            onValueChange = { textState.value = it
                                ticket_price = it.text.toDouble()},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(
                        onClick = {
                            onDismissDialog()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple500,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                        ,
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(
                        onClick = {
                            if (ticketEvent != null) {
                                ticketEvent.event?.uid?.let {
                                    ticketEvent.ticket?.uid?.let { it1 ->
                                        sellTicketViewModel.sellTicket(
                                            ticketID = it1,
                                            eventID = it,
                                            price = ticket_price,
                                        context = context)
                                    }
                                }
                                navController.navigate(
                                    Graph.HOME
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple500,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Sell",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

            }
        }
    }
}

private fun createAssetFromBitmap(bitmap: Bitmap): Asset =
    ByteArrayOutputStream().let { byteStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
        Asset.createFromBytes(byteStream.toByteArray())
    }