package com.example.nftticketingapp.ViewModel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.nftticketingapp.R
import com.google.android.gms.wearable.Asset
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.PutDataRequest
import com.google.android.gms.wearable.Wearable
import java.io.ByteArrayOutputStream
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nftticketingapp.graphs.HomeNavGraph
import com.example.nftticketingapp.screens.home.BottomBar


@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
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
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        HomeNavGraph(navController = navController)
    }
}
private fun createAssetFromBitmap(bitmap: Bitmap): Asset =
    ByteArrayOutputStream().let { byteStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
        Asset.createFromBytes(byteStream.toByteArray())
    }


