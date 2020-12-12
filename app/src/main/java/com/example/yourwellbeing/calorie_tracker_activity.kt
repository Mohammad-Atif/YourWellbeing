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
        getbtn.setOnClickListener {
            CoroutineScope(IO).launch {
                val che = db.getchartDao()    //till here app working perfectly

                val a=che.getCalorie("Male","51+")     //now on calling this my app crashes
             withContext(Main){
                 println("${a[0].toString()}")
               }
            }
        }




    }

}
