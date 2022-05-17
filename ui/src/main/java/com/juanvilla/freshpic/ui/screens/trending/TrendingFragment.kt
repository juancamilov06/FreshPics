package com.juanvilla.freshpic.ui.screens.trending

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.util.Constants
import com.juanvilla.freshpic.ui.databinding.FragmentTrendingBinding
import com.juanvilla.freshpic.ui.screens.home.HomeActivity
import com.juanvilla.freshpic.ui.screens.shared.GifAdapter
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoriteViewState
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoritesViewModel
import com.juanvilla.freshpic.ui.utils.eventObserve
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModels()
    private val sharedFavoritesViewModel: SharedFavoritesViewModel by activityViewModels()

    private var currentOffset = 0

    private lateinit var adapter: GifAdapter
    private lateinit var binding: FragmentTrendingBinding
    private lateinit var selectedRating: String

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
        selectedRating =
            arguments?.getString(HomeActivity.PARAM_SELECTED_RATING) ?: Constants.PG13_RATING
        adapter = GifAdapter(
            requireContext(),
            { trendingViewModel.getTrendingGifs(selectedRating) }
        ) { gif ->
            if (gif.isFavorite) {
                sharedFavoritesViewModel.deleteGifFromFavorites(gif)
            } else {
                sharedFavoritesViewModel.saveGifInFavorites(gif)
            }
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(MADE_CONFIG_CHANGE_FIELD, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        sharedFavoritesViewModel.gifFavoriteStatusSource.eventObserve(viewLifecycleOwner) {
            when (it) {
                is SharedFavoriteViewState.GifFavoriteStatusChanged -> {
                    trendingViewModel.updateFavorite(it.gifId)
                    adapter.updateFavorite(it.gifId)
                }
            }
        }
        trendingViewModel.trendingViewStateSource.observe(viewLifecycleOwner) {
            when (it) {
                is TrendingViewState.LoadingMore -> binding.apply {
                    loadingMoreTrendingProgressBar.isVisible = true
                }
                is TrendingViewState.Loading -> binding.apply {
                    trendingProgressBar.isVisible = true
                    trendingRecyclerView.isVisible = false
                }
                is TrendingViewState.Success -> binding.apply {
                    trendingProgressBar.isVisible = false
                    trendingRecyclerView.isVisible = true
                    loadingMoreTrendingProgressBar.isVisible = false

                    adapter.clear()
                    adapter.setItems(it.data)
                }
                is TrendingViewState.Error -> Unit
            }
        }

        if (savedInstanceState == null) {
            trendingViewModel.getTrendingGifs(selectedRating)
        }
    }

    private fun setViews() {
        binding.apply {
            val layoutManager = GridLayoutManager(context, 2)
            adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            trendingRecyclerView.adapter = adapter
            trendingRecyclerView.layoutManager = layoutManager
        }
    }

    companion object {
        const val TAG = "TrendingFragment"
        const val MADE_CONFIG_CHANGE_FIELD = "MADE_CONFIG_CHANGE"

        fun getInstance(
            selectedRating: String
        ): TrendingFragment {
            return TrendingFragment().apply {
                arguments = bundleOf(
                    HomeActivity.PARAM_SELECTED_RATING to selectedRating
                )
            }
        }
    }

}