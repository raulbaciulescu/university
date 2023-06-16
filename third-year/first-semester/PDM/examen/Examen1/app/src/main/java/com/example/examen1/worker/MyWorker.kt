package com.example.examen1.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.examen1.Api
import com.example.examen1.MyAppDatabase
import com.example.examen1.messages.Item
import com.example.examen1.messages.ItemApi
import com.example.examen1.messages.ItemOffline
import com.example.examen1.network.MyNetwork
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MyWorker(
    private val context: Context,
    private val workerParams: WorkerParameters
) : Worker(context, workerParams) {
    private val itemOfflineDao = MyAppDatabase.getDatabase(context = context).itemOfflineDao()
    private val itemApi: ItemApi = Api.retrofit.create(ItemApi::class.java)
    private val myNetwork: MyNetwork by lazy { MyNetwork(context) }

    override fun doWork(): Result {
        Log.d("MyWorker", "begin")
        var retry = false
        if (myNetwork.isNetworkAvailable()) {
            runBlocking {
                try {
                    val offlineItems = itemOfflineDao.getAll()
                    Log.d("MyWorker", "items size: " + offlineItems.size + " " + offlineItems[0].id)
                    if (offlineItems.isEmpty())
                            retry = true
                    offlineItems.forEach { offline ->
                        run {
                            itemApi.update(offline.id, Item(id = offline.id, text = offline.text, read = offline.read, sender = offline.sender, created = offline.created))
                            Log.d("MyWorker", "created: " + offline.id)
                        }
                    }
                    itemOfflineDao.deleteAll()
                    Log.d("MyWorker", "deleted")
                } catch (e: Exception) {
                    Log.d("MyWorker", "e: " + e.message)
                    retry = true
                }
            }
            Log.d("MyWorker", "end")
            return if (retry)
                Result.retry()
            else
                Result.success()
        }
        else
            return Result.retry()
    }
}