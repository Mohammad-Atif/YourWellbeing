package com.example.yourwellbeing

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
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

class calorie_tracker_activity : AppCompatActivity() ,SpinnerActivity.Changeview{

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

        CoroutineScope(IO).launch {
            lateinit var a:List<String>
            val che = newdb.getFoodDao()    //till here app working perfectly
            a=che.getFoodList()
            val l:MutableList<String> = mutableListOf("Select")
            l.addAll(a)
//                if(user_lifestyle=="Sedentary")
//                    a=che.getCalorie_Sedentary(user_gender.toString(),user_age.toString())
//                else if(user_lifestyle=="Moderately Active")
//                    a=che.getCalorie_Moderate(user_gender.toString(),user_age.toString())
//                else
//                    a=che.getCalorie_Active(user_gender.toString(),user_age.toString())     //now on calling this my app crashes
            withContext(Main){
                val adap=ArrayAdapter<String>(this@calorie_tracker_activity,R.layout.support_simple_spinner_dropdown_item,l)
                morningsp1.adapter=adap
                morningsp2.adapter=adap
                morningsp3.adapter=adap
                breakfastsp1.adapter=adap
                breakfastsp2.adapter=adap
                breakfastsp3.adapter=adap
                lunchsp1.adapter=adap
                lunchsp2.adapter=adap
                lunchsp3.adapter=adap
                eveningsp1.adapter=adap
                eveningsp2.adapter=adap
                eveningsp3.adapter=adap
                dinnersp1.adapter=adap
                dinnersp2.adapter=adap
                dinnersp3.adapter=adap

            }
        }


        morningsp1.onItemSelectedListener=SpinnerActivity(this)
        morningsp2.onItemSelectedListener=SpinnerActivity(this)
        morningsp3.onItemSelectedListener=SpinnerActivity(this)
        breakfastsp1.onItemSelectedListener=SpinnerActivity(this)
        breakfastsp2.onItemSelectedListener=SpinnerActivity(this)
        breakfastsp3.onItemSelectedListener=SpinnerActivity(this)
        lunchsp1.onItemSelectedListener=SpinnerActivity(this)
        lunchsp2.onItemSelectedListener=SpinnerActivity(this)
        lunchsp3.onItemSelectedListener=SpinnerActivity(this)
        eveningsp1.onItemSelectedListener=SpinnerActivity(this)
        eveningsp2.onItemSelectedListener=SpinnerActivity(this)
        eveningsp3.onItemSelectedListener=SpinnerActivity(this)
        dinnersp1.onItemSelectedListener=SpinnerActivity(this)
        dinnersp2.onItemSelectedListener=SpinnerActivity(this)
        dinnersp3.onItemSelectedListener=SpinnerActivity(this)


    }

    override fun onItemClick(parent: AdapterView<*>?,foodname: String) {
        lateinit var thelist:List<Int>
        if(foodname!="Select") {
            CoroutineScope(IO).launch {
                thelist = getfoodtype(foodname)
                withContext(Main) {
                    if (thelist[0] == 1)
                        settypeview(parent,"Katori")
                    else
                        settypeview(parent,"Piece")
                }
            }
        }
    }

    fun settypeview(parent: AdapterView<*>?,s:String)
    {
        when(parent?.id)
        {
            R.id.morningsp1-> morning_intake_view1.text=s
            R.id.morningsp2->morning_intake_view2.text=s
            R.id.morningsp3->morning_intake_view3.text=s
            R.id.breakfastsp1->breakfast_intake_view1.text=s
            R.id.breakfastsp2->breakfast_intake_view2.text=s
            R.id.breakfastsp3->breakfast_intake_view3.text=s
            R.id.lunchsp1->lunch_intake_view1.text=s
            R.id.lunchsp2->lunch_intake_view2.text=s
            R.id.lunchsp3->lunch_intake_view3.text=s
            R.id.eveningsp1->evening_intake_view1.text=s
            R.id.eveningsp2->evening_intake_view2.text=s
            R.id.eveningsp3->evening_intake_view3.text=s
            R.id.dinnersp1->dinner_intake_view1.text=s
            R.id.dinnersp2->dinner_intake_view2.text=s
            R.id.dinnersp3->dinner_intake_view3.text=s
        }
    }

    suspend fun getfoodtype(foodname:String):List<Int>
    {
        val newdb=FoodDatabase.getInstance(application)
        val che = newdb.getFoodDao()
        val l=che.KatoriOrPiece(foodname)
        return l
    }



}
