package kr.co.study.compose_fundamental_tutorial

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import kr.co.study.compose_fundamental_tutorial.ui.theme.Compose_fundamental_tutorialTheme
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
                    //BoxWithConstraintContainer()
                    //TextContainer()
                    //ShapeContainer()
                    //ButtonsContainer()
                    CheckBoxContainer()
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

@Composable
fun TextContainer() {
    val name = "최민재"
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var words = stringResource(id = R.string.dummy_short_text)
    var wordsArray = words.split(" ")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "안녕하세요? $name",
            style = TextStyle(
              textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Magenta)
        )
        Text(text = "안녕하세요? $name",
            style = TextStyle(
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
        )
        Text(text = stringResource(id = R.string.dummy_short_text),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                textAlign = TextAlign.Justify,
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough,
                        TextDecoration.Underline
                    )
                )
            ),
            fontWeight = FontWeight.W200,
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
        )

        Text(text = stringResource(id = R.string.dummy_short_text),
            style = TextStyle(
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.garang, weight = FontWeight.ExtraBold))
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
            fontSize = 30.sp
        )

        Text(text = buildAnnotatedString {
            append("안녕하세요?")
            withStyle(style = SpanStyle(color = Color.Blue,
                                fontSize = 40.sp,
                                fontWeight = FontWeight.ExtraBold)
            ) {
                append("개발하는 최민재입니다!\n")
            }
            withStyle(style = SpanStyle(color = Color.Red,
                                fontSize = 30.sp)
            ){
                append("히히히히ㅣㅎ")
            }
        })

        Text(text = buildAnnotatedString {
            wordsArray.forEach {
                if (it.contains("인생")) {
                    withStyle(style = SpanStyle(color = Color.Blue,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )) {
                        append("$it ")
                    }
                }else {
                    append("$it ")
                }
            }
        })

        ClickableText(text = AnnotatedString("클릭미!"), onClick = {
            Toast.makeText(context, "뀨잉", Toast.LENGTH_SHORT).show() },
        style = TextStyle(color = Color.Red, fontSize = 30.sp, fontFamily = FontFamily(Font(R.font.garang))))

        Text(text = stringResource(id = R.string.dummy_long_text),
            style = TextStyle(lineHeight = 20.sp))
    }
}

@Composable
fun ShapeContainer() {

    var polySides by remember { mutableStateOf(3) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //DummyBox(modifier = Modifier.clip(RectangleShape))
        DummyBox(modifier = Modifier.clip(CircleShape))
        DummyBox(modifier = Modifier.clip(RoundedCornerShape(10.dp)))
        DummyBox(modifier = Modifier.clip(CutCornerShape(10.dp)))
        DummyBox(modifier = Modifier.clip(TriangleShape()))
        DummyBox(modifier = Modifier.clip(PolyShape(polySides, 100f)))

        Text(text = "ploySides: $polySides")
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                polySides += 1
            }) {
                Text(text = "plotSides + 1")
            }
            Button(onClick = {
                polySides = 3
            }) {
                Text(text = "초기화")
            }
        }
    }
}

class TriangleShape(): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path = path)
    }
}

class PolyShape(private val sides: Int, private val radius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply { polygon(sides, radius, size.center) })
    }
}

fun Path.polygon(sides: Int, radius: Float, center: Offset) {
    val angle = 2.0 * Math.PI / sides
    moveTo(
        x = center.x + (radius * cos(0.0)).toFloat(),
        y = center.y + (radius * sin(0.0)).toFloat()
    )
    for (i in 1 until sides) {
        lineTo(
            x = center.x + (radius * cos(angle * i)).toFloat(),
            y = center.y + (radius * sin(angle * i)).toFloat()
        )
    }
    close()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ButtonsContainer() {

    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Yellow, Color.Red))
    val button4Disable = remember { mutableStateOf(true) }
    
    val interactionSourceForFirst = remember { MutableInteractionSource() }

    val interactionSourceForSecond = remember { MutableInteractionSource() }

    val isPressedForFirst by interactionSourceForFirst.collectIsPressedAsState()
    val isPressedForSecond by interactionSourceForSecond.collectIsPressedAsState()

    val pressedStatusTitle = if (isPressedForFirst || isPressedForSecond) "버튼을 누르고 있다." else "버튼에서 손을 뗐다."

    val pressedBtnRadiusWithAnim: Dp by animateDpAsState(
        if (isPressedForSecond) 0.dp else 10.dp
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            enabled = true,
            onClick = {
            Log.d("TestTest", "ButtonsContainer: 버튼 1 클릭")
        }) {
            Text(text = "버튼 1")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            onClick = {
                Log.d("TestTest", "ButtonsContainer: 버튼 2 클릭")
            }) {
            Text(text = "버튼 2")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(top = 15.dp, bottom = 15.dp, start = 20.dp, end = 20.dp),
            onClick = {
                Log.d("TestTest", "ButtonsContainer: 버튼 3 클릭")
                button4Disable.value = !button4Disable.value
            }) {
            Text(text = "버튼 3")
        }

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = button4Disable.value,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.LightGray
            ),
            interactionSource = interactionSourceForFirst,
            onClick = {
                Log.d("TestTest", "ButtonsContainer: 버튼 4 클릭")
            }) {
            Text(text = "버튼 4", color = Color.White)
        }

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = button4Disable.value,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.LightGray
            ),
            interactionSource = interactionSourceForSecond,
            modifier = Modifier.drawColoredShadow(
                color = Color.Red,
                alpha = 0.5f,
                borderRadius = 10.dp,
                shadowRadius = pressedBtnRadiusWithAnim,
                offsetY = 0.dp,
                offsetX = 0.dp
            ),
            onClick = {
                Log.d("TestTest", "ButtonsContainer: 버튼 5 클릭")
            }) {
            Text(text = "버튼 5", color = Color.White)
        }
        Text(text = "$pressedStatusTitle")
    }
}

