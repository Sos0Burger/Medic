package com.example.medic.onboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medic.R
import com.example.medic.ui.theme.GrayTextColor
import com.example.medic.ui.theme.GreenTextColor
import com.example.medic.ui.theme.MedicTheme
import com.example.medic.ui.theme.skipButtonColor

@Composable
fun OnboardScreen(leftUpperText: String, greenText: String, dotPosition: Int, illustrationId: Int) {
    Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally ,modifier = Modifier.fillMaxSize()) {

        Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Blue
                )
            ) {
                Text(text = leftUpperText, fontSize = 20.sp, color = skipButtonColor)
            }
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.rectangleplus),
                    contentDescription = null,
                    modifier = Modifier.size(165.dp, 165.dp)
                )
            }

        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = greenText, color = GreenTextColor, fontSize = 20.sp, modifier =Modifier.padding(bottom = 30.dp))
            Text(
                text = stringResource(R.string.analizes_onboard_gray_text),
                fontSize = 14.sp,
                color = GrayTextColor
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(all = 50.dp)
            ) {
                for (i in 1..3) {
                    Image(
                        painter = painterResource(id = if (dotPosition == i) R.drawable.filledellipse else R.drawable.emptyellipse),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = illustrationId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(360.dp)
                        .padding(bottom = 50.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreen() {
    MedicTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            OnboardScreen("Пропустить", "Анализы", 1, R.drawable.analizes_illustration)
        }
    }
}