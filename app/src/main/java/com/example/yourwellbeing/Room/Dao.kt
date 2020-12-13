package com.example.yourwellbeing.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface chartDao{

    @Insert
    suspend fun enter(c:Chart)
    @Query("SELECT Cal_Active from Chart where Gender= :gender and Age= :age")
    suspend fun getCalorie_Active(gender:String,age:String):List<Int>
    @Query("SELECT Cal_Moderately from Chart where Gender= :gender and Age= :age")
    suspend fun getCalorie_Moderate(gender:String,age:String):List<Int>
    @Query("SELECT Cal_Sedentary from Chart where Gender= :gender and Age= :age")
    suspend fun getCalorie_Sedentary(gender:String,age:String):List<Int>
}