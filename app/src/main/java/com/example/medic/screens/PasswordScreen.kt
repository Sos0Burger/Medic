package com.example.medic.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medic.R
import com.example.medic.data.User
import com.example.medic.ui.theme.GrayTextColor
import com.example.medic.ui.theme.PasswordButtonColor
import com.example.medic.ui.theme.SFPDH
import com.example.medic.ui.theme.SkipButtonColor


@Composable
fun PasswordScreen(onNavigateToMain: () -> Unit, onNavigateToPatientCard: () -> Unit) {
    var password by remember { mutableStateOf("") }

    if (password.length == 4) {
        with(User.sharedPrefs.edit()) {
            putString("password", password)
            apply()
        }
        if (User.isCardCreated == false) {
            onNavigateToPatientCard()
        } else onNavigateToMain()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Row() {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (User.isCardCreated == false) {
                        onNavigateToPatientCard()
                    } else onNavigateToMain()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(text = "Пропустить", color = SkipButtonColor, fontWeight = FontWeight(400))
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 70.dp)
        ) {
            Text(
                text = "Создайте пароль",
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 10.dp),
                fontFamily = SFPDH
            )
            Text(
                text = "Для защиты ваших персональных данных",
                fontWeight = FontWeight(400),
                color = GrayTextColor
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(top = 40.dp)
            ) {
                for (i in 0..3) {
                    Image(
                        painter = painterResource(id = if (password.length > i) R.drawable.filledellipse else R.drawable.emptyellipse),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Column(
                modifier = Modifier.padding(top = 50.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                var counter = 0
                for (y in 1..3) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        for (i in 1..3) {
                            counter++
                            Button(
                                onClick = { password += counter },
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(containerColor = PasswordButtonColor),
                                contentPadding = PaddingValues(0.dp),
                                enabled = password.length != 4,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(all = 5.dp)
                            ) {
                                Text(
                                    text = counter.toString(),
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight(600),
                                    fontSize = 24.sp
                                )
                            }
                        }
                    }
                }

                Row() {
                    Button(
                        onClick = {},
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.Transparent),
                        enabled = false,
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .size(80.dp)
                            .padding(all = 5.dp)
                    ) {}

                    Button(
                        onClick = { password += 0 },
                        shape = CircleShape,
                        enabled = password.length != 4,
                        colors = ButtonDefaults.buttonColors(containerColor = PasswordButtonColor),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .size(80.dp)
                            .padding(all = 5.dp)
                    ) {
                        Text(
                            text = "0",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight(600),
                            fontSize = 24.sp
                        )
                    }
                    Button(
                        onClick = { password = password.dropLast(1) },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = PasswordButtonColor),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .size(80.dp)
                            .padding(all = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.deleteicon),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            }

        }
    }

}


@Preview
@Composable
fun PasswordScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        PasswordScreen({}, {})
    }
}