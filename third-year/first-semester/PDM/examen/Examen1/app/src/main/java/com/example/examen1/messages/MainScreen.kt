package com.example.examen1.messages

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun MainScreen() {
    val mainScreenViewModel = viewModel<MainViewModel>(factory = MainViewModel.Factory)
    val uiStateInterface = mainScreenViewModel.uiStateInterface
    var user: String by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Messages")
        })
    }) {
        when (uiStateInterface) {
            is MainUiStateInterface.Success -> {
                Column {
                    uiStateInterface.items.zip(uiStateInterface.unreadNumberList)
                        .forEach { bigPair ->
                            Column {
                                ExpandableText(text = "${bigPair.first.first} unread messages: [${bigPair.second}]",
                                    expandableList = bigPair.first.second!!,
                                    expanded = user == bigPair.first.first,
                                    onExpandClick = { text -> mainScreenViewModel.onExpandClick(text) },
                                    onClick = {
                                        user = if (user == bigPair.first.first) ""
                                        else bigPair.first.first
                                    })
                            }
                        }
                }
            }
            is MainUiStateInterface.Loading -> CircularProgressIndicator()
            is MainUiStateInterface.Error -> Text(text = "Failed to load items")
        }
    }
}
