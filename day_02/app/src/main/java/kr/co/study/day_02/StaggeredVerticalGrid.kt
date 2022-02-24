package kr.co.study.day_02

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.unit.Constraints


@Composable
fun StaggeredVerticalGrid(
    modifier: Modifier = Modifier,
    columnCount: Int = 2,
    content: @Composable () -> Unit
){
    // Layout 콤포저블을 통해 레이아웃 배치
    Layout(content = content,
        modifier = modifier,
        measurePolicy = { measurables: List<Measurable>, constraints: Constraints ->

            // 컬럼 가로
            val columnWidth = (constraints.maxWidth / columnCount)

            // 컨스트레인트 변경
            // 아이템의 가로 맥스가 위에서 설정한 컬럼 가로가 맥스가 되도록 컨스트레인트 변경
            val itemConstraints = constraints.copy(maxWidth = columnWidth)

            // 각각의 아이템 높이 추적을 위해 설정
            val columnHeights = IntArray(columnCount) { 0 }

            // 각각의 배치 아이템들 변형
            // 어디에 위치 시킬 것인지
            val placeables = measurables.map{ aMeasurable ->

                // 어떤 컬럼 아이템이 짧은지 알아야 한다.
                val shortestColumnIndex = shortestColumn(columnHeights)

                //
                val placeable = aMeasurable.measure(itemConstraints)

                columnHeights[shortestColumnIndex] += placeable.height

                placeable
            }

            // 콜롬 높이들 중 가장 높은 녀석을 가져오기
            // coerceIn 콜롬 최소 높이, 최대 높이 범위 안에 있도록 설정
            // ?: 값이 없다면 컨스트레인트의 최소 높이를 넣기
            val containerHeight = columnHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight) ?: constraints.minHeight

            val containerWidth = constraints.maxWidth

            // 최종적으로 아이템들 배치
            layout(
                width = containerWidth,
                height = containerHeight,
                placementBlock = {

                    // 콜롬 Y 위치 추적을 위한 배열
                    val columnYPointers = IntArray(columnCount) { 0 }

                    placeables.forEach { aPlaceable ->

                        val shortestColumn = shortestColumn(columnYPointers)

                        // place 로 아이템 배치
                        aPlaceable.place(
                            x = columnWidth * shortestColumn,
                            y = columnYPointers[shortestColumn]
                        )

                        columnYPointers[shortestColumn] += aPlaceable.height
                    }

                })
        })
}

// 가장 짧은 컬럼 찾기
private fun shortestColumn(columnHeights: IntArray): Int {

    var minHeight = Int.MAX_VALUE
    var columnIndex = 0

    // 각각의 컬럼 높이들 반복
    columnHeights.forEachIndexed { index, height ->
        if (height < minHeight){
            minHeight = height
            columnIndex = index
        }
    }
    return columnIndex
}
