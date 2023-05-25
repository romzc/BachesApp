package com.romzc.bachesapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.romzc.bachesapp.data.User
import com.romzc.bachesapp.ui.composables.BacheForm

class MainActivity : ComponentActivity() {


    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> if (isGranted) shouldShowCamera.value = true }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestCameraPermission()
        setContent {
            BacheForm(
                User(1, "Rony", "Ventura", "@", "password"),
                saveData = { Log.i("APP", "Save information") },
                isCameraGranted = shouldShowCamera.value
            )
        }
        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("APP", "Permission previously granted")
                shouldShowCamera.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.i("APP", "Show camera permission dialog")

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}