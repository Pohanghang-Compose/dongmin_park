package com.chattymin.week3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MainScreen() {
    val expanded = remember { mutableStateOf(false) }
    var percent by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SurveyBtn(expanded)
        CustomProgressBar(percent)
        ShowDialog(expanded) {
            percent = it * 7.2f
        }
        ShowPoint(point = percent)
    }
}

@Composable
fun SurveyBtn(expanded: MutableState<Boolean>) {
    Button(
        onClick = { expanded.value = true },
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = "설문조사 하기")
    }
}

@Composable
fun CustomProgressBar(percent: Float) = Box(
    modifier = Modifier
        .fillMaxHeight(0.05f)
        .fillMaxWidth(),
    contentAlignment = Alignment.Center
) {
    DrawCustomProgressBar(sweepAngle = 180f, color = Color.LightGray)
    DrawCustomProgressBar(sweepAngle = percent, color = Color.Red)
}

@Composable
fun DrawCustomProgressBar(sweepAngle: Float, color: Color) = Canvas(
    modifier = Modifier.fillMaxSize()
) {
    drawArc(
        color = color,
        startAngle = 180f,
        sweepAngle = sweepAngle,
        useCenter = false,
        topLeft = Offset(150f, 0f),
        size = Size(800f, 800f),
        style = Stroke(
            width = 20f,
            cap = StrokeCap.Square
        )
    )
}

val titleList =
    listOf("Compose 스터디 만족도", "Compose 스터디 난이도", "오늘 점심 메뉴 만족도", "오늘 저녁 메뉴 만족도", "SOPT 만족도")

@Composable
fun ShowDialog(expanded: MutableState<Boolean>, add: (Int) -> Unit) {
    val sum = remember { mutableStateOf(0) }

    if (expanded.value) {
        Dialog(
            onDismissRequest = { expanded.value = false }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .background(Color.White),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(titleList) {
                    Survey(title = it, sum = sum)
                }
                item {
                    Button(onClick = {
                        add(sum.value)
                        expanded.value = false
                    }) {
                        Text(text = "제출하기")
                    }
                }
            }
        }
    }
}

@Composable
fun Survey(title: String, sum: MutableState<Int>) {
    val expanded = remember { mutableStateOf(false) }
    var stars by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Text(text = title)
            Column {
                Icon(
                    modifier = Modifier.clickable {
                        expanded.value = true
                    },
                    imageVector = if (expanded.value) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = title
                )
                DropDownMenu(expanded = expanded) {
                    stars = it
                    sum.value += it
                }
            }
        }
        Row {
            val size = 40.dp

            if (stars == 0) Spacer(modifier = Modifier.size(size))

            repeat(stars) {
                Icon(
                    imageVector = Icons.Default.Star,
                    tint = Color(0xFFD0B336),
                    contentDescription = "별점",
                    modifier = Modifier.size(size)
                )
            }
        }
    }
}

@Composable
fun DropDownMenu(expanded: MutableState<Boolean>, add: (Int) -> Unit) {
    fun close() = false.also { expanded.value = it }

    @Composable
    fun pointText(point: Int) {
        Text(
            modifier = Modifier
                .clickable {
                    add(point)
                    close()
                }
                .padding(horizontal = 20.dp, vertical = 4.dp),
            text = point.toString()
        )
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { close() }
    ) {
        repeat(5) {
            pointText(point = it + 1)
        }
    }
}

@Composable
fun ShowPoint(point: Float) {
    Text(
        fontSize = 20.sp,
        color = Color.LightGray,
        text = "지금 내 점수는"
    )
    Text(
        fontSize = 80.sp,
        color = Color.Red,
        text = (point / 180 * 100).toInt().toString()
    )
}

