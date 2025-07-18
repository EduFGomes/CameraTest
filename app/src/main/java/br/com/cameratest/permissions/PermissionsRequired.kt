package br.com.cameratest.permissions

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext

// Toda função composable tem um modifier, porque aquela função composable pode
// ser invocada a partir de outra que já mexeu no layout da tela e a
// nova função deve modificar os valores ajustadas previamente.

@Composable
fun RequestPermissionScreen(
    modifier: Modifier = Modifier,
    permission: String,
    permissionActionLabel: String,
    onPermissionGranted: () -> Unit
){
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ){ granted ->
        if(granted){
            onPermissionGranted()
        }
    }

    Box(modifier = modifier.fillMaxSize()){
        Button(
            modifier = modifier.align(Alignment.Center),
            onClick = {
                launcher.launch(permission)
            }
        ) {
            Text(text = permissionActionLabel)
        }
    }
}

@Composable
fun WithPermission(
    modifier: Modifier = Modifier,
    permission: String,
    permissionActionLabel: String,
    content: @Composable () -> Unit
){
    // obtendo o contexto do app
    val context = LocalContext.current

    var permissionGranted by remember{ mutableStateOf(context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) }

    if(!permissionGranted){
        RequestPermissionScreen(
            modifier = modifier,
            permission = permission,
            permissionActionLabel = permissionActionLabel
        ) {
            permissionGranted = true
        }
    } else {
        Surface(modifier = modifier){
            content()
        }
    }
}