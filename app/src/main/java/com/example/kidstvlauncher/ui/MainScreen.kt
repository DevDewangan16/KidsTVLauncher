package com.example.kidstvlauncher.ui
import coil.compose.rememberAsyncImagePainter

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController, approvedApps: MutableSet<String>) {
    val context = LocalContext.current
    val pm = context.packageManager
    val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF3E0)) // Light background
    ) {
        Text(
            text = "Kids TV Launcher",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50), // Green Heading
            modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    navController.navigate("adminPin")
                }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 columns for app grid
            modifier = Modifier
                .padding(12.dp)
                .weight(1f)
        ) {
            items(packages.filter { approvedApps.contains(it.packageName) }) { app ->
                AppCard(app, pm)
            }
        }

//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp)
//                .padding(8.dp)
//        ) {
//            // Hidden Admin Button placed at bottom-right corner
//            Box(
//                modifier = Modifier
//                    .size(24.dp) // Small invisible button
//                    .align(Alignment.CenterStart)
//                    .clickable(onClick = { navController.navigate("adminPin") })
//                    .alpha(0f) // Invisible but clickable
//            ) {
//                // Optional placeholder (can be removed if not needed)
//                Text(
//                    text = "Hidden",
//                    fontSize = 1.sp,
//                    color = Color.Transparent
//                )
//            }
//        }
    }
}

@Composable
fun AppCard(app: ApplicationInfo, pm: PackageManager) {
    val appIcon = remember { pm.getApplicationIcon(app) }
    val context= LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                val launchIntent = pm.getLaunchIntentForPackage(app.packageName)
                context.startActivity(launchIntent)
            },
        elevation =CardDefaults.elevatedCardElevation(6.dp),
        colors =CardDefaults.cardColors(
            containerColor =  Color(0xFFF0F4C3)
        )// Light Green Cards
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(appIcon),
                contentDescription = app.packageName,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = app.loadLabel(pm).toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4E342E)
            )
        }
    }
}
