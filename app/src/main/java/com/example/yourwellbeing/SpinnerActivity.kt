package com.example.yourwellbeing

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_calorie_tracker_activity.*

class SpinnerActivity(val chnger:Changeview):Activity(),AdapterView .OnItemSelectedListener{
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        lateinit var foodname:String
        foodname=parent?.getItemAtPosition(pos).toString()
//        when(parent?.id){
//            R.id.morningsp1->{
//
//            }
//            R.id.morningsp2->{
//                Log.d("taging hun","morningsp2 called")
//            }
//        }
        chnger.onItemClick(parent,foodname)

    }
    interface Changeview {
        fun onItemClick(parent: AdapterView<*>?,foodname:String)
    }


}