package com.example.medic.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.medic.R
import com.example.medic.api.newsApi.NewsApiIml
import com.example.medic.api.response.NewsResponse
import com.example.medic.ui.theme.GrayTextColor
import com.example.medic.ui.theme.SFPD
import com.example.medic.ui.theme.SFPDH
import com.example.medic.ui.theme.SFPDSB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MainScreen() {

    val searchContent = remember { mutableStateOf("") }

    var newsList: List<NewsResponse>? = null
    var newsListCount = remember {
        mutableStateOf(0)
    }

    val newsApi = NewsApiIml()
    val response = newsApi.news()

    response.enqueue(object : Callback<List<NewsResponse>> {
        override fun onResponse(
            call: Call<List<NewsResponse>>,
            response: Response<List<NewsResponse>>
        ) {
            if (response.code() == 200) {
                newsList = response.body()
                Log.d("successful", "news!!!")
                newsListCount.value = response.body()!!.size

            }
            if (!response.isSuccessful) {
                Log.d("error", response.message().toString())
            }
        }

        override fun onFailure(call: Call<List<NewsResponse>>, t: Throwable) {

        }

    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp)
    ) {
        TextField(value = searchContent.value, onValueChange = {
            searchContent.value = it
        },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder =
            {
                Row(horizontalArrangement = Arrangement.Start) {
                    Image(
                        painter = painterResource(id = R.drawable.searchicon),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "Искать анализы", modifier = Modifier.padding(start = 8.dp))
                }
            })

        LazyColumn(modifier = Modifier.padding(top = 32.dp)) {
            item {
                Text(
                    text = "Акции и новости",
                    fontSize = 17.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = SFPDSB,
                    color = GrayTextColor
                )
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                    items(count = newsListCount.value) { index ->
                        Button(
                            onClick = { /*TODO*/ },
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .height(152.dp)
                                .width(270.dp)
                        ) {
                            Box(modifier = Modifier.height(152.dp).width(270.dp)) {
                                AsyncImage(
                                    model = newsList!![index].image,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Text(
                                    text = newsList!![index].name,
                                    fontFamily = SFPDH,
                                    fontWeight = FontWeight(800),
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                                )
                                Row(
                                    verticalAlignment = Alignment.Bottom,
                                    modifier = Modifier.padding(bottom = 40.dp)
                                ) {
                                    Text(
                                        text = newsList!![index].description,
                                        fontFamily = SFPD,
                                        fontWeight = FontWeight(400),
                                        fontSize = 14.sp
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.Bottom,
                                    modifier = Modifier.padding(bottom = 12.dp)
                                ) {
                                    Text(
                                        text = newsList!![index].price + " ₽",
                                        fontFamily = SFPDH,
                                        fontWeight = FontWeight(800),
                                        fontSize = 20.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MainScreen()
    }

}