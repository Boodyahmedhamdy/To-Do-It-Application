package com.example.todoapplicationwithroomdb.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplicationwithroomdb.R
import com.example.todoapplicationwithroomdb.database.AppDatabase
import com.example.todoapplicationwithroomdb.database.Todo
import com.example.todoapplicationwithroomdb.databinding.FragmentHomeBinding
import com.example.todoapplicationwithroomdb.screens.TodoAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).todoDao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)
        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.onClickCreate.observe(viewLifecycleOwner) {
            if(it == true) {
                val action = HomeFragmentDirections.actionHomeFragmentToTodoPreviewDialog(Todo())
                findNavController().navigate(action)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            homeViewModel.onCreateTodo()
        }


    }

}