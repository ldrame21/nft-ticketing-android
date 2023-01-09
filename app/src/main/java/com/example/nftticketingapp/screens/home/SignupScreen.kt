package com.example.nftticketingapp.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nftticketingapp.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nftticketingapp.ViewModel.AuthViewModel
import com.example.nftticketingapp.ViewModel.UserSignupStatus


@Composable
fun SignupScreen(onSignUpSuccesful: () -> Unit,
                onSignUpClick: () -> Unit,
                authViewModel: AuthViewModel = viewModel()
){

    //For Toast messages:
    val signupScreenContext = LocalContext.current

    //Initialize blank username and password
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }


    // Gets the status of the user connection attempt
    val signupStatus = authViewModel.userSignupStatus.collectAsState()

    var showFailedDialog by remember {

        mutableStateOf(false)
    }

    LaunchedEffect(key1 = signupStatus.value){

        /* Launched effect is a Coroutine composable function used in a composable function
        * to execute part of the code only when the key given as argument is recomposed.*/

        when(signupStatus.value) {
            is UserSignupStatus.StatusFailure -> {

                /* When the user fails to login this boolean like variable is set to true
                * and is then used in an if statement to display an error message*/

                signupScreenContext.showToast("UserStatusFailure: Unable to Sign up")
                showFailedDialog = true

            }
            UserSignupStatus.StatusSucesseful -> {
                signupScreenContext.showToast("UserStatusSucess: Sign Up Successful!")
                //Navigates back to the login screen
                onSignUpSuccesful()

            }
            null -> {

            }
        }

    }

    //The box is gonna take the entire parent size
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Sign Up",
            modifier = Modifier
                //.clickable { onLogInSucessful() }
                .fillMaxSize()
                .blur(6.dp),
            contentScale = ContentScale.Crop
        )
        // Transparent white box
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .alpha(0.6f)
            .clip(
                CutCornerShape(
                    topStart = 8.dp,
                    topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 8.dp
                )
            )
            .background(MaterialTheme.colors.background))

        Column(
            Modifier
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround){

            SignupHeader()
            SignupFields(username = username, password = password,
                onPasswordChange = {
                    password = it
                },
                onUsernameChange = {
                    username = it
                })
            SignupFooter(onLogInClick = {
                when {
                    username.isBlank() -> {

                        signupScreenContext.showToast("Username is blank!")

                    }

                    password.isBlank() -> {

                        signupScreenContext.showToast("Password is blank!")

                    }

                    else -> {
                        authViewModel.actionSignUpFirebase(username = username, password = password)
                    }
                }
            },
                onSignUpClick = onSignUpClick)
        }
    }

}

@Composable
fun SignupHeader(){
    Text(text= "Create account", fontSize = 36.sp,
        fontWeight = FontWeight.ExtraBold)

    Text(text = "Enter your email address and a password",
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun SignupFields(username: String,
                password: String,
                onUsernameChange: (String) -> Unit,
                onPasswordChange: (String) -> Unit){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        NftTicketingFields(value = username, label = "Email Adress", placeholder = "Enter email adress",
            onValueChange = onUsernameChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next))

        Spacer(modifier = Modifier.height(8.dp))

        NftTicketingFields(value = password, label = "Password", placeholder = "Enter password",
            onValueChange = onPasswordChange, visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Go)
        )

    }
}

@Composable
fun SignupFooter(
    onLogInClick: () -> Unit,
    onSignUpClick: () -> Unit
){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = onLogInClick,
            modifier = Modifier
                .clickable { onLogInClick() }
                .width(100.dp)){
            Text(text = "Sign Up")
        }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.weight(5F)) {

            }
            TextButton(onClick = onSignUpClick,
                modifier = Modifier
                    .clickable { onSignUpClick() }
                    .weight(2F)) {
                Text(text = "Back to login")
            }


        }
    }
}


private fun Context.showToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}



//Cannot be displayed because of ViewModel which is not implemented for Preview of Composable
@Composable
@Preview
fun DisplaySignupScreen(){
    SignupScreen({},
        {})
}