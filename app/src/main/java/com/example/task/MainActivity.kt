package com.example.task

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ankittask.errorhandling.ResultState
import com.example.ankittask.viewmodel.ApiViewModel
import com.example.task.adapter.TaskAdapter
import com.example.task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: ApiViewModel by viewModels()
    private lateinit var adapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapter = TaskAdapter(this, arrayListOf())
        binding.rvTaskData.adapter = adapter


        setContentView(binding.root)
        enableEdgeToEdge()
        viewModel.getRepo(1,10)
        observeViewModel()
        initControl()
    }


    private fun observeViewModel() {
        viewModel.getRepo.observe(this) { item ->
            when (item) {
                is ResultState.Success -> {
                    binding.progressBar.isVisible = false
                    binding.rvTaskData.isVisible = true
                    adapter.addItems(item.data)
                }

                is ResultState.Error -> {
                    Log.d("ankit", "observeViewModel: ${item.exception}")
                    binding.progressBar.isVisible = false
                }

                is ResultState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.rvTaskData.isVisible = false
                    Log.d("ankit", "observeViewModel: Loading")


                }
            }
        }


    }

    private fun initControl() {
        binding.rvTaskData.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.rvTaskData.layoutManager as LinearLayoutManager
                if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {

                    viewModel.getRepo(viewModel.currentPage,5)
                }
            }
        })
    }

}