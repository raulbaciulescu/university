package com.example.examen1.messages

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "itemsOffline")
data class ItemOffline(@PrimaryKey val id: Int = 0,
                val text: String = "",
                val read: Boolean = false,
                val sender: String = "",
                val created: Long = 0)