package kr.co.study.day_02

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.co.study.day_02.ui.theme.Day_02Theme

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(

    // layout
    layout { measurable, constraints ->

        // 배치할 녀석
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        // placeable[FirstBaseline] 이 AlignmentLine.Unspecified 라면 오류 발생
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline

        val height = placeable.height + placeableY

        // 배치 x, y
        layout(placeable.width, height) {
            // Where the composable gets placed
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    Day_02Theme() {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    Day_02Theme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}
