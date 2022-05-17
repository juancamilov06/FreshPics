package com.juanvilla.freshpic.ui.screens.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.juanvilla.freshpic.domain.util.ResultType
import com.juanvilla.freshpic.ui.databinding.FragmentFavoritesBinding
import com.juanvilla.freshpic.ui.screens.shared.GifAdapter
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val sharedFavoritesViewModel: SharedFavoritesViewModel by activityViewModels()
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private lateinit var adapter: GifAdapter
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(
            inflater,
            container,
            false
        )
        adapter = GifAdapter(
            requireContext(),
            onLoadMore = null,
            onFavoriteClicked = { gif ->
                sharedFavoritesViewModel.deleteGifFromFavorites(gif)
            },
            onItemClicked = {

            }
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        favoritesViewModel.fetchFavorites().observe(viewLifecycleOwner) {
            when (it) {
                is ResultType.Success -> {
                    binding.apply {
                        favoritesRecyclerView.isVisible = it.data.isNotEmpty()
                        favoritesProgressBar.isVisible = false
                        favoritesTextView.isVisible = it.data.isEmpty()
                    }
                    adapter.setFavoriteItems(it.data)
                }
            }
        }
    }

    private fun setViews() {
        binding.apply {
            favoritesRecyclerView.adapter = adapter
            favoritesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    companion object {
        const val TAG = "FavoritesFragment"
        fun getInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }
}