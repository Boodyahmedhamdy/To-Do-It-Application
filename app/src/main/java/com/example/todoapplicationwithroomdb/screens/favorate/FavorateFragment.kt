package com.example.todoapplicationwithroomdb.screens.favorate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplicationwithroomdb.R
import com.example.todoapplicationwithroomdb.database.AppDatabase
import com.example.todoapplicationwithroomdb.databinding.FragmentFavorateBinding

class FavorateFragment : Fragment() {

    private lateinit var binding: FragmentFavorateBinding
    private lateinit var favorateViewModel: FavorateViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).todoDao
        val factory = FavorateViewModelFactory(dataSource, application)
        favorateViewModel = ViewModelProvider(this, factory)[FavorateViewModel::class.java]
        binding = FragmentFavorateBinding.inflate(
            inflater, container, false
        )

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favorateViewModel = favorateViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


    }

}