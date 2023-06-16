package com.example.examen1.messages

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.examen1.MyApplication
import kotlinx.coroutines.launch


data class MainUiState(
    var items: MutableList<Pair<String, List<Item>?>> = mutableListOf()
)

sealed interface MainUiStateInterface {
    data class Success(
        val items: MutableList<Pair<String, List<Item>?>>,
        val unreadNumberList: List<Int>
    ) : MainUiStateInterface
    data class Error(val exception: Throwable?) : MainUiStateInterface
    object Loading : MainUiStateInterface
}


class MainViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    var uiStateInterface: MainUiStateInterface by mutableStateOf(MainUiStateInterface.Loading)
        private set
    var uiState: MainUiState by mutableStateOf(MainUiState())
        private set

    init {
        Log.d(TAG, "init")
        loadItems()
    }

    private fun loadItems() {
        Log.d(TAG, "loadItems...")
        viewModelScope.launch {
            uiStateInterface = MainUiStateInterface.Loading
            itemRepository.refresh()
            itemRepository.itemStream.collect { messages ->
                Log.d(TAG, "loadItems messages $messages")
                val result = convertMessagesToMap(messages)
                val unreadNumberList = getUnreadNumber(result)
                Log.d(TAG, "loadItems collect $result")
                uiStateInterface = MainUiStateInterface.Success(result, unreadNumberList)
                uiState = uiState.copy(items = result)
            }
        }
    }

    private fun getUnreadNumber(userMap: MutableList<Pair<String, List<Item>?>>): List<Int> {
        val unreadNumberList = MutableList(userMap.size) { 0 }
        var contor = 0
        userMap.forEach {
            it.second!!.forEach { item ->
                if (!item.read)
                    unreadNumberList[contor] += 1
            }
            contor += 1
        }

        return unreadNumberList
    }

    private fun convertMessagesToMap(messages: List<Item>): MutableList<Pair<String, List<Item>?>> {
        val usernamesList = mutableListOf<String>()
        val sortedMessages = messages.sortedByDescending { it.created }
        sortedMessages.forEach {
            if (!usernamesList.contains(it.sender) && !it.read) {
                usernamesList.add(it.sender)
            }
        }

        Log.d("", "sorted messages: $sortedMessages")
        sortedMessages.forEach {
            if (!usernamesList.contains(it.sender)) {
                usernamesList.add(it.sender)
            }
        }
        Log.d("", "sorted messages: $usernamesList")
        val groupedByUsername: Map<String, List<Item>> = sortedMessages.groupBy { it.sender }
        val result = mutableListOf<Pair<String, List<Item>?>>()
        usernamesList.forEach {
            result.add(Pair(it, groupedByUsername[it]))
        }

        return result
    }

    fun onExpandClick(text: String) {
        Log.d(TAG, "expand click $text")
        uiState.items.forEach {
            if (it.first == text) {
                updateItems(it.second)
            }
        }
    }

    private fun updateItems(items: List<Item>?) {
        viewModelScope.launch {
            items!!.forEach { item ->
                if (!item.read)
                    itemRepository.update(item.copy(read = true))
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                MainViewModel(app.container.itemRepository)
            }
        }
    }
}