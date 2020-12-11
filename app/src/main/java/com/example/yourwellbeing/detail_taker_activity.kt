package com.example.yourwellbeing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.yourwellbeing.KEYS.EXTRA_AGE
import com.example.yourwellbeing.KEYS.EXTRA_GENDER
import com.example.yourwellbeing.KEYS.EXTRA_LIFE
import kotlinx.android.synthetic.main.activity_detail_taker_activity.*

class detail_taker_activity : AppCompatActivity() {

    private lateinit var gender:String
    private lateinit var age:String
    private lateinit var lifestyle:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_taker_activity)


        gender_spin.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                gender=adapterView?.getItemAtPosition(position).toString()
            }
        }
        age_spin.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                age=adapterView?.getItemAtPosition(position).toString()
            }
        }
        lifestyle_spin.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                lifestyle=adapterView?.getItemAtPosition(position).toString()
            }
        }

        continuebtn.setOnClickListener {
            if(gender!="Select*" && age!="Select*" && lifestyle!="Select*") {
                val intent = Intent(this, calorie_tracker_activity::class.java)
                intent.putExtra(EXTRA_AGE, age)
                intent.putExtra(EXTRA_GENDER, gender)
                intent.putExtra(EXTRA_LIFE, lifestyle)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this,"Please Select All",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
