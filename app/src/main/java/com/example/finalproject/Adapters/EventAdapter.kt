package com.example.finalproject.Adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Calculations
import com.example.finalproject.Databasery.Event
import com.example.finalproject.R
import kotlinx.android.synthetic.main.erecycle.view.*
//This class interacts with the recycler view, and updates the data on the fly.
class EventAdapter:RecyclerView.Adapter<EventAdapter.MyViewHolder>() {

    //var EventList = emptyList<Event>()
    var specifiedEvents = emptyList<Event>()
    val tag = "EventListAdapter"
    //Each individual element in the list is defined by a view holder object.
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
            //This is an onclick listener for the events, I didn't have time to implement the features for clicking them.
            //mostly would of been for updating/deleting them .
            itemView.Event_cardview.setOnClickListener{
                val position = adapterPosition
                Log.d(TAG,"Itemclicked at : $position")
                Log.d(TAG,"ID: ${specifiedEvents[position].Eid}")

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
    //there we originally intended to store more data in our events, but we cut those for time.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        val currentevent = specifiedEvents[position]
        //holder.itemView.iv_habit_icon.setImageResource(currentGoal.imageid)
        holder.itemView.Tv_EDate.text = currentevent.type
        // TODO: 5/18/2021 This is to add info to the cards on eventlist
       //holder.itemView.tv_item_description.text = currentevent.habit_description
        holder.itemView.Tv_Eduration.text = "Duration: "+Calculations.getduration(currentevent.Event_startTime,currentevent.Event_endTime)
      //  holder.itemView.tv_item_createdTimeStamp.text = "Since: ${currentevent.habit_startTime}"

    }
    //this is called for each event to add to the recycler view.
    fun setdata(event: List<Event>){

        this.specifiedEvents = event
        notifyDataSetChanged()
    }



}