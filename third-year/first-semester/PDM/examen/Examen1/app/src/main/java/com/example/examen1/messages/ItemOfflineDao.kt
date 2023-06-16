package com.example.examen1.messages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemOfflineDao {
    @Query("SELECT * FROM ItemsOffline")
    fun getAll(): List<ItemOffline>

    @Query("SELECT * FROM ItemsOffline WHERE id = :id")
    fun findById(id: String): Flow<List<ItemOffline>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemOffline: ItemOffline)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<ItemOffline>)

    @Update
    suspend fun update(item: ItemOffline): Int

    @Query("DELETE FROM ItemsOffline WHERE id = :id")
    suspend fun deleteById(id: String): Int

    @Query("DELETE FROM ItemsOffline")
    suspend fun deleteAll()
}
