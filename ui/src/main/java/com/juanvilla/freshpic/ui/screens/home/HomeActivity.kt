package com.juanvilla.freshpic.ui.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.juanvilla.freshpic.domain.util.Constants
import com.juanvilla.freshpic.ui.R
import com.juanvilla.freshpic.ui.databinding.ActivityHomeBinding
import com.juanvilla.freshpic.ui.screens.home.adapter.MainPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var mainPagerAdapter: MainPagerAdapter
    private lateinit var binding: ActivityHomeBinding

    private lateinit var selectedRating: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        selectedRating = intent.extras?.getString(PARAM_SELECTED_RATING) ?: Constants.PG13_RATING
        setViews()
    }

    private fun setViews() {
        binding.apply {
            mainPagerAdapter = MainPagerAdapter(this@HomeActivity, 3, selectedRating)
            mainPager.adapter = mainPagerAdapter
            mainPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    mainNavView.selectedItemId = when (position) {
                        0 -> R.id.trendingMenuItem
                        1 -> R.id.searchMenuItem
                        2 -> R.id.favoritesMenuItem
                        else -> throw IllegalStateException("Invalid selection")
                    }
                    setToolbarContentBasedOnPage(position)
                    super.onPageSelected(position)
                }
            })

            mainNavView.setOnItemSelectedListener {
                mainPager.currentItem = when (it.itemId) {
                    R.id.trendingMenuItem -> 0
                    R.id.searchMenuItem -> 1
                    R.id.favoritesMenuItem -> 2
                    else -> throw IllegalStateException("Invalid menu selection")
                }
                true
            }
        }
    }

    private fun setToolbarContentBasedOnPage(page: Int) {
        binding.apply {
            mainToolbarTitle.text = when (page) {
                0 -> {
                    binding.searchEditText.isVisible = false
                    "Trending"
                }
                1 -> {
                    binding.searchEditText.isVisible = true
                    "Search"
                }
                2 -> {
                    binding.searchEditText.isVisible = false
                    "My Favorites"
                }
                else -> throw IllegalStateException("Invalid menu selection")
            }
        }
    }

    companion object {
        const val PARAM_SELECTED_RATING = "SELECTED_RATING"
        const val PAGER_ITEM_COUNT = 3
    }
}