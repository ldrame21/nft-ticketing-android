package com.example.nftticketingapp.screens.home.user
// Page pour minter un nouvel évenement

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nftticketingapp.DataClasses.Event
import com.example.nftticketingapp.R
import com.example.nftticketingapp.ViewModel.CreateEventViewModel
import com.example.nftticketingapp.graphs.Graph
import androidx.activity.result.launch

import com.example.nftticketingapp.ui.theme.Purple500

@SuppressLint("UnrememberedMutableState")
@Composable
fun CreateEventContent(navController: NavHostController) {
    val ScreenContext = LocalContext.current

    var new_event_name by remember { mutableStateOf("") }
    var artist by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0.0) }
    var n_tokens by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Login",
            modifier = Modifier
                //.clickable { onLogInSucessful() }
                .fillMaxSize()
                .blur(6.dp),
            contentScale = ContentScale.Crop
        )
        // Transparent white box
        Box(modifier = Modifier
            .fillMaxSize()
            .alpha(0.6f)
            .clip(
                CutCornerShape(
                    topStart = 8.dp,
                    topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 8.dp
                )
            )
            .background(MaterialTheme.colors.background))
        Column(modifier = Modifier.height(1200.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Top, Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top =10.dp, bottom = 15.dp),
                    text = "Create new event",
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Bold
                )
                Box(modifier = Modifier.clickable {
                    /*val imagePicker = registerForActivityResult(
                        ActivityResultContracts.GetContent()) { result: Instrumentation.ActivityResult ->
                        if (result.resultCode == Activity.RESULT_OK) {
                            val imageUri = result.data?.data
                            if (imageUri != null) {
                                //updateImage(imageUri)
                            }
                        }
                    }*/
                    //imagePicker.launch("image/*")

                }
                    .size(170.dp)){

                    Image(
                        painter = painterResource(R.drawable.event),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(170.dp)
                            .clip(RectangleShape)                       // clip to the circle shape
                            .border(2.dp, Color.Gray, RectangleShape)   // add a border (optional)
                    )

                }


                val nametextState = remember { mutableStateOf(TextFieldValue())}
                val artisttextState = remember { mutableStateOf(TextFieldValue())}
                val addresstextState = remember { mutableStateOf(TextFieldValue()) }
                val datetextState = remember { mutableStateOf(TextFieldValue()) }
                val descriptiontextState = remember { mutableStateOf(TextFieldValue()) }
                val pricetextState = remember { mutableStateOf(TextFieldValue()) }
                val n_tokenstextState = remember { mutableStateOf(TextFieldValue()) }


                TextField(
                    value = nametextState.value,
                    singleLine = true,
                    onValueChange = {
                        nametextState.value = it
                        new_event_name = it.text
                    },
                    placeholder = { Text(text = "New event name") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )

                Row() {
                    TextField(
                        modifier = Modifier.width(170.dp),
                        value = n_tokenstextState.value,
                        singleLine = true,
                        onValueChange = {
                            n_tokenstextState.value = it
                            n_tokens = it.text.toInt()
                        },
                        placeholder = { Text(text = "No of tickets") },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    TextField(
                        modifier = Modifier.width(90.dp),
                        value = pricetextState.value,
                        singleLine = true,
                        onValueChange = {
                            pricetextState.value = it
                            price = it.text.toDouble()
                        },
                        placeholder = { Text(text = "0.0 chf") },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent
                        )
                    )
                }
                TextField(
                    singleLine = true,
                    value = artisttextState.value,
                    onValueChange = {
                        artisttextState.value = it
                        artist = it.text
                    },
                    placeholder = { Text(text = "Artist") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
                TextField(
                    singleLine = true,
                    value = addresstextState.value,
                    onValueChange = {
                        addresstextState.value = it
                        address = it.text
                    },
                    placeholder = { Text(text = "Address") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
                TextField(
                    singleLine = true,
                    value = datetextState.value,
                    onValueChange = {
                        datetextState.value = it
                        date = it.text
                    },
                    placeholder = { Text(text = "Date") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
                TextField(
                    maxLines = 6,
                    modifier = Modifier.wrapContentHeight(),
                    value = descriptiontextState.value,
                    onValueChange = {
                        descriptiontextState.value = it
                        description = it.text
                    },
                    placeholder = { Text(text = "Description") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                Arrangement.Bottom, Alignment.CenterHorizontally
            ){
                Spacer(Modifier.fillMaxWidth())
                OutlinedButton(
                    onClick = {
                        val result = if (new_event_name.isBlank()) {
                            ScreenContext.showToast("Missing event name")
                        } else if (price == 0.0) {
                            ScreenContext.showToast("Missing ticket price")
                        } else if (n_tokens == 0) {
                            ScreenContext.showToast("Missing number of tickets")
                        } else if (artist.isBlank()) {
                            ScreenContext.showToast("Missing artist name")
                        } else if (address.isBlank()) {
                            ScreenContext.showToast("Missing address")
                        } else if (date.isBlank()) {
                            ScreenContext.showToast("Missing date")
                        } else if (description.isBlank()) {
                            ScreenContext.showToast("Missing description")
                        } else {
                            ScreenContext.showToast("$new_event_name uploaded")
                            CreateEventViewModel().addEvent(
                                Event(name = new_event_name,
                                    numberOfTickets = n_tokens,
                                    price = price,
                                    artist = artist,
                                    address = address,
                                    date = date,
                                    description = description)
                            )
                            navController.navigate(Graph.HOME)
                        }
                    },
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 80.dp)
                        .height(60.dp)
                        .width(290.dp)
                        .clip(RoundedCornerShape(50)),
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Purple500)
                ) {
                    Text(
                        text = "Mint Event",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }
        }
    }
}


fun MintEvent(
    name: String,
    price: Double,
    n_tokens: Int,
    artist: String,
    address: String,
    date: String,
    description: String
) {
//TODO Déplacer ça dans le viewModel approprié et remplir la fonction
}

@Composable
@Preview(showBackground = true)
fun CreateEventContentPreview() {
    CreateEventContent(navController = rememberNavController())
}

private fun Context.showToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}