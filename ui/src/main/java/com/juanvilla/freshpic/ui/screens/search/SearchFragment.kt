package com.juanvilla.freshpic.ui.screens.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanvilla.freshpic.domain.util.Constants
import com.juanvilla.freshpic.ui.R
import com.juanvilla.freshpic.ui.databinding.FragmentSearchBinding
import com.juanvilla.freshpic.ui.screens.home.HomeActivity
import com.juanvilla.freshpic.ui.screens.shared.GifAdapter
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoriteViewState
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoritesViewModel
import com.juanvilla.freshpic.ui.screens.trending.TrendingFragment
import com.juanvilla.freshpic.ui.utils.eventObserve
import com.juanvilla.freshpic.ui.utils.onTextChangedListenerDebounced
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private val sharedFavoritesViewModel: SharedFavoritesViewModel by activityViewModels()

    private lateinit var adapter: GifAdapter
    private lateinit var binding: FragmentSearchBinding
    private lateinit var selectedRating: String
    private lateinit var toolbarSearchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(
            inflater,
            container,
            false
        )
        toolbarSearchEditText = (activity as AppCompatActivity).findViewById(R.id.searchEditText)
        selectedRating =
            arguments?.getString(HomeActivity.PARAM_SELECTED_RATING) ?: Constants.PG13_RATING
        adapter = GifAdapter(
            requireContext(),
            {
                searchViewModel.findGifsByKeyword(
                    toolbarSearchEditText.text.toString(),
                    selectedRating
                )
            }
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
        outState.putBoolean(TrendingFragment.MADE_CONFIG_CHANGE_FIELD, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        sharedFavoritesViewModel.gifFavoriteStatusSource.observe(viewLifecycleOwner) {
            when (it) {
                is SharedFavoriteViewState.GifFavoriteStatusChanged -> {
                    searchViewModel.updateFavorite(it.gifId, it.isFavorite)
                    adapter.updateFavorite(it.gifId)
                }
            }
        }
        searchViewModel.searchViewStateSource.observe(viewLifecycleOwner) {
            when (it) {
                is SearchViewState.LoadingMore -> binding.apply {
                    loadingMoreSearchProgressBar.isVisible = true
                }
                is SearchViewState.Loading -> binding.apply {
                    searchProgressBar.isVisible = true
                    searchRecyclerView.isVisible = false
                    hintTextView.isVisible = false
                }

                is SearchViewState.Empty -> binding.apply {
                    searchProgressBar.isVisible = false
                    searchRecyclerView.isVisible = false
                    hintTextView.isVisible = true
                    loadingMoreSearchProgressBar.isVisible = false

                    adapter.clear()
                    adapter.notifyDataSetChanged()
                }

                is SearchViewState.Success -> binding.apply {
                    searchProgressBar.isVisible = false
                    searchRecyclerView.isVisible = true
                    hintTextView.isVisible = false
                    loadingMoreSearchProgressBar.isVisible = false

                    adapter.apply {
                        clear()
                        setItems(it.data)
                    }
                }
                is SearchViewState.Error -> Log.d(TAG, it.error.message!!)
            }
        }
    }

    private fun setViews() {
        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        toolbarSearchEditText.onTextChangedListenerDebounced({
            searchViewModel.findGifsByKeyword(it, selectedRating)
        }, lifecycleScope)
    }

    companion object {
        const val TAG = "SearchFragment"
        const val MADE_CONFIG_CHANGE_FIELD = "MADE_CONFIG_CHANGE"

        fun getInstance(
            selectedRating: String
        ): SearchFragment {
            return SearchFragment().apply {
                arguments = bundleOf(
                    HomeActivity.PARAM_SELECTED_RATING to selectedRating
                )
            }
        }
    }
}