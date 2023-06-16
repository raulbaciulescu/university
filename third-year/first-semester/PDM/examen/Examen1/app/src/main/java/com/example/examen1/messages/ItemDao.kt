package com.example.examen1.messages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM Items")
    fun getAll(): Flow<List<Item>>

    @Query("SELECT * FROM Items WHERE id = :id")
    fun findById(id: String): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Item>)

    @Update
    suspend fun update(item: Item): Int

    @Query("DELETE FROM Items WHERE id = :id")
    suspend fun deleteById(id: String): Int

    @Query("DELETE FROM Items")
    suspend fun deleteAll()
}
