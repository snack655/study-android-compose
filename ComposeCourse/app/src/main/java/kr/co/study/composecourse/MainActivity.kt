package kr.co.study.composecourse

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.withContext
import kr.co.study.composecourse.ui.theme.ComposeCourseTheme
import kr.co.study.composecourse.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCourseTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(name = "최민재 입니다!")
                }
            }
        }
    }
}

// 뷰
@Composable
fun Greeting(name: String) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("민재의 안드 앱") },
                backgroundColor = Color(0xfff25287),
                contentColor = Color.Blue
            )
         },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show() }) {
                Text(text = "클릭")
            }
        }
    ){
        //Text(text = "안녕하세요?! $name")
        MyComposableView()
    }
}

@Composable
fun MyRowItem(count: Int) {
    Row(
        Modifier
            .padding(10.dp)
            .background(Color(0xffeaffd0))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "안뉴융",
            Modifier
                .padding(all = 10.dp)
                .background(Color.Yellow))
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "안녕하다고 [$count]", Modifier.padding(all = 10.dp))
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Hello", Modifier.padding(all = 10.dp))
    }
}

@Composable
fun MyComposableView() {
    Log.d("TestTest", "MyComposableView: ")
    // Horizontal 리니어

    Column(
        Modifier
            .background(Color.Green)
            .verticalScroll(rememberScrollState())
    ) {
        for (i in 0..30) {
            MyRowItem(i)
        }
    }

}

// 미리보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCourseTheme {
        Greeting(name = "몰?루")
    }
}