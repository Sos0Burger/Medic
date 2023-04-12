package com.example.medic.screens

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.medic.AlertUI
import com.example.medic.R
import com.example.medic.api.profileApi.ProfileApiImpl
import com.example.medic.api.response.CreateProfileResponse
import com.example.medic.data.Profile
import com.example.medic.data.User
import com.example.medic.ui.theme.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PatientCardScreen(onNavigateToMain: () -> Unit) {
    val firstname = remember { mutableStateOf("") }
    val middlename = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val birthday = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf("") }

    val state = rememberScrollState()

    val patientCard = arrayOf(firstname, middlename, lastname, birthday, gender)

    val labels = arrayOf("Имя", "Отчество", "Фамилия", "День рождения", "Пол")

    val openDialog = remember { mutableStateOf(false) }
    val title = remember { mutableStateOf("") }
    val text = remember { mutableStateOf("") }

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            AlertUI(title = title.value, text = text.value)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 20.dp).verticalScroll(state)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Text(
                text = "Создание карты \nпациента",
                fontWeight = FontWeight(700),
                fontFamily = SFPDH,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Button(
                onClick = { onNavigateToMain() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "Пропустить",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = SkipButtonColor
                )
            }
        }
        Text(
            text = stringResource(R.string.patient_card_gray_text_1),
            fontFamily = SFPD,
            fontWeight = FontWeight(400),
            fontSize = 14.sp,
            color = GrayTextColor,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = stringResource(R.string.patient_card_gray_text_2),
            fontFamily = SFPD,
            fontWeight = FontWeight(400),
            fontSize = 14.sp,
            color = GrayTextColor,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        for (i in patientCard.indices) {
            TextField(
                value = patientCard[i].value,
                onValueChange = { patientCard[i].value = it },
                shape = RoundedCornerShape(30),
                modifier = Modifier
                    .padding(end = 20.dp, top = 24.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                label = { Text(text = labels[i]) },
                singleLine = true
            )
        }
        Button(
            onClick = {
                val profileApi = ProfileApiImpl()
                val response = profileApi.createProfile(
                    Profile(
                        firstname = firstname.value,
                        middlename = middlename.value,
                        lastname = lastname.value,
                        bith = birthday.value,
                        pol = gender.value
                    ),
                    token = "Bearer "+User.token.toString()
                )
                response.enqueue(object : Callback<CreateProfileResponse>{
                    override fun onResponse(
                        call: Call<CreateProfileResponse>,
                        response: Response<CreateProfileResponse>
                    ) {
                        if(response.code()==200){
                            User.isCardCreated = true
                            with(User.sharedPrefs.edit()){
                                putBoolean("isCardCreated", User.isCardCreated!!)
                                apply()
                            }
                            onNavigateToMain()
                        }
                        if(response.code()==403){
                            title.value = "Ошибка 403"
                            text.value = response.message()
                            openDialog.value = true
                        }
                        if(response.code()==422){
                            title.value = "Ошибка 422"
                            text.value = response.message()
                            openDialog.value = true
                        }
                        else{
                            title.value="Ошибка"
                            text.value=response.message()
                            openDialog.value = true
                        }
                    }
                    override fun onFailure(call: Call<CreateProfileResponse>, t: Throwable) {
                        title.value = "Ошибка"
                        text.value = t.message.toString()
                        openDialog.value = true
                    }

                })
            },
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .padding(end = 20.dp, top = 48.dp)
                .height(56.dp)
                .fillMaxWidth(),
            enabled = patientCard.all { it.value.isNotEmpty() },
            colors = ButtonDefaults.buttonColors(containerColor = CreateButtonColor)
        ) {
            Text(
                text = "Создать",
                fontSize = 17.sp,
                fontWeight = FontWeight(600),
                fontFamily = SFPDSB,
                color = Color.White
            )
        }

    }
}

@Preview
@Composable
fun PatientCardPreview() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        PatientCardScreen({})
    }
}