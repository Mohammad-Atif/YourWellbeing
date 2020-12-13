package com.example.yourwellbeing.RoomFoodDb

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ChartFood::class],version = 1)
abstract class FoodDatabase : RoomDatabase(){
    abstract fun getFoodDao(): FoodDao
    companion object {
        fun getInstance(application: Application): FoodDatabase {
            return Room.databaseBuilder(application,FoodDatabase::class.java,"food_calorie_chart").createFromAsset("foodchart.db").build()
        }
    }
}