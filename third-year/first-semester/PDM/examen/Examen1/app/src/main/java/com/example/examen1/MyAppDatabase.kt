package com.example.examen1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.examen1.messages.Item
import com.example.examen1.messages.ItemDao
import com.example.examen1.messages.ItemOffline
import com.example.examen1.messages.ItemOfflineDao

@Database(entities = arrayOf(Item::class, ItemOffline::class), version = 1)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun itemOfflineDao(): ItemOfflineDao

    companion object {
        @Volatile
        private var INSTANCE: MyAppDatabase? = null

        fun getDatabase(context: Context): MyAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MyAppDatabase::class.java,
                    "exam1_db2"
                ) .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}