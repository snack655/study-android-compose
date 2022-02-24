package kr.co.study.day_02

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable

@Composable
fun MyOwnRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        // 전체 높이
        var containerHeight = 0
        // 전체 폭
        var containerWidth = 0

        // 아이템들 가로 리스트
        val itemWidthList = IntArray(measurables.count()) { 0 }

        // 아이템들 세로 리스트
        val itemHeightList = IntArray(measurables.count()) { 0 }

        // measurable 즉 측정 가능한 애들을 placeable로 반환
        val placeables: List<Placeable> = measurables.mapIndexed { index, aMeasurable ->
            // Measure each child
            val aPlaceable = aMeasurable.measure(constraints)

            itemHeightList[index] = aPlaceable.height

            // 각각 아이템의 가로를 넣는다
            itemWidthList[index] = aPlaceable.width

            aPlaceable
        }

        // Track the y co-ord we have placed children up to
        var xPosition = 0

        // 아이템 가로의 값을 모두 더한다.
        containerWidth = itemWidthList.sum()

        // 세로의 값 중 가장 큰 값을 찾는다.
        containerHeight = itemHeightList.maxOrNull() ?: 0

        // Set the size of the layout as big as it can
        // 최종 크기
        layout(containerWidth, containerHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = xPosition, y = 0)

                // Record the y co-ord placed up to
                xPosition += placeable.width
            }
        }
    }
}