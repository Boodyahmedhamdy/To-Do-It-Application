package com.example.todoapplicationwithroomdb.screens.todopreview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapplicationwithroomdb.database.AppDatabase
import com.example.todoapplicationwithroomdb.databinding.TodoPreviewDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TodoPreviewDialog: BottomSheetDialogFragment() {

    private lateinit var binding: TodoPreviewDialogBinding
    private lateinit var todoPreviewDialogViewModel: TodoPreviewDialogViewModel
    private val args: TodoPreviewDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TodoPreviewDialogBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).todoDao
        val passedTodo = args.todo
        val factory = TodoPreviewDialogViewModelFactory(dataSource, application, passedTodo)
        todoPreviewDialogViewModel = ViewModelProvider(this, factory)[TodoPreviewDialogViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.todoPreviewViewModel = todoPreviewDialogViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        // when click cancel nothing happen
        todoPreviewDialogViewModel.onClickCancel.observe(viewLifecycleOwner) {
            if(it == true) {
                val action = TodoPreviewDialogDirections.actionTodoPreviewDialogToHomeFragment()
                findNavController().navigate(action)
            }
        }

        todoPreviewDialogViewModel.onClickCreate.observe(viewLifecycleOwner) {
            if(it == true) {
                updateTodoValues()
                val action = TodoPreviewDialogDirections.actionTodoPreviewDialogToHomeFragment()
                findNavController().navigate(action)
            }
        }




    }

    private fun updateTodoValues() {
        todoPreviewDialogViewModel.todo.title = binding.etTodoTitle.text.toString()
        todoPreviewDialogViewModel.todo.isFavorite = binding.cbIsFavorate.isChecked
        todoPreviewDialogViewModel.todo.isFinished = binding.cbMarkAsFinished.isChecked
    }

}