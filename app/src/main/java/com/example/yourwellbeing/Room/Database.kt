package com.example.yourwellbeing.Room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Chart::class],version = 1)
abstract class Databases: RoomDatabase() {
    abstract fun getchartDao(): chartDao

    companion object {
        fun getInstance(application: Application): Databases {
            return Room.databaseBuilder(application,Databases::class.java,"calorie_chart").createFromAsset("caloriechart.db").build()
            }
        }
}
