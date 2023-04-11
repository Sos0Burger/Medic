package com.example.medic.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medic.R
import com.example.medic.ui.theme.Blue
import com.example.medic.ui.theme.LightBlue
import com.example.medic.ui.theme.MedicTheme
import com.example.medic.ui.theme.gradientBackground

@Composable
fun GradientScreen() {
    val listColors =
        listOf(
            LightBlue, Blue, LightBlue
        )
    Box(
        modifier = Modifier
            .gradientBackground(listColors, 45f)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 56.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.smartlab), contentDescription = null)
            Spacer(modifier = Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.smartlab_icon),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    MedicTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            GradientScreen()
        }
    }
}