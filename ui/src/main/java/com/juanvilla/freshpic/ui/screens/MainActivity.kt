package com.juanvilla.freshpic.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
import com.juanvilla.freshpic.ui.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.preferencesSource.observe(
            this
        ) {
            onAgePreferencesUpdated(it)
        }

        mainViewModel.getSavedAgeControlPreferences()
    }

    private fun onAgePreferencesUpdated(ageControlPreferences: AgeControlPreferences) =
        setStartDestinationBasedOnPrefs(ageControlPreferences.arePreferencesValid())

    private fun setStartDestinationBasedOnPrefs(validPreferences: Boolean) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navGraph = navHostFragment.navController.navInflater.inflate(R.navigation.main_graph)

        navGraph.setStartDestination(
            if (validPreferences) {
                R.id.ageFragment
            } else {
                R.id.homeActivity
            }
        )

        navHostFragment.navController.graph = navGraph
    }
}