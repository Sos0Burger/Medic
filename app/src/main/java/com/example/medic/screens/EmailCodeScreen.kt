package com.example.medic.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.medic.AlertUI
import com.example.medic.api.userApi.UserApi
import com.example.medic.ui.theme.ArrowColor
import com.example.medic.ui.theme.MedicTheme
import com.example.medic.ui.theme.PasswordButtonColor
import com.example.medic.ui.theme.SFPD

@Composable
fun EmailCodeScreen() {
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
                onClick = { /*TODO*/ },
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
                fontFamily = SFPD
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
                                //todo
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
            EmailCodeScreen()
        }
    }
}