package com.example.medic.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.medic.AlertUI
import com.example.medic.R
import com.example.medic.api.response.SendCodeResponse
import com.example.medic.api.userApi.UserApiImpl
import com.example.medic.data.User
import com.example.medic.ui.theme.BlueMid
import com.example.medic.ui.theme.BorderColor
import com.example.medic.ui.theme.SFPDSB
import com.example.medic.ui.theme.SecondaryTextColor
import retrofit2.Call
import retrofit2.Response

@Composable
fun RegistrationScreen(onNavigateToEmailCode: () -> Unit) {

    val openDialog = remember { mutableStateOf(false) }

    val title = remember { mutableStateOf("") }
    val text = remember { mutableStateOf("") }

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            AlertUI(title = title.value, text = text.value)
        }
    }

    Column() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 105.dp, start = 20.dp, end = 20.dp)
        ) {
            var email by remember {
                mutableStateOf("")
            }
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.wave),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(text = "Добро пожаловать!")
                }
                Spacer(modifier = Modifier.size(25.dp))
                Text(text = "Войдите, чтобы воспользоваться функциями приложения")
                Spacer(modifier = Modifier.size(64.dp))
                Text("Bход по E-mail", color = SecondaryTextColor)
            }
            Spacer(modifier = Modifier.size(4.dp))
            TextField(
                value = email, onValueChange = {
                    email = it
                },
                isError = !email.matches(Regex("[A-z0-9]+@[A-z0-9]+\\.[a-z]+")),
                shape = MaterialTheme.shapes.medium, colors = TextFieldDefaults.textFieldColors(
                    textColor = SecondaryTextColor,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ), label = {
                    Text("example@example.com", color = SecondaryTextColor)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .border(1.dp, color = BorderColor, shape = MaterialTheme.shapes.medium),
                singleLine = true
            )
            Spacer(modifier = Modifier.size(32.dp))
            Button(
                onClick = {
                    if (email.matches(Regex("[A-z0-9]+@[a-z]+\\.[a-z]+"))) {
                        val userApiImpl = UserApiImpl()
                        val response = userApiImpl.sendCode(email)
                        response.enqueue(object : retrofit2.Callback<SendCodeResponse> {
                            override fun onResponse(
                                call: Call<SendCodeResponse>,
                                response: Response<SendCodeResponse>
                            ) {
                                if (response.code() == 200) {
                                    User.email = email
                                    with(User.sharedPrefs.edit()) {
                                        putString("email", User.email)
                                        apply()
                                    }
                                    onNavigateToEmailCode()
                                }
                                if (response.code() == 422) {
                                    title.value = "Ошибка 422"
                                    text.value = response.message().toString()
                                    openDialog.value = true
                                }
                            }

                            override fun onFailure(call: Call<SendCodeResponse>, t: Throwable) {
                                title.value = "Ошибка сервера"
                                text.value = t.toString()
                                openDialog.value = true
                                Log.d("Error", t.message.toString())
                            }

                        })

                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BlueMid)
            ) {
                Text(
                    text = "Далее",
                    color = Color.White,
                    fontFamily = SFPDSB,
                    fontWeight = FontWeight(600),
                    fontSize = 17.sp
                )
            }
        }
        Spacer(modifier = Modifier.size(100.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = "Или войдите с помощью", color = SecondaryTextColor)
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedButton(
                onClick = { },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 20.dp, end = 20.dp),
                border = BorderStroke(1.dp, BorderColor)
            ) {
                Text(text = "Bойти с Яндекс", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        RegistrationScreen({})
    }
}