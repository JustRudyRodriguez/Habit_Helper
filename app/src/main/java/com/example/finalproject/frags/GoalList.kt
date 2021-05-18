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

class GoalList : Fragment(R.layout.fragment_goal_list) {

    private lateinit var goalList: List<Habit>
    private lateinit var goalViewModel: HabitViewModel
    private lateinit var adapter: GoalAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = GoalAdapter()
        rv_habits.adapter = adapter
        rv_habits.layoutManager = LinearLayoutManager(context)

        goalViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

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

        setHasOptionsMenu(true)
        swipeToRefresh.setOnRefreshListener {
            adapter.setdata(goalList)
            swipeToRefresh.isRefreshing= false
        }
        //grabs button from fragment_goal_list
        Fab.setOnClickListener{
            findNavController().navigate(R.id.action_goalList_to_createGoal)
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