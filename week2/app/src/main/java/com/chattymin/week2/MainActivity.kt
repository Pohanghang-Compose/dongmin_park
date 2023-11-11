package com.chattymin.week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.chattymin.week2.ui.theme.Week2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Catalogue()
                }
            }
        }
    }
}

@Composable
fun Catalogue() {
    LazyColumn {
        items(items) { itemData ->
            Item(itemData = itemData)
        }
    }
}

@Composable
fun Item(itemData: ItemData) {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        val painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = itemData.imageId)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.img_placeholder)
                        error(R.drawable.img_error)
                    }).build()
            )

        Column(
            Modifier.padding(12.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = "image"
            )
            Text(
                text = itemData.title,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = itemData.description)
        }
    }
}

val items = listOf(
    ItemData(
        imageId = "https://upload.wikimedia.org/wikipedia/ko/thumb/3/3e/%ED%8F%AC%EC%BC%93%EB%AA%AC_%EC%9E%A0%EB%A7%8C%EB%B3%B4.png/200px-%ED%8F%AC%EC%BC%93%EB%AA%AC_%EC%9E%A0%EB%A7%8C%EB%B3%B4.png",
        title = "잠만보",
        description = "잠만보. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = "https://i.namu.wiki/i/4hoeZzNNSeHXBR5dUvl5EiCjOtqD6BgGRXWkwX0VFvWqjipnn2mj4L9hTwLxQmbgraoQMXvtrlhGlHCYwP2ExJjA3JR_Dsqg3pIDDjY22V8HmynAk35OxtoqJ_kuElABrC7jkrCXiwt444ZaBARoyQ.webp",
        title = "파이리",
        description = "파이리. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = "https://i.namu.wiki/i/A0xjmjf_pjpjbnU8k-hEWeT1cTjv8RpphsXYwlsOsvRlXbkliJ0AbABVW_sW_e-MCItmidhgxjT8YVHIHVhz5g2jmb5W09ZyE3LCVZkFnK7_Tl4fWJpNp5Xd3STztszajDK7kz6nr2QDn5QGJAlQ0Q.webp",
        title = "꼬부기",
        description = "꼬부기. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = "https://i.namu.wiki/i/X6ncMrIGvuA7JQhsQNaVz3nQMa6WhgbkoRBS7oGtDq0XuTtm7y83myOQWJQ-QUbIQDdh7ncN3sgnI_-3-Bq8nnG5huxXR-nYx47JGOQMOFyshwXZG5aJTJqC_1daeByX8EGhYCyaFXtwSjiCNtt4vA.webp",
        title = "파치리스",
        description = "파치리스. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = "https://i.namu.wiki/i/T-a8tF95n1XyR9-zl5wcvIkuTlFjYi__xD5VgU_Li3lIUUUuYxn8odpSxVpSOHeFxgW9Qdw82DVurSNG4CHy5XHJqFZKmrXJ0KdCyOQGkvxJtt_Xt_veVJ4g6txn7y-WahHT66ku1PoH6MxkeP4djA.webp",
        title = "피카츄",
        description = "피카츄. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Week2Theme {
        Catalogue()
    }
}
