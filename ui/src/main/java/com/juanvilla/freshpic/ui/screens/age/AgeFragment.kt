package com.juanvilla.freshpic.ui.screens.age

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.juanvilla.freshpic.ui.R
import com.juanvilla.freshpic.ui.databinding.FragmentAgeBinding
import com.juanvilla.freshpic.ui.screens.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgeFragment : Fragment() {

    private val ageViewModel: AgeViewModel by viewModels()
    private lateinit var binding: FragmentAgeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgeBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()

        ageViewModel.preferencesSetSource.observe(
            viewLifecycleOwner
        ) { selectedRating ->
            selectedRating?.let {
                navigateToHome(it)
            } ?: Log.d(TAG, "Error setting prefs, restart app")
        }
    }

    private fun navigateToHome(selectedRating: String) {
        findNavController()
            .navigate(
                R.id.action_ageFragment_to_homeActivity,
                bundleOf(
                    HomeActivity.PARAM_SELECTED_RATING to selectedRating
                )
            )
    }

    private fun setViews() {
        binding.apply {
            legalBtn.setOnClickListener {
                ageViewModel.setAgePrefs(true)
            }
            notLegalBtn.setOnClickListener {
                ageViewModel.setAgePrefs(false)
            }
        }
    }

    companion object {
        const val TAG = "AgeFragment"
    }
}