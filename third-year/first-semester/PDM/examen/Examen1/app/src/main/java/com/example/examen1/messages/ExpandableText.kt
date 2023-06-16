package com.example.examen1.messages

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun ExpandableText(text: String, expandableList: List<Item>, expanded: Boolean, onExpandClick: (text: String) -> Unit,
                   onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier.clickable(onClick = {
            onClick()
        }),
        style = TextStyle(
            fontSize = 30.sp
        )
    )
    if (expanded) {
        onExpandClick(expandableList[0].sender)
        expandableList.forEach { message ->
            Text(
                text = message.text + " " + message.sender + " " + message.read + " " + message.created,
                style = TextStyle(
                    fontSize = 20.sp
                )
            )
        }
    }
}