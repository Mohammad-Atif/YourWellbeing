package com.example.yourwellbeing.RoomFoodDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChartFood(
    @PrimaryKey
    val S_No:Int?,
    val Food_Name:String?,
    val In_Katori:Int?,
    val In_Piece:Int?,
    val Calorie:Int?
)