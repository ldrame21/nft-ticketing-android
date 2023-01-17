@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.nftticketingapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.nftticketingapp.R
import com.example.nftticketingapp.ui.theme.Purple500
import com.example.nftticketingapp.ViewModel.UserViewModel
import com.example.nftticketingapp.graphs.Graph




@Composable
fun UserContent(
    username: String,
    viewModel: UserViewModel,
    navController: NavHostController
) {

    var username: String? = null

    //val user = remember { viewModel.getUserData().value }
    //var user by viewModel.getUserData().observeAsState()
    /*viewModel.userData.observe(LocalLifecycleOwner.current,Observer {

        username = it.username
        uid = it.uid
        blnc = it.balance

    })*/

    val user = viewModel.userData.observeAsState()



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
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Top, Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.user),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .padding(40.dp)
                        .size(250.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
                )
                if (user.value?.username != null) {

                    Text(
                        text = user.value?.username!!,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                } else{
                    Text(
                        text = "Username is null",
                        fontSize = MaterialTheme.typography.h3.fontSize,
                        fontWeight = FontWeight.Bold)
                }
                /*Text(
                    text = "Username is null",
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold)*/
            }
            Spacer(modifier = Modifier.height(60.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Top
            )
            {
                Text(modifier = Modifier.padding(horizontal = 40.dp),
                    text = "Credit : "
                            + "%.2f".format(user.value!!.balance)
                            + " CHF",
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Normal,
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Top,
                Alignment.CenterHorizontally
            ) {
                OutlinedButton(onClick = { viewModel.onLoadWalletClick() },
                modifier = Modifier
                    .height(60.dp)
                    .width(290.dp)
                    .clip(RoundedCornerShape(50)),
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Purple500)
                ) {
                    Text(text = "Reload",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedButton(onClick = { navController.navigate(Graph.USER)},
                    modifier = Modifier
                        .height(60.dp)
                        .width(290.dp)
                        .clip(RoundedCornerShape(50)),
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Purple500)
                ) {
                    Text(text = "Mint Event",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }
        }
    }
    if(viewModel.isDialogShown){
        CustomDialog(
            viewModel = viewModel,
            onDismiss = {
                viewModel.onDismissDialog()
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    viewModel: UserViewModel,
    onDismiss:()->Unit,
) {
    var new_balance: Double = 0.0
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
                    text = "Choose the amount you want to load on your wallet",
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
                                new_balance = it.text.toDouble()},
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
                            onDismiss()
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
                            viewModel.addBalance(new_balance)
                            viewModel.onDismissDialog()
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
                            text = "Confirm",
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

@Composable
@Preview(showBackground = true)
fun UserContentPreview() {
    CustomDialog(viewModel = UserViewModel(), onDismiss = {})
}