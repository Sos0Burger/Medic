package com.example.medic.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medic.api.response.SignInResponse
import com.example.medic.api.userApi.UserApiImpl
import com.example.medic.data.User
import com.example.medic.ui.theme.*
import retrofit2.Call
import retrofit2.Response

@Composable
fun EmailCodeScreen(onNavigateToRegistration: ()-> Unit, onNavigateToPassword: ()-> Unit) {

    val openDialog = remember { mutableStateOf(false) }

    val title = remember { mutableStateOf("") }
    val text = remember { mutableStateOf("") }

    val emailCode = arrayOf(remember { mutableStateOf("") },
        remember { mutableStateOf("") },
        remember { mutableStateOf("") },
        remember { mutableStateOf("") })
    Column() {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(top = 20.dp, start = 20.dp)
        ) {
            Button(
                onClick = { onNavigateToRegistration() },
                shape = RoundedCornerShape(30),
                modifier = Modifier.size(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PasswordButtonColor,
                    contentColor = ArrowColor
                )
            ) {
                Text(text = "<", color = ArrowColor, fontSize = 16.sp)
            }
        }
        Spacer(modifier = Modifier.size(200.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Введите код из E-mail",
                fontWeight = FontWeight(600),
                fontSize = 17.sp,
                fontFamily = SFPDSB
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 0..3)
                    TextField(
                        value = emailCode[i].value,
                        onValueChange = {
                            emailCode[i].value = it
                            if (i == 3 && emailCode[3].value != "") {

                                var i = ""
                                for (value in emailCode){
                                    i += value.value
                                }

                                val userApiImpl = UserApiImpl()
                                val response = userApiImpl.signIn(User.email, i)
                                response.enqueue(object: retrofit2.Callback<SignInResponse>{
                                    override fun onResponse(
                                        call: Call<SignInResponse>,
                                        response: Response<SignInResponse>
                                    ) {
                                        if(response.code()==200){
                                            User.token = response.body()?.token
                                            Log.d("token", User.token.toString())
                                            with(User.sharedPrefs.edit()) {
                                                putString("token", User.token)
                                                apply()
                                            }
                                            onNavigateToPassword()
                                        }
                                        if(response.code()==422){
                                            title.value = "Ошибка 422"
                                            text.value = response.errorBody().toString()
                                            openDialog.value = true
                                        }
                                    }

                                    override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                                        title.value = "Ошибка сервера"
                                        text.value = t.toString()
                                        openDialog.value = true
                                    }

                                })

                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        shape = RoundedCornerShape(30),
                        modifier = Modifier
                            .width(50.dp)
                            .padding(top = 24.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
            }

        }
    }
}

@Preview
@Composable
fun EmailCodeScreenPreview() {
    MedicTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            EmailCodeScreen({},{})
        }
    }
}