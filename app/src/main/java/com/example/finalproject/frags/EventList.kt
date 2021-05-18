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
import com.example.finalproject.Databasery.jointInfo
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_event_view.*
import kotlinx.android.synthetic.main.fragment_goal_list.*
//This fragment is the view where you can see all the events for a specified Habit.
class EventList : Fragment(R.layout.fragment_event_view) {

    //this variable is the Habit we selected, it was sent here via the navigator.
    private val args by navArgs<EventListArgs>()

    private lateinit var eventList: List<Event>
    private lateinit var eventViewModel: EventViewModel
    private lateinit var adapter: EventAdapter
    private lateinit var jointlist: List<jointInfo>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //We add an adapter to our recycler view here, so we can update the elements.
        adapter = EventAdapter()
        rv_Eventlist.adapter = adapter
        rv_Eventlist.layoutManager = LinearLayoutManager(context)

        //This view model is how we access the data from our repository/DB.
        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        //here we specify we only want events for the habit we clicked on.
        eventViewModel.updatelist(args.GoalArg.id)

        //we add all events to our adapter through this function.
        eventViewModel.specifiedEvents.observe(viewLifecycleOwner, Observer {
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
        //This is to refresh on swipe, not needed unless we use a remote DB.
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
        //back button to main page.
        backToGoalbtn.setOnClickListener {
            findNavController().navigate(EventListDirections.actionEventViewToGoalList())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }


}