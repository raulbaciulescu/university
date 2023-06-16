package com.example.examen1
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.examen1.messages.ItemApi
import com.example.examen1.messages.ItemRepository
import com.example.examen1.network.MyNetwork
import com.example.examen1.ws.ItemWsClient


class AppContainer(private val context: Context) {
    private val itemApi: ItemApi = Api.retrofit.create(ItemApi::class.java)
    private val itemWsClient: ItemWsClient = ItemWsClient(Api.okHttpClient)
    private val myNetwork: MyNetwork by lazy { MyNetwork(context) }
    val database: MyAppDatabase by lazy { MyAppDatabase.getDatabase(context) }

    val itemRepository: ItemRepository by lazy {
        ItemRepository(itemApi, database.itemDao(), database.itemOfflineDao(), itemWsClient, myNetwork)
    }
}
