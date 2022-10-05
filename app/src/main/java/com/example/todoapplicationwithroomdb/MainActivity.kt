package com.example.todoapplicationwithroomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.todoapplicationwithroomdb.database.AppDatabase
import com.example.todoapplicationwithroomdb.databinding.ActivityMainBinding
import com.example.todoapplicationwithroomdb.screens.home.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // hold the navigation controller
        navController = findNavController(R.id.fragmentContainerView)

        // hold the view model
        val application = requireNotNull(this).application
        val dataSource = AppDatabase.getInstance(application).todoDao
        val factory = MainViewModelFactory(dataSource, application)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this

        // observe data in view model
        // navigation related stuff
        mainViewModel.onHomeTab.observe(this) {
            if (it == true) {
                navController.navigate(R.id.homeFragment)
            }
        }
        mainViewModel.onFavorateTab.observe(this) {
            if(it == true) {
                navController.navigate(R.id.favorateFragment)
            }
        }
        mainViewModel.onFinishedTab.observe(this) {
            if(it == true) {
                navController.navigate(R.id.finishedFragment)
            }
        }

        // handling the menu actions with view model functions
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> mainViewModel.onClickOnHomeTab()
                R.id.miFavorate -> mainViewModel.onClickOnFavorateTab()
                R.id.miFinished -> mainViewModel.onClickOnFinishedTab()
            }
            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.miClearDatabase -> {
                mainViewModel.onClearDatabase()
                Toast.makeText(this, "database was cleared successfully," +
                        " please refresh the page",
                    Toast.LENGTH_LONG).show()
                if(mainViewModel.onHomeTab.value == true) {
                    navController.navigate(R.id.homeFragment)
                }
                if(mainViewModel.onFavorateTab.value == true) {
                    navController.navigate(R.id.favorateFragment)
                }
                if(mainViewModel.onFinishedTab.value == true) {
                    navController.navigate(R.id.finishedFragment)
                }
            }
        }

        return true
    }



}