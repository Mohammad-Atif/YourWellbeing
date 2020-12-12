package com.example.yourwellbeing.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface chartDao{

    @Insert
    suspend fun enter(c:Chart)
    @Query("SELECT Cal_Active from Chart where Gender= :gender and Age= :age")
    suspend fun getCalorie(gender:String,age:String):List<Int>
}