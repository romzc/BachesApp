package com.romzc.bachesapp.ui.composables

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.romzc.bachesapp.data.User
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 * Datos de un bache:
 *  - Foto del lugar
 *  - Titulo
 *  - Descripcion
 *  - Latitud y longitud
 *  - Fecha
 */

@Composable
fun BacheForm(
    user: User,
    saveData: () -> Unit,
    isCameraGranted: Boolean
) {

    val title = remember { mutableStateOf("")}
    val description = remember { mutableStateOf("") }
    val isCameraPermissionGranted = remember { mutableStateOf(isCameraGranted) }
    val capturedImage = remember { mutableStateOf<Bitmap?>(null) }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(10.dp)
    ) {

        ImageWithDefault(capturedImage.value, contentDescription = "Text" )

        if (isCameraPermissionGranted.value) {
            CameraButton(onImageCaptured = { capturedImage.value = it })
        }
        else {
            Button(
                onClick = { Log.i("APP", "Acceso a camara") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Permitir acceso a la cámara")
            }
        }

        CommonTextField(
            title = "Titulo",
            placeholder = "Bache en...",
            text = title.value,
            onValueChange = { title.value = it }
        )

        CommonTextField(
            title = "Descripción",
            placeholder = "En la av...",
            text = description.value,
            onValueChange = { description.value = it }
        )

        Row( Modifier.padding(top = 10.dp)) {
            CommonButton(text = "Guardar", onClick = {})
            Spacer(modifier = Modifier.width(20.dp))
            CommonButton(text = "Cancelar", onClick = {})
        }
    }
}


fun createCameraIntent(context: Context): Pair< Intent, Uri> {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    val photoUri = createPhotoUri(context)
    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
    return intent to photoUri
}

fun createPhotoUri(context: Context): Uri {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "IMG_$timeStamp.jpg"
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val file = File(storageDir, fileName)
    return FileProvider.getUriForFile(context, context.packageName + ".provider", file)
}