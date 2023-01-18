package com.example.nftticketingapp.ViewModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataItem
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.PutDataRequest
import java.io.ByteArrayOutputStream


class DisplayWatchViewModel: ViewModel() {

    var imageUri: Uri?

    init {
        imageUri = null
    }

    fun sendDataToWear(context: Context?, dataClient: DataClient, bitmap: Bitmap) {

        val matrix = Matrix()

        //var imageBitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, imageUri)
        var imageBitmap= bitmap
        val ratio: Float = 13F

        val imageBitmapScaled = Bitmap.createScaledBitmap(
            imageBitmap,
            (imageBitmap.width / ratio).toInt(),
            (imageBitmap.height / ratio).toInt(),
            false
        )
        imageBitmap = Bitmap.createBitmap(
            imageBitmapScaled,
            0,
            0,
            (imageBitmap.width / ratio).toInt(),
            (imageBitmap.height / ratio).toInt(),
            matrix,
            true
        )

        val stream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val imageByteArray = stream.toByteArray()

        val request: PutDataRequest = PutDataMapRequest.create("/userInfo").run {
            dataMap.putByteArray("profileImage", imageByteArray)
            asPutDataRequest()
        }

        request.setUrgent()
        val putTask: Task<DataItem> = dataClient.putDataItem(request)
    }
}