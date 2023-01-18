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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nftticketingapp.ViewModel.AuthViewModel
import com.example.nftticketingapp.ViewModel.UserLoginStatus


@Composable
fun LoginScreen(onLogInSucessful: () -> Unit,
                onSignUpClick: () -> Unit,
                onForgotClick: () -> Unit,
                authViewModel: AuthViewModel = viewModel()
){

    //For Toast messages:
    val loginScreenContext = LocalContext.current

    //Initialize blank username and password
    var username by remember {
        mutableStateOf("pi@hotmail.com")
    }

    var password by remember {
        mutableStateOf("123456")
    }


    // Gets the status of the user connection attempt
    val loginStatus = authViewModel.userLoginStatus.collectAsState()

    var showFailedDialog by remember {

        mutableStateOf(false)
    }

    LaunchedEffect(key1 = loginStatus.value){

        /* Launched effect is a Coroutine composable function used in a composable function
        * to execute part of the code only when the key given as argument is recomposed.*/

        when(loginStatus.value) {
            is UserLoginStatus.StatusFailure -> {

                /* When the user fails to login this boolean like variable is set to true
                * and is then used in an if statement to display an error message*/

                loginScreenContext.showToast("UserStatusFailure: Unable to login")
                showFailedDialog = true

            }
            UserLoginStatus.StatusSucesseful -> {
                loginScreenContext.showToast("UserStatusSucess: Login Successful!")
                onLogInSucessful()

            }
            null -> {

            }
        }

    }

    //The box is gonna take the entire parent size
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

            LoginHeader()
            LoginFields(username = username, password = password,
            onPasswordChange = {
                password = it
            },
            onUsernameChange = {
                username = it
            },
            onForgotPassword = {
            },
                onForgotClick = onForgotClick)
            LoginFooter(onLogInClick = {
            when {
                username.isBlank() -> {

                    loginScreenContext.showToast("Username is blank!")

                }

                password.isBlank() -> {

                    loginScreenContext.showToast("Password is blank!")

                }

                else -> {
                    authViewModel.actionLoginFirebase(username = username, password = password)
                }
            }
                                       },
                onSignUpClick = onSignUpClick)
        }
    }

}

@Composable
fun LoginHeader(){
    Text(text= "Welcome Back", fontSize = 36.sp,
    fontWeight = FontWeight.ExtraBold)

    Text(text = "Sign in to continue",
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun LoginFields(username: String,
                password: String,
                onUsernameChange: (String) -> Unit,
                onPasswordChange: (String) -> Unit,
                onForgotPassword: () -> Unit,
                onForgotClick: () -> Unit){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        NftTicketingFields(value = username, label = "Email Adress", placeholder = "Enter email adress",
            onValueChange = onUsernameChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next))

        Spacer(modifier = Modifier.height(8.dp))

        NftTicketingFields(value = password, label = "Password", placeholder = "Enter password",
            onValueChange = onPasswordChange, visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Go)
        )

        TextButton(onClick = onForgotPassword){
            Text(text = "Forgot password ?", modifier = Modifier.clickable { onForgotClick() })
        }
    }
}

@Composable
fun LoginFooter(
    onLogInClick: () -> Unit,
    onSignUpClick: () -> Unit
){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = onLogInClick,
            modifier = Modifier
                .clickable { onLogInClick() }
                .width(100.dp)){
            Text(text = "Log in")
        }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.weight(5F)) {
                
            }
            TextButton(onClick = onSignUpClick,
                modifier = Modifier
                    .clickable { onSignUpClick() }
                    .weight(2F)) {
                Text(text = "Create Account")
        }


        }
    }
}

@Composable
fun NftTicketingFields(value: String,
                      label: String,
                      placeholder: String,
                      visualTransformation: VisualTransformation = VisualTransformation.None,
                      keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                      leadingIcon: @Composable (() -> Unit)? = null,
                      trailingIcon: (@Composable () -> Unit)? = null,
                      onValueChange: (String) -> Unit){

    //Username and password fields
    OutlinedTextField(value = value,
        onValueChange = onValueChange, 
    label = {
        Text(text = label)
    },
    placeholder = {
        Text(text = placeholder)
    },
    visualTransformation =  visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
 )


}

private fun Context.showToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}



//Cannot be displayed because of ViewModel which is not implemented for Preview of Composable
@Composable
@Preview
fun DisplayScreen(){
    LoginScreen({},
        {},
        {},
        AuthViewModel())
}