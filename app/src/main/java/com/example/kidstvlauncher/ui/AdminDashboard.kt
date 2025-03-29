package com.example.kidstvlauncher.ui
import coil.compose.rememberAsyncImagePainter

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AdminDashboardScreen(navController: NavController, approvedApps: MutableSet<String>) {
    val context = LocalContext.current
    val pm = context.packageManager
    val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Blue background
    ) {
        Text(
            text = "Admin Dashboard",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1565C0),
            modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn(modifier = Modifier.padding(12.dp)) {
            items(packages) { app ->
                AdminAppItem(app, pm, approvedApps)
            }
        }

        Button(
            onClick = {
                saveApprovedApps(context, approvedApps)
                Toast.makeText(context, "Changes Saved!", Toast.LENGTH_SHORT).show()
                navController.navigate("main")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF43A047) // Green Button
            )
        ) {
            Text("Save & Exit", color = Color.White, fontSize = 18.sp)
        }
    }
}
@Composable
fun AdminAppItem(app: ApplicationInfo, pm: PackageManager, approvedApps: MutableSet<String>) {
    val appIcon = remember {
        pm.getApplicationIcon(app)
    }
    var isChecked by remember { mutableStateOf(approvedApps.contains(app.packageName)) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEDEF6)
        ) // Light Yellow
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(appIcon),
                contentDescription = app.packageName,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = app.loadLabel(pm).toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    if (isChecked) {
                        approvedApps.add(app.packageName)
                    } else {
                        approvedApps.remove(app.packageName)
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,     // Thumb when ON
                    uncheckedThumbColor = Color.White,   // Thumb when OFF
                    checkedTrackColor = Color.Black,     // Track when ON
                    uncheckedTrackColor = Color.Black,    // Track when OFF
                    checkedBorderColor = Color.White,
                    uncheckedBorderColor = Color.White
                )
            )
        }
    }
}
fun saveApprovedApps(context: Context, approvedApps: Set<String>) {
    val sharedPrefs = context.getSharedPreferences("KidsTVPrefs", Context.MODE_PRIVATE)
    sharedPrefs.edit().putStringSet("approved_apps", approvedApps).apply()
}

fun loadApprovedApps(context: Context): MutableSet<String> {
    val sharedPrefs = context.getSharedPreferences("KidsTVPrefs", Context.MODE_PRIVATE)
    return sharedPrefs.getStringSet("approved_apps", mutableSetOf()) ?: mutableSetOf()
}

