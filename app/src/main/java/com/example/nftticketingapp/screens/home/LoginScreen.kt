package com.example.nftticketingapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(){

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    //The box is gonna take the entire parent size
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.login_screen),
            contentDescription = "Login",
            modifier = Modifier
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
            })
            LoginFooter(onSignInClick = {}, onSignUpClick = {})
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
                onForgotPassword: () -> Unit){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        NftTicketingFields(value = username, label = "Username", placeholder = "Enter email adress",
            onValueChange = onUsernameChange)

        Spacer(modifier = Modifier.height(8.dp))

        NftTicketingFields(value = password, label = "Password", placeholder = "Enter password",
            onValueChange = onPasswordChange, visualTransformation = PasswordVisualTransformation())

        TextButton(onClick = onForgotPassword){
            Text(text = "Forgot password ?")
        }
    }
}

@Composable
fun LoginFooter(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = onSignInClick, modifier = Modifier.width(100.dp)){
            Text(text = "Sign in")
        }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.weight(5F)) {
                
            }
            TextButton(onClick = onSignUpClick, modifier = Modifier.weight(2F)) {
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








@Composable
@Preview
fun DisplayScreen(){
    LoginScreen()
}