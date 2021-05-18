package com.example.finalproject.frags



import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.Adapters.EventAdapter
import com.example.finalproject.Adapters.EventViewModel
import com.example.finalproject.Adapters.GoalAdapter
import com.example.finalproject.Databasery.Habit
import com.example.finalproject.Adapters.HabitViewModel
import com.example.finalproject.Databasery.Event
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_event_view.*
import kotlinx.android.synthetic.main.fragment_goal_list.*

class EventList : Fragment(R.layout.fragment_event_view) {


    private val args by navArgs<EventListArgs>()
    private lateinit var eventList: List<Event>
    private lateinit var eventViewModel: EventViewModel
    private lateinit var adapter: EventAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = EventAdapter()
        rv_Eventlist.adapter = adapter
        rv_Eventlist.layoutManager = LinearLayoutManager(context)

        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        eventViewModel.getAllEvents.observe(viewLifecycleOwner, Observer {
            adapter.setdata(it)
            eventList = it
            //Replace these text views
            // TODO: 5/18/2021  
            if (it.isEmpty()) {
                rv_Eventlist.visibility = View.GONE
            //    tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_Eventlist.visibility = View.VISIBLE
              //  tv_emptyView.visibility = View.GONE
            }
        })

        setHasOptionsMenu(true)
        swipeToRefreshEvent.setOnRefreshListener {
            adapter.setdata(eventList)
            swipeToRefreshEvent.isRefreshing= false
        }
        //uses button to pull up event creation menu, bringing parent habit with it as args.
        AddEventBtn.setOnClickListener{
            val action = EventListDirections.actionEventViewToCreateEvent(args.GoalArg)
            findNavController().navigate(action)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }

    /*Don't need this function because I don't want a delete all feature.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_delete -> goalViewModel.deleteallgoals()
        }

        return super.onOptionsItemSelected(item)
    }*/
}