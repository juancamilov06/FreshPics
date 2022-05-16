package com.juanvilla.freshpic.ui.screens.shared

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.ui.R
import com.juanvilla.freshpic.ui.databinding.ItemGifBinding

class GifAdapter(
    private val context: Context,
    private val onFavoriteClicked: (Gif) -> Unit
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

    fun updateFavorite(id: String) {
        val itemPosition = items.indexOfFirst {
            it.id == id
        }
        if (itemPosition > -1) {
            val item = items[itemPosition]
            val itemToUpdate = item.copy(
                isFavorite = !item.isFavorite
            )
            items[itemPosition] = itemToUpdate
            notifyItemChanged(itemPosition)
        }
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
            binding.apply {
                setFavoriteButton.setOnClickListener {
                    onFavoriteClicked(gif)
                }

                setFavoriteButton.icon = if (gif.isFavorite) {
                    AppCompatResources.getDrawable(context, R.drawable.ic_favorite_fill)
                } else {
                    AppCompatResources.getDrawable(context, R.drawable.ic_favorite)
                }

                Glide
                    .with(root)
                    .asGif()
                    .load(gif.image.url)
                    .into(gifImageView)
            }
        }
    }
}