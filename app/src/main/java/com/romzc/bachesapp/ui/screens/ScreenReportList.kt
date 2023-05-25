package com.romzc.bachesapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import com.romzc.bachesapp.R
import com.romzc.bachesapp.navigation.Routes
import com.romzc.bachesapp.ui.composables.CustomNavBar
import com.romzc.bachesapp.viewmodel.PotholeViewModel
import com.romzc.bachesapp.viewmodel.UserViewModel

@Composable
fun ScreenReportList(navController: NavController){
    /**************/
    val mPotholeViewModel: PotholeViewModel = ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(PotholeViewModel::class.java)

    CustomNavBar(navController = navController, title = "Reporte")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 60.dp, 10.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        for (i in 1..10) {
            item {
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .border(
                            border = BorderStroke(2.dp, Color.LightGray)
                        )
                ) {ItemReport(i, navController)}
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {navController.navigate(Routes.ReportRegister.route)},
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = "añadir"
            )
        }
    }
}

@Composable
fun ItemReport (i:Int, navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_baseline_report_24),
            contentDescription = "Image description",
            modifier = Modifier
                .padding(10.dp, 0.dp)
                .size(100.dp)
        )
        Column {
            Text(
                modifier = Modifier
                    .width(100.dp)
                    .padding(0.dp, 10.dp),
                text = "Reporte: $i"
            )
            val t1 = "Descripciónszlknzflksndfoljawssndfl lsdfnmaiksndfq fdwpofjwoainfonwasddlofnasolñdnfasldofn"
            val t2 = "Ddwpofjwoainfonwasddlofnasolñdnfasldofn"
            var t3 = ""
            if (i%2 ==0){
                t3 = t2
            }else{
                t3 = t1
            }
            Text(
                modifier = Modifier
                    .width(150.dp)
                    .padding(0.dp, 10.dp),
                text = t3
            )
        }
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {navController.navigate(Routes.ReportEdit.route)}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit_24),
                    contentDescription = "editar",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp)
                )
            }
            IconButton(
                onClick = { navController.navigate(Routes.ReportEdit.route)}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete_24),
                    contentDescription = "Borrar",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(45.dp)
                )
            }
        }
    }
}

