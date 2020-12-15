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

        supportActionBar?.hide()

        calorieconsumed=intent.getIntExtra(CONSUMED_CAL,0)
        averagecalorie=intent.getIntExtra(AVERAGE_CAL,0)

        val resulttext="Average Calorie:${averagecalorie.toString()}Cal"
        val consumedtext="Calorie Consumed:${calorieconsumed.toString()}Cal"
        if(calorieconsumed<=averagecalorie)
        {
            if(averagecalorie-calorieconsumed>600)
            {
                resultquoteview.text= "Calorie count is very less from Average"
                healthstat_image.setImageResource(R.drawable.ic_eatenless)
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
                healthstat_image.setImageResource(R.drawable.ic_eatenlot)
            }
            else
            {
                resultquoteview.text="Going Good !!!"
            }
        }
        max_resultview.text=resulttext
        con_resultview.text=consumedtext



    }
}
