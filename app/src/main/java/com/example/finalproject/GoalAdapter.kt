package com.example.finalproject

import android.content.ContentValues.TAG
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycleritem.view.*

class GoalAdapter:RecyclerView.Adapter<GoalAdapter.MyViewHolder>() {

    var GoalList = emptyList<Habit>()
    val tag = "HabitListAdapter"
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
            itemView.cv_cardView.setOnClickListener{
                val position = adapterPosition
                Log.d(TAG,"Itemclicked at : $position")
                Log.d(TAG,"ID: ${GoalList[position].id}")

                //Goallistdirection is created at compiletime.
                //This action is used to select a specific goal/habit and update or view anything about it.
                val action = GoalListDirections.actionGoalListToUpdateGoal(GoalList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalAdapter.MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycleritem,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return GoalList.size
    }

    override fun onBindViewHolder(holder: GoalAdapter.MyViewHolder, position: Int) {
        val currentGoal=GoalList[position]
        //holder.itemView.iv_habit_icon.setImageResource(currentGoal.imageid)
        holder.itemView.tv_item_title.text = currentGoal.habit_title
        holder.itemView.tv_item_description.text = currentGoal.habit_description
        holder.itemView.tv_timeElapsed.text = Calculations.calculateTimeBetweenDates(currentGoal.habit_startTime);
        holder.itemView.tv_item_createdTimeStamp.text = "Since: ${currentGoal.habit_startTime}"

        }

    fun setdata(habit: List<Habit>){

        this.GoalList = habit
        notifyDataSetChanged()
    }

}