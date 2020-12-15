package com.example.yourwellbeing

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import com.example.yourwellbeing.Calculations.calculate_cal_perfood

import com.example.yourwellbeing.Calculations.calculate_total_cal
import com.example.yourwellbeing.Calculations.resetlist
import com.example.yourwellbeing.KEYS.*

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
    var maxcal:Int=0
    var consumed_cal:Int=0

    var allfoods:MutableList<String> = mutableListOf()
    var origallfoods:MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_tracker_activity)
        user_age= intent.getStringExtra(EXTRA_AGE).toString()
        user_gender= intent.getStringExtra(EXTRA_GENDER).toString()
        user_lifestyle= intent.getStringExtra(EXTRA_LIFE).toString()


        allfoods=resetlist()


        val fooddb=FoodDatabase.getInstance(application)
        val db = Databases.getInstance(application)
        val che = fooddb.getFoodDao()
        val char_dao=db.getchartDao()

        CoroutineScope(IO).launch {
            lateinit var a:List<String>
            lateinit var maxcallist:List<Int>

                //till here app working perfectly
            a=che.getFoodList()

            val l:MutableList<String> = mutableListOf("Select")
            l.addAll(a)
                if(user_lifestyle=="Sedentary")
                    maxcallist=char_dao.getCalorie_Sedentary(user_gender.toString(),user_age.toString())
                else if(user_lifestyle=="Moderately Active")
                    maxcallist=char_dao.getCalorie_Moderate(user_gender.toString(),user_age.toString())
                else
                    maxcallist=char_dao.getCalorie_Active(user_gender.toString(),user_age.toString())
            maxcal=maxcallist[0]
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

        calculatebtn.setOnClickListener {

            origallfoods=resetlist()
            CoroutineScope(IO).launch {
                val f= allfoods.toList()
                val cal_perfood_list= calculate_cal_perfood(f,che)
                consumed_cal= calculate_total_cal(cal_perfood_list,
                    listOf(morningsp1count_txt.text.toString().toInt(),morningsp2count_txt.text.toString().toInt(),morningsp3count_txt.text.toString().toInt(),breakfastsp1count_txt.text.toString().toInt(),breakfastsp2count_txt.text.toString().toInt(),breakfastsp3count_txt.text.toString().toInt(),lunchsp1count_txt.text.toString().toInt(),lunchsp2count_txt.text.toString().toInt(),lunchsp3count_txt.text.toString().toInt(),eveningsp1count_txt.text.toString().toInt(),eveningsp2count_txt.text.toString().toInt(),eveningsp3count_txt.text.toString().toInt(),dinnersp1count_txt.text.toString().toInt(),dinnersp2count_txt.text.toString().toInt(),dinnersp3count_txt.text.toString().toInt()))
                withContext(Main){
                    //Toast.makeText(this@calorie_tracker_activity,"total: $maxcal consumed: $consumed_cal",Toast.LENGTH_SHORT).show()
                    if(!(allfoods.containsAll(origallfoods) && origallfoods.containsAll(allfoods))) {
                        val intent =
                            Intent(this@calorie_tracker_activity, Result_Activity::class.java)
                        intent.putExtra(AVERAGE_CAL, maxcal)
                        intent.putExtra(CONSUMED_CAL, consumed_cal)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this@calorie_tracker_activity,"Please select food",Toast.LENGTH_SHORT).show()
                    }
                }
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

    override fun onItemClick(parent: AdapterView<*>?,foodname: String,pos: Int) {
        lateinit var thelist:List<Int>
        if(foodname!="Select") {
            CoroutineScope(IO).launch {
                thelist = getfoodtype(foodname)
                withContext(Main) {
                    if (thelist[0] == 1)
                        settypeview(parent,"Katori",pos)
                    else
                        settypeview(parent,"Piece",pos)
                }
            }
        }
    }

    fun settypeview(parent: AdapterView<*>?,s:String,pos:Int)
    {
        when(parent?.id)
        {
            R.id.morningsp1-> {
                morning_intake_view1.text=s
                allfoods[0]=parent.getItemAtPosition(pos).toString()
            }
            R.id.morningsp2-> {
                morning_intake_view2.text =s
                allfoods[1]=parent.getItemAtPosition(pos).toString()
            }
            R.id.morningsp3->{
                morning_intake_view3.text=s
                allfoods[2]=parent.getItemAtPosition(pos).toString()
            }
            R.id.breakfastsp1->{
                breakfast_intake_view1.text=s
                allfoods[3]=parent.getItemAtPosition(pos).toString()
            }
            R.id.breakfastsp2->{
                breakfast_intake_view2.text=s
                allfoods[4]=parent.getItemAtPosition(pos).toString()
            }
            R.id.breakfastsp3->{
                breakfast_intake_view3.text=s
                allfoods[5]=parent.getItemAtPosition(pos).toString()
            }
            R.id.lunchsp1->{
                lunch_intake_view1.text=s
                allfoods[6]=parent.getItemAtPosition(pos).toString()
            }
            R.id.lunchsp2->{
                lunch_intake_view2.text=s
                allfoods[7]=parent.getItemAtPosition(pos).toString()
            }
            R.id.lunchsp3->{
                lunch_intake_view3.text=s
                allfoods[8]=parent.getItemAtPosition(pos).toString()
            }
            R.id.eveningsp1->{
                evening_intake_view1.text=s
                allfoods[9]=parent.getItemAtPosition(pos).toString()
            }
            R.id.eveningsp2->{
                evening_intake_view2.text=s
                allfoods[10]=parent.getItemAtPosition(pos).toString()
            }
            R.id.eveningsp3->{
                evening_intake_view3.text=s
                allfoods[11]=parent.getItemAtPosition(pos).toString()
            }
            R.id.dinnersp1->{
                dinner_intake_view1.text=s
                allfoods[12]=parent.getItemAtPosition(pos).toString()
            }
            R.id.dinnersp2->{
                dinner_intake_view2.text=s
                allfoods[13]=parent.getItemAtPosition(pos).toString()
            }
            R.id.dinnersp3->{
                dinner_intake_view3.text=s
                allfoods[14]=parent.getItemAtPosition(pos).toString()
            }
        }
    }

    suspend fun getfoodtype(foodname:String):List<Int>
    {
        val newdb=FoodDatabase.getInstance(application)
        val che = newdb.getFoodDao()
        val l=che.KatoriOrPiece(foodname)
        return l
    }
//    suspend fun calculate_cal_perfood(foods:List<String>):List<Int>
//    {
//        val che = fooddb.getFoodDao()
////    lateinit var l:List<Int>
//        var callist:MutableList<Int> = mutableListOf()
//        for( i in foods)
//        {
//            if(i!="Select") {
//                val l = che.getfoodCalorie(i)
//                callist.add(l[0])
//            }
//            else
//                callist.add(0)
//
//        }
//        return callist.toList()
//    }




}