@Composable
fun CheckBoxContainer() {

    val checkedStatusForFirst = remember { mutableStateOf(false) }
    val checkedStatusForSecond = remember { mutableStateOf(false) }
    val checkedStatusForThird = remember { mutableStateOf(false) }
//    val checkedStatusForFourth = remember { mutableStateOf(false) }

    val checkedStatesArray = listOf(
                                checkedStatusForFirst,
                                checkedStatusForSecond,
                                checkedStatusForThird,
                            )

    val allBoxChecked: (Boolean) -> Unit = { isAllBoxChecked ->
        Log.d("TestTest", "CheckBoxContainer: isAllBoxChecked: $isAllBoxChecked")
        checkedStatesArray.forEach { it.value = isAllBoxChecked }
    }

 //   val checkedStatusForFourth: Boolean = checkedStatesArray.all { it.value == true }
    val checkedStatusForFourth: Boolean = checkedStatesArray.all { it.value }


    //var checkedStatusForSecond by remember { mutableStateOf(false) }

    //var (checkedStatusForThird, setCheckedStatusForThird) = remember { mutableStateOf(false) }

    //var (checkedStatusForFourth, setCheckedStatusForFourth) = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CheckBoxWithTitle(title = "1번 확인사항", checkedStatusForFirst)
        CheckBoxWithTitle(title = "2번 확인사항", checkedStatusForSecond)
        CheckBoxWithTitle(title = "3번 확인사항", checkedStatusForThird)
        /*Checkbox(
            enabled = true,
            checked = checkedStatusForSecond,
            onCheckedChange = { isChecked ->
                Log.d("TestTest", "CheckBoxContainer: isChecked: $isChecked")
                checkedStatusForSecond = isChecked }
        )
        Checkbox(
            enabled = true,
            checked = checkedStatusForThird,
            onCheckedChange = {
                Log.d("TestTest", "CheckBoxContainer: isChecked: $it")
                setCheckedStatusForThird.invoke(it) }
        )*/
        Spacer(modifier = Modifier.height(10.dp))
        AllAgreeCheckBox(title = "모두 동의하십니까?", shouldChecked = checkedStatusForFourth, allBoxChecked)
        Spacer(modifier = Modifier.height(10.dp))
        MyCustomCheckBox(title = "커스텀 체크박스 리플 O", withRipple = true)
        MyCustomCheckBox(title = "커스텀 체크박스 리플 X", withRipple = false)

        /*Checkbox(
            enabled = true,
            checked = checkedStatusForFourth,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color(0xFFFFC700),
                checkmarkColor = Color.Black,
                disabledColor = Color(0xFF005EFF)
            ),
            onCheckedChange = {
                Log.d("TestTest", "CheckBoxContainer: isChecked: $it")
                setCheckedStatusForFourth.invoke(it) }
        )*/
    }
}

@Composable
fun CheckBoxWithTitle(title: String, isCheckedState: MutableState<Boolean>) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(
            enabled = true,
            checked = isCheckedState.value,
            onCheckedChange = { isChecked ->
                Log.d("TestTest", "CheckBoxContainer: isChecked: $isChecked")
                isCheckedState.value = isChecked
            }
        )
        Text(text = title)
    }
}

@Composable
fun AllAgreeCheckBox(
    title: String,
    shouldChecked: Boolean,
    allBoxChecked: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(
            enabled = true,
            checked = shouldChecked,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color(0xFFFFC700),
                checkmarkColor = Color.White,
                disabledColor = Color(0xFF005EFF)
            ),
            onCheckedChange = { isChecked ->
                Log.d("TestTest", "CheckBoxContainer: isChecked: $isChecked")
//                isCheckedState.value = isChecked
                allBoxChecked(isChecked)
            }
        )
        Text(text = title)
    }
}

@Composable
fun MyCustomCheckBox(title: String, withRipple: Boolean = false) {

    //var isCheckedState by remember { mutableStateOf(false) }
    //var isCheckt =  remember { mutableStateOf(false) }
    var (isChecked, setIsChecked) = remember { mutableStateOf(false) }

    var togglePainter = if (isChecked) R.drawable.ic_checked else R.drawable.ic_unchecked

    var checkedInfoString = if (isChecked) "체크됨" else "체크안됨"

    var rippleEffect = if (withRipple) rememberRipple(
        radius = 30.dp,
        bounded = false,
        color = Color.Blue
    ) else null

    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clickable(
                    indication = rippleEffect,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    setIsChecked.invoke(!isChecked)
                    Log.d("TestTest", "MyCustomCheckBox: 클릭이 되었다! / $isChecked")
                }
        ) {
            Image(
                painter = painterResource(id = togglePainter),
                contentDescription = null
            )
        }
        Text(text = "$title / $checkedInfoString")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MyPreview() {
    Compose_fundamental_tutorialTheme {
        //Container()
        //Greeting("Android")
        //VerticalContainer()
        //BoxContainer()
        //BoxWithConstraintContainer()
        //TextContainer()
        //ShapeContainer()
        //ButtonsContainer()
        CheckBoxContainer()
    }
}