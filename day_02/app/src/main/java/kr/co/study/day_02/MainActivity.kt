package kr.co.study.day_02

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
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
                //MyRowTest()
                //StaggeredGridGoogleExample()
                //LargeConstraintLayout()
                //DecoupledConstraintLayout()
                TwoTexts(modifier = Modifier, text1 = "오늘도", text2 = "카페")


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
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.background(Color.Yellow)
        .height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .height(100.dp)
                .wrapContentWidth(Alignment.Start),
            text = "asfdsafdsafasdfasf"
        )

        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),

            text = text2
        )
    }
}

@Composable
fun DecoupledConstraintLayout() {

    val buttonId: String = "MyButton"
    val addMarginButtonId: String = "AddMarginButtonId"
    val textId: String = "MyText"

    // 추가된 마진
    val addedMargin = remember { mutableStateOf(0.dp) }

    val animatedMargin = animateDpAsState(
        targetValue = addedMargin.value,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(
                buttonId = buttonId,
                textId = textId,
                margin = 16.dp,
                addMarginButtonId = addMarginButtonId,
                addedMargin = animatedMargin.value
            ) // Portrait constraints
        } else {
            decoupledConstraints(
                buttonId = buttonId,
                textId = textId,
                margin = 100.dp,
                addMarginButtonId = addMarginButtonId,
                addedMargin = animatedMargin.value
            ) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId(buttonId)
            ) {
                Text("Button")
            }

            Button(
                modifier = Modifier.layoutId(addMarginButtonId),
                onClick = {
                    addedMargin.value = addedMargin.value + 100.dp
            }) {
                Text(text = "마진 추가 버튼")
            }

            Text("Text", Modifier.layoutId(textId))
        }
    }
}

private fun decoupledConstraints(addMarginButtonId: String,
                                 buttonId: String,
                                 textId: String,
                                 margin: Dp,
                                 addedMargin: Dp
): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor(buttonId)
        val text = createRefFor(textId)
        val addMarginButton = createRefFor(addMarginButtonId)

        constrain(button) {
            top.linkTo(parent.top, margin= margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
        constrain(addMarginButton) {
            top.linkTo(text.bottom, margin = addedMargin)
        }
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier.background(Color.Yellow)
    ) {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            "This is a very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent.atLeast(100.dp)
            }
        )
    }
}

@Composable
fun ConstraintLayoutContent() {

    ConstraintLayout {
        // Creates references for the three composables
        // in the ConstraintLayout's body
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("Button 2")
        }
    }



//    ConstraintLayout(
//        modifier = Modifier
//            .background(Color.Yellow)
//            .fillMaxSize()
//    ) {
//
//        // Create references for the composables to constrain
//        // XML 상에서 id 같은 개념.
//        // 추적하기 위한 개념.
//        val (buttonRef, textRef, someTextRef) = createRefs()
//
//        Button(
//            onClick = { /* Do something */ },
//            // Assign reference "button" to the Button composable
//            // and constrain it to the top of the ConstraintLayout
//            modifier = Modifier.constrainAs(buttonRef) {
////                top.linkTo(parent.top, margin = 100.dp)
////                start.linkTo(parent.start)
//                centerHorizontallyTo(parent)
//            }
//        ) {
//            Text("Button")
//        }
//
//        // Assign reference "text" to the Text composable
//        // and constrain it to the bottom of the Button composable
//        Text("Text", Modifier.constrainAs(textRef) {
////            top.linkTo(buttonRef.bottom, margin = 16.dp)
//            start.linkTo(buttonRef.end, margin = 30.dp)
//        })
//
//
//        Text("SomeText", Modifier.constrainAs(someTextRef) {
//            top.linkTo(buttonRef.bottom, margin = 16.dp)
//            // Centers Text horizontally in the ConstraintLayout
//            centerHorizontallyTo(parent)
//            centerVerticallyTo(parent)
//        })
//    }
}



val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)


@Composable
fun StaggeredGridGoogleExample(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = Color.LightGray)
            .size(200.dp)
            .padding(16.dp)
            .background(Color.Yellow)
            .horizontalScroll(rememberScrollState())
    ) {
        StaggeredGrid {
            for (topic in topics) {
                Chip(modifier = Modifier.padding(8.dp), text = topic)
            }
        }
    }
}

@Composable
fun StaggeredVerticalGridTest() {
    Scaffold {
        StaggeredVerticalGrid(
            modifier = Modifier.background(Color.Yellow),
            columnCount = 2
        ) {
            Text(text = "gkasjfkdsjfklas", modifier = Modifier
                .padding(4.dp)
                .background(Color.Green))
            Text(text = "gkasjfkdsjfklasasdf", modifier = Modifier
                .padding(4.dp)
                .background(Color.Green))
            Text(text = "gkasjfklas", modifier = Modifier
                .padding(4.dp)
                .background(Color.Green))
            Text(text = "gkasjfkdsjfklasasdfasdfdf", modifier = Modifier
                .padding(4.dp)
                .background(Color.Green))
            Text(text = "gkasjfk", modifier = Modifier
                .padding(4.dp)
                .background(Color.Green))
            Text(text = "gkasjfkdsjfklasffffff", modifier = Modifier
                .padding(4.dp)
                .background(Color.Green))
        }
    }
}

@Composable
fun MyRowTest() {
    Scaffold() {
        MyOwnRow(modifier = Modifier.background(Color.Yellow)) {
            Text(text = "하나 하나 하나",
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.Green)
                    .height(100.dp)
            )
            Text(text = "둘 둘 둘",
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.Blue)
                    .height(30.dp)
            )
            Text(text = "셋",
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.Magenta)
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
        //StaggeredGridGoogleExample()
        //DecoupledConstraintLayout()
        TwoTexts(text1 = "테스트", text2 = "입니다.")
    }
}