package com.example.todoapplicationwithroomdb.screens.finished

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplicationwithroomdb.R
import com.example.todoapplicationwithroomdb.database.AppDatabase
import com.example.todoapplicationwithroomdb.databinding.FragmentFinishedBinding

class FinishedFragment : Fragment() {

    private lateinit var binding: FragmentFinishedBinding
    private lateinit var finishedViewModel: FinishedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).todoDao
        val factory = FinishedViewModelFactory(dataSource, application)
        finishedViewModel = ViewModelProvider(this, factory)[FinishedViewModel::class.java]
        binding = FragmentFinishedBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.finishedViewModel = finishedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
}