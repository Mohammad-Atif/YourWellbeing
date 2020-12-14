package com.example.yourwellbeing.RoomFoodDb

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodDao {

    @Query("select Calorie from ChartFood where Food_Name=:Food")
    suspend fun getfoodCalorie(Food:String):List<Int>
    @Query("select Food_Name from ChartFood")
    suspend fun getFoodList():List<String>
    @Query("select In_Katori from ChartFood where Food_Name=:Food ")
    suspend fun KatoriOrPiece(Food: String):List<Int>
}