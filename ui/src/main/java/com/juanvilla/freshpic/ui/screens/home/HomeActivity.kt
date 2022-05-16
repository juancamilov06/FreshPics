package com.juanvilla.freshpic.ui.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.juanvilla.freshpic.ui.R
import com.juanvilla.freshpic.ui.databinding.ActivityHomeBinding
import com.juanvilla.freshpic.ui.screens.home.adapter.MainPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var mainPagerAdapter: MainPagerAdapter
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViews()
    }

    private fun setViews() {
        binding.apply {
            mainPagerAdapter = MainPagerAdapter(this@HomeActivity, 3)
            mainPager.adapter = mainPagerAdapter
            mainPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    mainNavView.selectedItemId = when (position) {
                        0 -> R.id.trendingMenuItem
                        1 -> R.id.searchMenuItem
                        2 -> R.id.favoritesMenuItem
                        else -> throw IllegalStateException("Invalid selection")
                    }
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

    companion object {
        const val PAGER_ITEM_COUNT = 3
    }
}