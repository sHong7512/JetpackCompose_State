package com.shong.jetpackcompose_state

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shong.jetpackcompose_state.ui.theme.JetpackCompose_StateTheme
import com.shong.jetpackcompose_state.ui.theme.StatefulCounter
import com.shong.jetpackcompose_state.ui.theme.WaterCounter

/*
mutableStateListOf 대신 API를 사용하여 목록을 만들 수 있습니다.
그러나 사용 방법에 따라 예기치 않은 재구성 및 최적화되지 않은 UI 성능이 발생할 수 있습니다.
목록을 정의한 다음 다른 작업에 작업을 추가하면 모든 재구성에 대해 중복 항목이 추가됩니다.

// Don't do this!
val list = remember { mutableStateListOf<WellnessTask>() }
list.addAll(getWellnessTasks())
*/

// 뷰모델을 사용해서 state할 때
@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()

        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task ->
                wellnessViewModel.remove(task)
            }
        )
    }
}

/* 뷰모델을 사용하지 않고 state할 때
@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        StatefulCounter()

        val list = remember { getWellnessTasks().toMutableStateList() }
        WellnessTasksList(list = list, onCloseTask = { task -> list.remove(task) })
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
*/


@Preview("WellnessScreen")
@Composable
fun WellnessScreen_Pre(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()

        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task ->
                wellnessViewModel.remove(task)
            }
        )
    }
}