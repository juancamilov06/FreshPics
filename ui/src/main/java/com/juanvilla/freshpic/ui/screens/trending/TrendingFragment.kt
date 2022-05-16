package com.juanvilla.freshpic.ui.screens.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.juanvilla.freshpic.ui.databinding.FragmentTrendingBinding
import com.juanvilla.freshpic.ui.screens.shared.GifAdapter
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoriteViewState
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModels()
    private val sharedFavoritesViewModel: SharedFavoritesViewModel by activityViewModels()

    private lateinit var adapter: GifAdapter
    private lateinit var binding: FragmentTrendingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendingBinding.inflate(
            inflater,
            container,
            false
        )
        adapter = GifAdapter(requireContext()) { gif ->
            if (gif.isFavorite) {
                sharedFavoritesViewModel.deleteGifFromFavorites(gif)
            } else {
                sharedFavoritesViewModel.saveGifInFavorites(gif)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        sharedFavoritesViewModel.gifFavoriteStatusSource.observe(viewLifecycleOwner) {
            when (it) {
                is SharedFavoriteViewState.GifFavoriteStatusChanged -> {
                    adapter.updateFavorite(it.gifId)
                }
            }
        }
        trendingViewModel.trendingViewStateSource.observe(viewLifecycleOwner) {
            when (it) {
                is TrendingViewState.Loading -> binding.apply {
                    trendingProgressBar.isVisible = true
                    trendingRecyclerView.isVisible = false
                }
                is TrendingViewState.Success -> binding.apply {
                    trendingProgressBar.isVisible = false
                    trendingRecyclerView.isVisible = true
                    adapter.setItems(it.data.gifs)
                }
                is TrendingViewState.Error -> Unit
            }
        }
        trendingViewModel.getTrendingGifs()
    }

    private fun setViews() {
        binding.apply {
            trendingRecyclerView.adapter = adapter
            trendingRecyclerView.layoutManager = GridLayoutManager(context, 2)
        }
    }

    companion object {
        const val TAG = "TrendingFragment"
        fun getInstance(): TrendingFragment {
            return TrendingFragment()
        }
    }

}