package com.juanvilla.freshpic.ui.screens.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.juanvilla.freshpic.ui.databinding.FragmentTrendingBinding
import com.juanvilla.freshpic.ui.screens.GifAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModels()
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
        adapter = GifAdapter(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trendingViewModel.trendingViewStateSource.observe(viewLifecycleOwner) {
            when (it) {
                is TrendingViewState.Loading -> binding.apply {
                    trendingProgressBar.isVisible = true
                    trendingRecyclerView.isVisible = false
                }
                is TrendingViewState.Success -> binding.apply {
                    trendingProgressBar.isVisible = false
                    trendingRecyclerView.isVisible = true

                    trendingRecyclerView.adapter = adapter
                    trendingRecyclerView.layoutManager = GridLayoutManager(context, 2)
                    adapter.setItems(it.data.gifs)
                }
                is TrendingViewState.Error -> Unit
            }
        }
        trendingViewModel.getTrendingGifs()
    }

    private fun setViews() {
    }

    companion object {
        fun getInstance(): TrendingFragment {
            return TrendingFragment()
        }
    }

}