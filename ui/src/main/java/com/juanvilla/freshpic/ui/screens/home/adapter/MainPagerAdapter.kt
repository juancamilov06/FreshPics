package com.juanvilla.freshpic.ui.screens.home.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.juanvilla.freshpic.ui.screens.favorites.FavoritesFragment
import com.juanvilla.freshpic.ui.screens.search.SearchFragment
import com.juanvilla.freshpic.ui.screens.trending.TrendingFragment
import java.lang.IllegalStateException

class MainPagerAdapter(
    activity: AppCompatActivity,
    private val count: Int
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> TrendingFragment.getInstance()
            1 -> SearchFragment.getInstance()
            2 -> FavoritesFragment.getInstance()
            else -> throw IllegalStateException("Can't render fragment position")
        }
    }
}