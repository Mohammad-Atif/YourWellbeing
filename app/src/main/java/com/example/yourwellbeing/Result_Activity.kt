package com.example.yourwellbeing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yourwellbeing.KEYS.AVERAGE_CAL
import com.example.yourwellbeing.KEYS.CONSUMED_CAL
import kotlinx.android.synthetic.main.activity_result_.*

class Result_Activity : AppCompatActivity() {

    var calorieconsumed=0
    var averagecalorie=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_)

        calorieconsumed=intent.getIntExtra(CONSUMED_CAL,0)
        averagecalorie=intent.getIntExtra(AVERAGE_CAL,0)

        val resulttext="Average Calorie:${averagecalorie.toString()} Calorie Consumed:${calorieconsumed.toString()}"
        if(calorieconsumed<=averagecalorie)
        {
            if(averagecalorie-calorieconsumed>600)
            {
                resultquoteview.text= "Calorie count is very less from Average"
            }
            else
            {
                resultquoteview.text="Going Good !!!"
            }
        }
        else
        {
            if(calorieconsumed-averagecalorie>500)
            {
                resultquoteview.text="You have eaten a lot today"
            }
            else
            {
                resultquoteview.text="Going Good !!!"
            }
        }
        resultvalueview.text=resulttext



    }
}
