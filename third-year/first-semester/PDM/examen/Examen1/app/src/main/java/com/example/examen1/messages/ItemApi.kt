package com.example.examen1.messages

import retrofit2.http.*

interface ItemApi {
    @GET("/message")
    suspend fun findAll(): List<Item>

    @Headers("Content-Type: application/json")
    @PUT("/message/{id}")
    suspend fun update(@Path("id") itemId: Int?, @Body item: Item): Item
}
