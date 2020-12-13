package com.example.yourwellbeing.RoomFoodDb

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodDao {

    @Query("select Calorie from ChartFood where Food_Name=:Food")
    suspend fun getfoodCalorie(Food:String):List<Int>
}