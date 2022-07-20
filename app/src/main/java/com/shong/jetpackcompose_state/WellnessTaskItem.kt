package com.shong.jetpackcompose_state

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shong.jetpackcompose_state.ui.theme.JetpackCompose_StateTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable


// ERROR : Type 'TypeVariable(T)' has no method 'getValue(Nothing?, KProperty<*>)' and thus it cannot serve as a delegate
// remember에서 간혹 발생한다. getValue, setValue 가 정의되지 않아서 그럼
// import androidx.compose.runtime.* 해주면 해결
// 아니면 import androidx.compose.runtime.getValue
//      import androidx.compose.runtime.setValue
// 이렇게로 해결

//리스트를 state할 때
@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose : () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = taskName,
            modifier = modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

//리스트를 state하지 않을 때
@Composable
fun WellnessTaskItem(taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier){
    // Saveable로 바꾸면 스크롤 시에도 가능
    // 일반 remember는 프로세스 재생성에서 살아님지 못한다.
//    var checkedState by remember { mutableStateOf(false) }
    var checkedState by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = onClose, // we will implement this later!
        modifier = modifier,
    )
}

@Preview("WellnessTaskItem")
@Composable
fun WellnessTaskItem_Pre(){
    JetpackCompose_StateTheme {
        var checkedState by remember { mutableStateOf(false) }

        WellnessTaskItem(
            taskName = "Preview taskName",
            checked = checkedState,
            onCheckedChange = { newValue -> checkedState = newValue },
            onClose = {} // we will implement this later!
        )
    }
}

