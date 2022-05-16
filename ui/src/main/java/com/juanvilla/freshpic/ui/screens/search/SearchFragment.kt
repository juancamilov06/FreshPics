package com.juanvilla.freshpic.ui.screens.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanvilla.freshpic.ui.databinding.FragmentSearchBinding
import com.juanvilla.freshpic.ui.screens.shared.GifAdapter
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoriteViewState
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoritesViewModel
import com.juanvilla.freshpic.ui.utils.onTextChangedListenerDebounced
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private val sharedFavoritesViewModel: SharedFavoritesViewModel by activityViewModels()

    private lateinit var adapter: GifAdapter
    private lateinit var binding: FragmentSearchBinding

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
        searchViewModel.searchViewStateSource.observe(viewLifecycleOwner) {
            when (it) {
                is SearchViewState.Loading -> {
                    binding.apply {
                        searchProgressBar.isVisible = true
                        searchRecyclerView.isVisible = false
                        hintTextView.isVisible = false
                    }
                }
                is SearchViewState.Empty -> {
                    binding.apply {
                        searchProgressBar.isVisible = false
                        searchRecyclerView.isVisible = false
                        hintTextView.isVisible = true
                    }
                }
                is SearchViewState.Success -> {
                    binding.apply {
                        searchProgressBar.isVisible = false
                        searchRecyclerView.isVisible = true
                        hintTextView.isVisible = false

                        searchRecyclerView.adapter = adapter
                        searchRecyclerView.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        adapter.setItems(it.data.gifs)
                    }
                }
                is SearchViewState.Error -> Log.d(TAG, it.error.message!!)
            }
        }
    }

    private fun setViews() {
        binding.searchInput.onTextChangedListenerDebounced({
            searchViewModel.findGifsByKeyword(it)
        }, lifecycleScope)
    }

    companion object {
        const val TAG = "SearchFragment"

        fun getInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}