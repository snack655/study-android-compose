package kr.co.study.day_02

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.study.day_02.ui.theme.Blue200
import kr.co.study.day_02.ui.theme.Day_02Theme
import kr.co.study.day_02.ui.theme.Purple200
import kr.co.study.day_02.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Day_02Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    PhotographerCard(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Blue200)
            .padding(all = 10.dp)
            .height(300.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(80.dp)
                .weight(0.3f)
                .clickable { Log.d("TAG", "PhotographerCard: PhotographerCard: 클릭") }
                .clip(RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
                .background(Color.Red)
                .padding(10.dp)
                .background(Color.Magenta)
                .padding(10.dp)
                .background(Color.Yellow)
            ,
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)) {
        }
        Column(verticalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier
                .background(Color.Yellow)
                .weight(0.7f)
                .fillMaxHeight()
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Purple500)
                    .weight(0.3f))
            // LocalContentAlpha is defining opacity level of its children
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .background(Color.Green)
                        .weight(0.3f))
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("이용불가", style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .background(Purple200)
                        .weight(0.3f))
            }
        }
    }


}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun PhotographerCardPreview() {
    Day_02Theme {
        PhotographerCard(modifier = Modifier.fillMaxWidth())
    }
}