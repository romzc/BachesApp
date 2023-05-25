package com.romzc.bachesapp.navigation

sealed class Routes(val route: String) {
    object ReportsList : Routes("Lista Reportes")
    object Edit : Routes("Editar")
}
