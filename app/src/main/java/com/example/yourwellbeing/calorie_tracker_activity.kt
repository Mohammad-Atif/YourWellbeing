package com.example.yourwellbeing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Database
import androidx.room.Room
import com.example.yourwellbeing.KEYS.EXTRA_AGE
import com.example.yourwellbeing.KEYS.EXTRA_GENDER
import com.example.yourwellbeing.KEYS.EXTRA_LIFE
import com.example.yourwellbeing.Room.Chart
import com.example.yourwellbeing.Room.Databases
import com.example.yourwellbeing.RoomFoodDb.FoodDatabase
import kotlinx.android.synthetic.main.activity_calorie_tracker_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class calorie_tracker_activity : AppCompatActivity() {

    lateinit var user_gender:String
    lateinit var user_age:String
    lateinit var user_lifestyle:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_tracker_activity)
        user_age= intent.getStringExtra(EXTRA_AGE).toString()
        user_gender= intent.getStringExtra(EXTRA_GENDER).toString()
        user_lifestyle= intent.getStringExtra(EXTRA_LIFE).toString()


        val db = Databases.getInstance(application)
        val newdb=FoodDatabase.getInstance(application)
        calculatebtn.setOnClickListener {
            CoroutineScope(IO).launch {
                lateinit var a:List<Int>
                val che = newdb.getFoodDao()    //till here app working perfectly
                a=che.getfoodCalorie("Coffee")
//                if(user_lifestyle=="Sedentary")
//                    a=che.getCalorie_Sedentary(user_gender.toString(),user_age.toString())
//                else if(user_lifestyle=="Moderately Active")
//                    a=che.getCalorie_Moderate(user_gender.toString(),user_age.toString())
//                else
//                    a=che.getCalorie_Active(user_gender.toString(),user_age.toString())     //now on calling this my app crashes
             withContext(Main){
                 println("Ans:${a[0].toString()}")
               }
            }
        }




    }

}
