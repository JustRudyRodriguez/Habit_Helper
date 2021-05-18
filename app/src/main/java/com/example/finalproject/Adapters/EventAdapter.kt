package com.example.finalproject.Adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Databasery.Event
import com.example.finalproject.Databasery.jointInfo
import com.example.finalproject.R
import kotlinx.android.synthetic.main.erecycle.view.*

class EventAdapter:RecyclerView.Adapter<EventAdapter.MyViewHolder>() {

    //var EventList = emptyList<Event>()
    var specifiedEvents = emptyList<Event>()
    val tag = "EventListAdapter"
    //Each individual element in the list is defined by a view holder object.
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
            itemView.Event_cardview.setOnClickListener{
                val position = adapterPosition
                Log.d(TAG,"Itemclicked at : $position")
                Log.d(TAG,"ID: ${specifiedEvents[position].Eid}")

                //Goallistdirection is created at compiletime.
                //This action is used to select a specific goal/habit and update or view anything about it.
                //actually I think they're made by the Nav.xml
                //This function is if I want to leave on Event selection. But I don't think I need that. yet.
                //for notes sake, this takes the item I'm clicking on into the next page.
                //val action = GoalListDirections.actionGoalListToUpdateGoal(EventList[position])
                //itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.erecycle,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return specifiedEvents.size
    }
    //this is where I bind the data to the erecylce view.
    //I may need to add EventAdapter before myviewholder here.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentevent = specifiedEvents[position]
        //holder.itemView.iv_habit_icon.setImageResource(currentGoal.imageid)
        holder.itemView.Tv_EGoalName.text = currentevent.Goalid.toString()
        // TODO: 5/18/2021 This is to add info to the cards on eventlist
       // holder.itemView.tv_item_description.text = currentevent.habit_description
        //holder.itemView.tv_timeElapsed.text = Calculations.calculateTimeBetweenDates(currentevent.habit_startTime);
      //  holder.itemView.tv_item_createdTimeStamp.text = "Since: ${currentevent.habit_startTime}"

    }

    fun setdata(event: List<Event>){

        this.specifiedEvents = event
        notifyDataSetChanged()
    }



}