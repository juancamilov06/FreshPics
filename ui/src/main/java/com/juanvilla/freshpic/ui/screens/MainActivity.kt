package com.juanvilla.freshpic.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
import com.juanvilla.freshpic.ui.R
import com.juanvilla.freshpic.ui.screens.home.HomeActivity
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
        setStartDestinationBasedOnPrefs(ageControlPreferences)

    private fun setStartDestinationBasedOnPrefs(ageControlPreferences: AgeControlPreferences) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navGraph = navHostFragment.navController.navInflater.inflate(R.navigation.main_graph)

        navGraph.setStartDestination(
            if (ageControlPreferences.arePreferencesValid()) {
                R.id.homeActivity
            } else {
                R.id.ageFragment
            }
        )

        navHostFragment.navController.setGraph(
            navGraph,
            if (ageControlPreferences.arePreferencesValid()) {
                bundleOf(
                    HomeActivity.PARAM_SELECTED_RATING to ageControlPreferences.selectedRating
                )
            } else {
                bundleOf()
            }
        )
    }
}