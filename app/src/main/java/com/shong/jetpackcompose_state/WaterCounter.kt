package com.shong.jetpackcompose_state.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shong.jetpackcompose_state.WellnessTaskItem

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // Changes to count are now tracked by Compose
        // 재구성 단계에서 0이 되므로 remember 시켜줘야한다.
        /*   // 1
           val count: MutableState<Int> = remember { mutableStateOf(0) }
           Text(text = "You've had ${count.value} glasses", modifier.padding(16.dp))
           Button(onClick = { count.value++ }, Modifier.padding(top = 8.dp)) {
               Text(text = "Add one")
           }
   */
        // 2
        /*var count by remember { mutableStateOf(0) }
        AnimatedVisibility(visible = count > 0) {
            // showTask의 동작을 유심히 보자!
            // 컴포지션을 떠나면서 이전 값을 잊어버렷기 때문에 컴포저블이 다시 표시된다.
            var showTask by remember { mutableStateOf(true) }
            Column {
                AnimatedVisibility(visible = showTask) {
                    WellnessTaskItem(
                        onClose = { showTask = false },
                        taskName = "Have you taken your 15 minute walk today?"
                    )
                }

                Text("You've had $count glasses.")
            }
        }
        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text(text = "Add one")
            }
            Button(onClick = { count = 0 }, Modifier.padding(start = 8.dp)) {
                Text(text = "Clear water count")
            }
        }*/

        // 3
        Column(modifier = modifier.padding(16.dp)) {
            var count by rememberSaveable { mutableStateOf(0) }
            AnimatedVisibility(visible = count > 0) {
                Text("You've had $count glasses.")
            }
            Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
                Text("Add one")
            }
        }
    }

}

@Preview("WaterCounter")
@Composable
fun WaterCounter_Pre() {
    JetpackCompose_StateTheme {
        WaterCounter()
    }
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        AnimatedVisibility(visible = count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

// 컴포저블 설계의 모범 사례는 필요한 매개변수만 전달하는 것
@Composable
fun StatefulCounter() {
    var waterCount by remember { mutableStateOf(0) }
//    var juiceCount by remember { mutableStateOf(0) }

    Column {
        StatelessCounter(waterCount, { waterCount++ })
//        StatelessCounter(juiceCount, { juiceCount++ })
    }
}

@Preview("StatefulCounter")
@Composable
fun StatefulCounter_Pre() {
    JetpackCompose_StateTheme {
        StatefulCounter()
    }
}