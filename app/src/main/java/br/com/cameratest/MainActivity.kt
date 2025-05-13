package br.com.cameratest

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.cameratest.permissions.WithPermission
import br.com.cameratest.ui.theme.CameraTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CameraTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    WithPermission(
                        modifier = Modifier.padding(innerPadding),
                        permission = Manifest.permission.CAMERA,
                        permissionActionLabel = "Permitir uso da câmera"
                    ) {
                        CameraAppTirarFoto()
                    }
                }
            }
        }
    }
}

@Composable
fun CameraAppTirarFoto(){
    Text("Câmera aberta...")
}