package com.juanvilla.freshpic.ui.screens.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanvilla.freshpic.ui.databinding.FragmentSearchBinding
import com.juanvilla.freshpic.ui.screens.GifAdapter
import com.juanvilla.freshpic.ui.utils.onTextChangedListenerDebounced
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
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
        adapter = GifAdapter(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()

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