package com.example.yourwellbeing.Calculations

import com.example.yourwellbeing.RoomFoodDb.FoodDao
import com.example.yourwellbeing.RoomFoodDb.FoodDatabase






suspend fun calculate_cal_perfood(foods:List<String>,foodsdao:FoodDao):List<Int>
{

//    lateinit var l:List<Int>
    var cal_list:MutableList<Int> = mutableListOf()
    for( i in foods)
    {
        if(i!="Select") {
            val l = foodsdao.getfoodCalorie(i)
            cal_list.add(l[0])
        }
        else
            cal_list.add(0)

    }
    return cal_list.toList()
}

fun calculate_total_cal(foodscal:List<Int>,count:List<Int>):Int
{
    var total:Int=0
    for(i in 0..14)
    {
        total=total+(foodscal[i]*count[i])
    }
    return total
}