package com.example.finalproject.frags

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.Adapters.GoalAdapter
import com.example.finalproject.Databasery.Habit
import com.example.finalproject.Adapters.HabitViewModel
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_goal_list.*
//This fragment is the host page, it contains a list of goals/habits you have crated already.
//from here you can either go to eventlist or the createHabit fragments.
class GoalList : Fragment(R.layout.fragment_goal_list) {

    private lateinit var goalList: List<Habit>
    private lateinit var goalViewModel: HabitViewModel
    private lateinit var adapter: GoalAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //this adapter is used to populate the recyclerview from this fragment.
        adapter = GoalAdapter()
        rv_habits.adapter = adapter
        rv_habits.layoutManager = LinearLayoutManager(context)
        //This view model interacts with the data repository.
        goalViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        // This function will go an get all current habits, and apply them to our adapter so the
        //recycler view can view them.
        goalViewModel.getAllHabits.observe(viewLifecycleOwner, Observer {
            adapter.setdata(it)
            goalList = it

            if (it.isEmpty()) {
                rv_habits.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_habits.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }
        })
        //This is for refreshing our recycler view, in our case it's not very useful. but if we we're working
        //with a remote DB, this would be needed.
        setHasOptionsMenu(true)
        swipeToRefresh.setOnRefreshListener {
            adapter.setdata(goalList)
            swipeToRefresh.isRefreshing= false
        }
        //grabs button from fragment_goal_list
        Fab.setOnClickListener{
            // uses nav controller to take you to the creat goal page. createhabit fragment.
            findNavController().navigate(R.id.action_goalList_to_createGoal)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }


    }