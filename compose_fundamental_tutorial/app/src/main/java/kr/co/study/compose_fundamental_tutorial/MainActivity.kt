package kr.co.study.compose_fundamental_tutorial

import android.content.res.Resources
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.study.compose_fundamental_tutorial.ui.theme.Compose_fundamental_tutorialTheme
import java.util.concurrent.TimeoutException
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_fundamental_tutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    //Container()
                    //VerticalContainer()
                    BoxWithConstraintContainer()
                }
            }
        }
    }
}

// arrangement 요소를 어떤식 배열할지
// arrangement 는 Row, Column 같은 요소들이 들어가는
// 컨테이너 성격의 콤포저블에서 요소들의 아이템을 정렬할 때 사용된다.
// 웹 개발 css에서 flex와 비슷하다고 보면 된다.
// alignment

@Composable
fun Container() {
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}

@Composable
fun BoxContainer() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        propagateMinConstraints = true
    ) {
        DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        DummyBox(modifier = Modifier.size(150.dp), color = Color.Yellow)
        DummyBox(color = Color.Blue)
    }
}

@Composable
fun BoxWithConstraintContainer() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        if (this.minWidth > 500.dp) {
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        } else {
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Yellow)
        }
        Text(text = "minHeight: ${this.minHeight}", color = Color.Black)

//        Column {
//            BoxWithConstraintItem(
//                modifier = Modifier
//                    .size(200.dp)
//                    .background(Color.Yellow)
//            )
//            BoxWithConstraintItem(
//                Modifier
//                    .size(300.dp)
//                    .background(Color.Green)
//            )
//        }

        /*DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        DummyBox(modifier = Modifier.size(150.dp), color = Color.Yellow)
        DummyBox(color = Color.Blue)*/
    }
}

@Composable
fun BoxWithConstraintItem(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        if (this.minWidth > 200.dp) {
            Text(text = "이것은 큰 상자이다.")
        } else {
            Text(text = "이것은 작은 상자이다.")
        }
    }
}

@Composable
fun VerticalContainer() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}

@Composable
fun DummyBox(modifier: Modifier = Modifier, color: Color? = null) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)

    // color 가 값이 있으면 해당 값을 넣어주고 값이 없다면 랜덤 값을 넣어주기
    val randomColor = color?.let { it } ?: Color(red, green, blue)
    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor))
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_fundamental_tutorialTheme {
        //Container()
        //Greeting("Android")
        //VerticalContainer()
        //BoxContainer()
        BoxWithConstraintContainer()
    }
}