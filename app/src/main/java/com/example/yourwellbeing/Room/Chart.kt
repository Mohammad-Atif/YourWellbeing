package com.example.yourwellbeing.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Chart(
    @PrimaryKey
    val s_no:Int?,
    val Gender:String?,
    val Age:String?,
    val Cal_Moderately:Int?,
    val Cal_Sedentary:Int?,
    val Cal_Active:Int?
)