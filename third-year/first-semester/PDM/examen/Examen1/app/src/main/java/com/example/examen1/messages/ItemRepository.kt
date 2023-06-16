package com.example.examen1.messages

import android.util.Log
import com.example.examen1.network.MyNetwork
import com.example.examen1.ws.ItemWsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext


class ItemRepository(
    private val itemApi: ItemApi,
    private val itemDao: ItemDao,
    private val itemOfflineDao: ItemOfflineDao,
    private val itemWsClient: ItemWsClient,
    private val myNetwork: MyNetwork
) {
    val itemStream by lazy { itemDao.getAll() }

    suspend fun refresh() {
        Log.d(TAG, "refresh started")
        try {
            val items = itemApi.findAll()
            itemDao.deleteAll()
            items.forEach { itemDao.insert(it) }
            Log.d(TAG, "refresh succeeded")
        } catch (e: Exception) {
            Log.w(TAG, "refresh failed", e)
        }
    }

    suspend fun openWsClient() {
        Log.d(TAG, "openWsClient")
        withContext(Dispatchers.IO) {
            getItemEvents().collect {
                Log.d(TAG, "Item event collected $it")
                if (it.isSuccess) {
                    val itemEvent = it.getOrNull()
                    if (itemEvent != null) {
                        Log.d("fffffffff", "$itemEvent")
                        handleItemCreated(itemEvent)
                    }
                }
            }
        }
    }

    suspend fun closeWsClient() {
        Log.d(TAG, "closeWsClient")
        withContext(Dispatchers.IO) {
            itemWsClient.closeSocket()
        }
    }

    private suspend fun getItemEvents(): Flow<Result<Item>> = callbackFlow {
        Log.d(TAG, "getItemEvents started")
        itemWsClient.openSocket(
            onEvent = {
                Log.d(TAG, "onEvent $it")
                if (it != null) {
                    trySend(Result.success(it))
                }
            },
            onClosed = { close() },
            onFailure = { close() });
        awaitClose { itemWsClient.closeSocket() }
    }

    private suspend fun handleItemCreated(item: Item) {
        Log.d(TAG, "handleItemCreated... $item")
        itemDao.insert(item)
    }

    suspend fun update(item: Item) {
        if (myNetwork.isNetworkAvailable()) {
            Log.d(TAG, "available internet")
            val updatedItem = itemApi.update(item.id, item)
            Log.d(TAG, "updated item $updatedItem")
            itemDao.update(updatedItem)
        } else {
            Log.d(TAG, "no internet")
            itemOfflineDao.insert(ItemOffline(id = item.id, text = item.text, read = item.read, sender = item.sender, created = item.created))
            itemDao.update(item)
        }
    }
}