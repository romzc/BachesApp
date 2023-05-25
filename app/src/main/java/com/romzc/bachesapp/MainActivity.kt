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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.romzc.bachesapp.data.User
import com.romzc.bachesapp.navigation.Routes
import com.romzc.bachesapp.ui.composables.ScreenReportRegister
import com.romzc.bachesapp.ui.screens.ScreenReportList
import com.romzc.bachesapp.ui.screens.ScreenUserLogin
import com.romzc.bachesapp.ui.screens.ScreenUserRegister

class MainActivity : ComponentActivity() {

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> if (isGranted) shouldShowCamera.value = true }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Routes.UserLogin.route
            ) {
               composable(Routes.ReportList.route) {
                   ScreenReportList(navController = navController)
               }
                composable(Routes.ReportRegister.route) {
                    ScreenReportRegister(
                        navController = navController,
                        userId = 1,
                        saveData = { /*TODO*/ },
                        isCameraGranted = shouldShowCamera.value
                    )
                }

                composable(Routes.UserLogin.route) {
                    ScreenUserLogin(naveController = navController)
                }

                composable(Routes.UserRegister.route) {
                    ScreenUserRegister(naveController = navController)
                }
            }
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