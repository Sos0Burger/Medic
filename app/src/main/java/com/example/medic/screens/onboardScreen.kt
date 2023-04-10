package com.example.medic.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardScreen() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (pagerState.currentPage == 2) {
                        //todo вот тут типо дальше на экран регистрации
                    } else {
                        coroutineScope.launch { pagerState.scrollToPage(pagerState.currentPage + 1) }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Blue
                )
            ) {
                Text(text = if(pagerState.currentPage == 2) "Завершить" else "Пропустить", fontSize = 20.sp, color = skipButtonColor)
            }
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.rectangleplus),
                    contentDescription = null,
                    modifier = Modifier.size(165.dp, 165.dp)
                )
            }

        }


        HorizontalPager(pageCount = 3, state = pagerState) { page ->
            when (page) {
                0 -> {
                    BottomContent(
                        greenText = "Анализы",
                        stringResource(id = R.string.analizes_onboard_gray_text),
                        dotPosition = pagerState.currentPage,
                        illustrationId = R.drawable.analizes_illustration
                    )
                }
                1 -> {
                    BottomContent(
                        greenText = "Уведомления",
                        grayText = stringResource(R.string.notification_gray_text),
                        dotPosition = pagerState.currentPage,
                        illustrationId = R.drawable.notificationillustration
                    )
                }
                2 -> {
                    BottomContent(
                        greenText = "Мониторинг",
                        grayText = stringResource(id = R.string.monitoring_gray_text),
                        dotPosition = pagerState.currentPage,
                        illustrationId = R.drawable.monitoringillustration
                    )
                }
            }

        }
    }
}

@Composable
fun BottomContent(greenText: String, grayText: String, dotPosition: Int, illustrationId: Int) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = greenText,
            color = GreenTextColor,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        Text(
            text = grayText,
            fontSize = 14.sp,
            color = GrayTextColor
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(all = 50.dp)
        ) {
            for (i in 0..2) {
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

@Preview
@Composable
fun PreviewScreen() {
    MedicTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            OnboardScreen()
        }
    }
}