package kr.co.study.day_01

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.study.day_01.ui.theme.Day_01Theme
import kr.co.study.day_01.ui.theme.Green200
import kr.co.study.day_01.ui.theme.Red200
import kr.co.study.day_01.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Day_01Theme {
                // A surface container using the 'background' color from the theme
                ContentView()
            }
        }
    }
}

@Composable
private fun ContentView() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {
            Log.d("TestTest", "ContentView: 온보딩 클릭됨")
            shouldShowOnboarding = !shouldShowOnboarding
        })
    } else {
        MyLazyColumn(modifier = Modifier.padding(20.dp))
    }


//    Surface(color = Green200) {
////        HelloWorld(modifier = Modifier.padding(all = 10.dp))
//        MyColumn(modifier = Modifier.padding(10.dp))
//    }
}

@Composable
private fun MyColumn(fruits: List<String> = listOf("바나나", "딸기", "포도"), modifier: Modifier) {
    Column(modifier = modifier) {
        fruits.forEach { aFruitName ->
            FruitView(aFruitName)
        }
    }
}

@Composable
private fun MyLazyColumn(fruits: List<String> = List(1000) { "번호 : $it" }, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = fruits) { aFruit ->
            FruitView(name = aFruit)
        }
    }
}

@Composable
fun FruitView(name: String) {

    // 3가지 방식!
    val expended = rememberSaveable { mutableStateOf(false) }
    var isOpen by remember { mutableStateOf(false) }
    val (shouldOpen, setShouldOpen) = remember { mutableStateOf(false) }

    //val extraPadding = if (expended.value) 48.dp else 0.dp

//    val extraPadding by animateDpAsState(
//        if (expended.value) 48.dp else 0.dp
//    )

    val extraPadding = animateDpAsState(
        if (expended.value) 48.dp else 0.dp
    )

    Surface(
        color = Red200,
        modifier = Modifier
            .padding(bottom = extraPadding.value)
            .clickable { Log.d("TestTest", "FruitView: $name ") }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth()
        ) {
            Text(text = name,
                modifier = Modifier
                    .padding(10.dp)
                    .background(Teal200)
                    .weight(0.5f),
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            OutlinedButton(
//                modifier = Modifier.weight(0.5f),
                onClick = {
                    expended.value = !expended.value
//                    isOpen = !isOpen
//                    setShouldOpen(!shouldOpen)
                Log.d("TestTest", "FruitView: 버튼 클릭 $name")
            }) {
                //Text(text = if (expended.value) "열렸다" else "닫혔다")
                Text(text = if (expended.value) "열렸다" else "닫혔다")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "오늘도 $name!")
}

@Composable
fun HelloWorld(modifier: Modifier) {
    Surface(color = Green200, modifier = modifier) {
        Text(text = "Hello World", color = Color.White, modifier = Modifier.padding(all = 30.dp))
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Composable
fun DefaultPreview() {
    Day_01Theme {
        //HelloWorld()

        MyLazyColumn(modifier = Modifier.padding(20.dp))
    }
}
