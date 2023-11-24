package com.chattymin.week4

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    var percent by remember { mutableStateOf(0f) }
    val percentAnimate by animateFloatAsState(
        targetValue = percent,
        label = "custom progress bar"
    )
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CustomProgressBar(percentAnimate)
        ShowPoint(point = percent)
        SurveyList(calcPercent = { percent = it * 7.2f })
    }
}

@Composable
fun CustomProgressBar(percent: Float) = Box(
    modifier = Modifier
        .fillMaxHeight(0.05f)
        .fillMaxWidth(),
    contentAlignment = Alignment.Center
) {
    Canvas(modifier = Modifier.fillMaxSize()){
        drawCustomProgressBar(sweepAngle = percent, color = Color(0xFFD0B336))
        drawCustomProgressBar(sweepAngle = percent, color = Color.Red)
    }
}

fun DrawScope.drawCustomProgressBar(sweepAngle: Float, color: Color){
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

val titleList =
    listOf("Compose 스터디 만족도", "Compose 스터디 난이도", "오늘 점심 메뉴 만족도", "오늘 저녁 메뉴 만족도", "SOPT 만족도")

@Composable
fun SurveyList(calcPercent: (Int) -> Unit) =
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .background(Color.White),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val starsList = MutableList(titleList.size){ 0 }

                itemsIndexed(titleList) { index, title ->
                    Survey(title = title){stars ->
                        starsList[index] = stars
                        calcPercent(starsList.sum())
                    }
                }
            }


@Composable
fun Survey(title: String, setStars: (Int) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    val stars = remember { mutableStateOf(0) }
    val starsAnimate = animateIntAsState(targetValue = stars.value, label = "star animate")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
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
                    stars.value = it
                    setStars(it)
                }
            }
        }
        Row() {
            val size = 40.dp

            DisplayStars(count = starsAnimate.value, size = size)
        }
    }
}

@Composable
fun DisplayStars(count: Int, size: Dp){
    repeat(count) {
        Icon(
            imageVector = Icons.Default.Star,
            tint = Color(0xFFD0B336),
            contentDescription = "별점",
            modifier = Modifier.size(size)
        )
    }
    repeat(5- count){
        Spacer(modifier = Modifier.size(size))
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
