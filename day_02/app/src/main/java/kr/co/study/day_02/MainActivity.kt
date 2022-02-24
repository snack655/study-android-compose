package kr.co.study.day_02

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import kr.co.study.day_02.ui.theme.Blue200
import kr.co.study.day_02.ui.theme.Day_02Theme
import kr.co.study.day_02.ui.theme.Purple200
import kr.co.study.day_02.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Day_02Theme {

                //LayoutsCodelab()
                //MyColumnTest()
                MyRowTest()
                // A surface container using the 'background' color from the theme
//                Surface(
//                    color = MaterialTheme.colors.background
//                ) {
//                    PhotographerCard(modifier = Modifier.fillMaxWidth()) {
//                        Column() {
//                            Text(text = "하하하")
//                            Text(text = "하하하")
//                            Text(text = "하하하")
//                            Text(text = "하하하")
//                            Text(text = "하하하")
//                        }
//                    }
//                }
            }
        }
    }
}

@Composable
fun MyColumnTest() {
    Scaffold {
        MyOwnColumn(modifier = Modifier.background(Color.Yellow)) {
            Text(text = "gkasjfkdsjfklas")
            Text(text = "gkasjfkdsjfklasasdf")
            Text(text = "gkasjfklas")
            Text(text = "gkasjfkdsjfklasasdfasdfdf")
            Text(text = "gkasjfk")
            Text(text = "gkasjfkdsjfklasffffff")
        }
    }
}

@Composable
fun MyRowTest() {
    Scaffold() {
        MyOwnRow(modifier = Modifier.background(Color.Yellow)) {
            Text(text = "하나 하나 하나",
                modifier = Modifier.padding(4.dp).background(Color.Green).height(100.dp)
            )
            Text(text = "둘 둘 둘",
                modifier = Modifier.padding(4.dp).background(Color.Blue).height(30.dp)
            )
            Text(text = "셋",
                modifier = Modifier.padding(4.dp).background(Color.Magenta)
            )
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun SimpleList(scrollState: ScrollState) {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list

    val getBGColor : (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }

    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            if (it == 0) {

            }
            Text(text = "Item #$it",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(getBGColor(it))
            )
        }
    }
}

@Composable
fun LazySimpleList(scrollState: LazyListState) {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list

    val getBGColor : (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }

    LazyColumn(state = scrollState) {
        items(100) {
//            Text(text = "Item #$it",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(getBGColor(it))
//            )
            ImageListItem(index = it)
        }
    }
}


@Composable
fun LayoutsCodelab() {

    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()
    val lazyListScrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = {
                        Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
                    }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }

                    IconButton(onClick = {
                        Log.d("TAG", "LayoutsCodelab: 홈 클릭")
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = {
                    Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
                }) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }

                IconButton(onClick = {
                    Log.d("TAG", "LayoutsCodelab: 홈 클릭")
                }) {
                    Icon(Icons.Filled.Home, contentDescription = null)
                }
            }
        },
        drawerContent = {
            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }

            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 홈 클릭")
            }) {
                Icon(Icons.Filled.Home, contentDescription = null)
            }
        },
        floatingActionButton = {
            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
                coroutineScope.launch {
//                    scrollState.scrollTo(0)
//                    scrollState.animateScrollTo(0)
                    lazyListScrollState.animateScrollToItem(0)
                }
            }) {
                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null)
            }
        }
    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            //SomeText()
            //SimpleList(scrollState)
            LazySimpleList(scrollState = lazyListScrollState)

        }
    }
}

@Composable
fun SomeText() {
    Column() {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the layouts codelab")
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier, contentSlot: @Composable () -> Unit) {
    Surface(
        color = Blue200,
        modifier = modifier,
        content = contentSlot
    )

//    Surface(
//        color = Blue200,
//        modifier = modifier,
//        content = {
//            Column() {
//                Text(text = "Hi there!")
//                Text(text = "Thanks for going through the Layouts codelab")
//            }
//        }
//    )
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier,
                     contentSlot: @Composable () -> Unit
) {
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

        Surface(
            modifier = Modifier
                .background(Color.Yellow)
                .weight(0.7f)
                .fillMaxHeight(), content = contentSlot)

//        Column(verticalArrangement = Arrangement.spacedBy(3.dp),
//            modifier = Modifier
//                .background(Color.Yellow)
//                .weight(0.7f)
//                .fillMaxHeight()
//        ) {
//            Text("Alfred Sisley", fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .background(Purple500)
//                    .weight(0.3f))
//            // LocalContentAlpha is defining opacity level of its children
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text("3 minutes ago", style = MaterialTheme.typography.body2,
//                    modifier = Modifier
//                        .background(Color.Green)
//                        .weight(0.3f))
//            }
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text("이용불가", style = MaterialTheme.typography.caption,
//                    modifier = Modifier
//                        .background(Purple200)
//                        .weight(0.3f))
//            }
//        }
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
//        PhotographerCard(modifier = Modifier.fillMaxWidth()) {
//            Text(text = "하하하")
//        }
        LayoutsCodelab()
    }
}