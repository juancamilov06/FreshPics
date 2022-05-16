package com.juanvilla.freshpic.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.ui.databinding.ItemGifBinding

class GifAdapter(
    private val context: Context,
    private val onFavoriteClicked: (Gif, Int) -> Unit
) : RecyclerView.Adapter<GifAdapter.TrendingViewHolder>() {

    private val items = mutableListOf<Gif>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Gif>) {
        this.items.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    fun updateFavorite(position: Int) {
        val item = items[position]
        val itemToUpdate = item.copy(
            isFavorite = !item.isFavorite
        )
        items[position] = itemToUpdate
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding = ItemGifBinding.inflate(LayoutInflater.from(context), parent, false)
        return TrendingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) = holder.setGif(
        items[position],
        position
    )

    override fun getItemCount(): Int = items.size

    inner class TrendingViewHolder(
        private val binding: ItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setGif(gif: Gif, position: Int) {
            binding.setFavoriteButton.setOnClickListener {
                onFavoriteClicked(gif, position)
            }
            Glide
                .with(binding.root)
                .asGif()
                .load(gif.image.url)
                .into(binding.gifImageView)
        }
    }
}