package com.example.finalproject.Adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Calculations
import com.example.finalproject.Databasery.Habit
import com.example.finalproject.R
import com.example.finalproject.frags.GoalListDirections
import kotlinx.android.synthetic.main.recycleritem.view.*
//This adapter is how the recycler view updates it's data elements.
class GoalAdapter:RecyclerView.Adapter<GoalAdapter.MyViewHolder>() {
    //here we gather a list of all goals.
    var GoalList = emptyList<Habit>()
    val tag = "HabitListAdapter"
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
            //This onclick listener is how we can click on the Habits, and get the Eventslist
            itemView.cv_cardView.setOnClickListener{
                val position = adapterPosition
                Log.d(TAG,"Itemclicked at : $position")
                Log.d(TAG,"ID: ${GoalList[position].id}")

                //Goallistdirection is created at compiletime.
                //This action is used to select a specific goal/habit and update or view anything about it.

                val action = GoalListDirections.actionGoalListToEventView(GoalList[position])
                //We are taking the specific goal selected, and passing that as an arg.
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycleritem,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return GoalList.size
    }
    //this is where we do the binding for our cardview, so that each habit shows up correctly.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentGoal=GoalList[position]
        //holder.itemView.iv_habit_icon.setImageResource(currentGoal.imageid)
        holder.itemView.tv_item_title.text = currentGoal.habit_title
        holder.itemView.tv_item_description.text = currentGoal.habit_description
        holder.itemView.tv_timeElapsed.text =
            Calculations.calculateTimeBetweenDates(
                currentGoal.habit_startTime
            );
        holder.itemView.tv_item_createdTimeStamp.text = "Since: ${currentGoal.habit_startTime}"

        }
    //this is run for every habit, to add it to the view.
    fun setdata(habit: List<Habit>){

        this.GoalList = habit
        notifyDataSetChanged()
    }

}