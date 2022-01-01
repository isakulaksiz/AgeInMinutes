package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_date_picker.setOnClickListener{view->
            clickDatePicker(view)
        }
    }
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view,selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,"The chosen year is $year the month is $month and the day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tv_selected_date.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time/60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateMinutes = currentDate!!.time/60000

                val differenceInMinutes = currentDateMinutes - selectedDateInMinutes
                tv_selected_date_minute.setText(differenceInMinutes.toString())
            }, year, month, day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }

}