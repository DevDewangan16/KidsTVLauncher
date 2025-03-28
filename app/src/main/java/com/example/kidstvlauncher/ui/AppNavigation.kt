package com.example.kidstvlauncher.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val context=LocalContext.current
    val navController = rememberNavController()
    val approvedApps = remember { mutableStateOf(loadApprovedApps(context)) }

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController, approvedApps.value) }
        composable("adminPin") { AdminPinScreen(navController) }
        composable("adminDashboard") { AdminDashboardScreen(navController, approvedApps.value) }
    }
}