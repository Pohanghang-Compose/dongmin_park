package com.chattymin.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.chattymin.week1.ui.theme.BackgroundGray
import com.chattymin.week1.ui.theme.DarkYellow
import com.chattymin.week1.ui.theme.Week1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week1Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    ProfileCard()
                }
            }
        }
    }
}

@Composable
fun ProfileCard() {
    Column {
        IdentificationCard()
        UserInfo()
        UserOrganisation()
    }
}

@Composable
fun IdentificationCard() = Text(
    modifier = Modifier
        .background(DarkYellow)
        .fillMaxWidth()
        .padding(24.dp),
    textAlign = TextAlign.Center,
    color = Color.White,
    fontWeight = FontWeight.Bold,
    text = "IDENTIFICATION CARD"
)

@Composable
fun UserInfo() {
    Column(
        modifier = Modifier
            .background(BackgroundGray)
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ProfilePicture()
        UserName()
        UnderLineSection()
    }
}

@Composable
fun ProfilePicture() {
    val url =
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDEyMTRfMTQx%2FMDAxNjA3ODcyNDUzNTc5.QDqSzxPYKahoMeYaM3Wdt5KJoOeBPIUxZZVXFZRIiOQg.K0mDfBBO4Y3w88oisDOqaCBtqd24P5_lZcWQs3I2tvQg.JPEG.sosohan_n%2F24_%252820%2529.jpg&type=a340"
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.img_placeholder)
                    error(R.drawable.img_error)
                }).build()
        )

    Image(
        modifier = Modifier.size(250.dp),
        painter = painter,
        contentDescription = "Profile Image"
    )
}

@Composable
fun UserName() = Column(
    modifier = Modifier,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = "SHIN Jjangu",
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold
    )
    Text(
        modifier = Modifier.padding(8.dp),
        text = "{ Leviathan }",
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
fun UnderLineSection() = Column {
    UnderLineInfo(title = "BREED", text = "PURE")
    UnderLineInfo(title = "SYNDROME", text = "SOLARIS")
    UnderLineInfo(title = "WORKS", text = "UGN JAPAN BRANCH CHIEF")
}

@Composable
fun UnderLineInfo(title: String, text: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.3f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                text = title
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                text = text
            )
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = DarkYellow
        )
    }
}

@Composable
fun UserOrganisation() = Row(
    modifier = Modifier
        .fillMaxSize()
        .background(DarkYellow),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        modifier = Modifier.size(72.dp),
        imageVector = Icons.Default.Face,
        contentDescription = "Face Icon"
    )
    Text(
        letterSpacing = (-1).sp,
        text = "UNIVERSAL GUARDIANS NETWORK",
        fontWeight = FontWeight.ExtraBold
    )
}

