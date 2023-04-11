package com.example.medic

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medic.ui.theme.PasswordButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertUI(title: String, text:String){
    Card(shape = RoundedCornerShape(10), modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp).height(250.dp), colors = CardDefaults.cardColors(containerColor = PasswordButtonColor)) {
        Column(Modifier.padding(top=10.dp, start = 5.dp)) {
            Text(text = title, fontSize = 26.sp, fontWeight = FontWeight(700), modifier = Modifier.padding(bottom = 20.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = text, fontSize = 20.sp, fontWeight = FontWeight(500))
            }
        }

    }


}