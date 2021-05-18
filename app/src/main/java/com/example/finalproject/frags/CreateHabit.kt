package com.example.finalproject.frags

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalproject.Calculations
import com.example.finalproject.Databasery.Habit
import com.example.finalproject.Adapters.HabitViewModel
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_create_goal.*
import java.util.*

//this fragment will capture data from the user, and format it to conform with DB requirements. .
class CreateHabit : Fragment(R.layout.fragment_create_goal),
    TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{

    private var title = ""
    private var description =""
    private var drawableSelected = 0
    private var timestamp = ""

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""

    private lateinit var habitViewModel: HabitViewModel

    override fun onViewCreated(view: View, SavedINstanceState: Bundle?){
        //We load in out viewmodel to interact with the DB.
        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        // this button submits the data to the db.
        CreateHabit_Btn.setOnClickListener {
            addHabitToDB()
        }

        pickDateAndTime()
        //Brings you back to the initial page without any edits.
        goalCancelButton.setOnClickListener{
            findNavController().navigate(R.id.action_createGoal_to_goalList)
        }


    }

    private fun addHabitToDB(){

        //Reads the data from the input areas, while checking to make sure they aren't empty
        //if all is well, we create a new HABIT, and add it to our database through the viewmodel.
        title = GoalTitle.text.toString()
        description = GoalDesc.text.toString()
        timestamp = "$cleanDate $cleanTime"
        if(!(title.isEmpty()||description.isEmpty()||timestamp.isEmpty())){
            val habit = Habit(
                0,
                title,
                description,
                timestamp,
                "Type"
            )
            habitViewModel.addHabit(habit)
            Toast.makeText(context,"Habit Created Successfully!",Toast.LENGTH_SHORT).show()
            //after the new habit is made, we navigate back to the main page.
            findNavController().navigate(R.id.action_createGoal_to_goalList)
            }else{
            Toast.makeText(context,"Please fill empty Fields",Toast.LENGTH_SHORT).show()
        }

    }


    //these functions are to format the dates before adding them to our database.
    fun pickDateAndTime() {
        PickDate_Btn.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(),this,year,month,day).show()

        }
        PickTime_Btn.setOnClickListener{
                getTimeCalandar()
                TimePickerDialog(context,this,hour,minute,true).show()
        }
    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime =
            Calculations.cleantime(hourOfDay, minute)
        textView2.text = "Time: $cleanTime"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(
            dayOfMonth,
            month,
            year
        )
        textView.text = "Date:$cleanDate"
    }

    private fun getTimeCalandar() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun getDateCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }
}