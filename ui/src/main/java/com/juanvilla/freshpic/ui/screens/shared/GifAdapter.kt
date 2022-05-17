package com.juanvilla.freshpic.ui.screens.shared

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.theme.R.color
import com.juanvilla.freshpic.ui.R
import com.juanvilla.freshpic.ui.databinding.ItemGifBinding
import kotlin.random.Random

class GifAdapter(
    private val context: Context,
    private val onLoadMore: (() -> Unit)?,
    private val onFavoriteClicked: (Gif) -> Unit,
    private val onItemClicked: (Gif) -> Unit
) : RecyclerView.Adapter<GifAdapter.TrendingViewHolder>() {

    private val items = mutableListOf<Gif>()
    private val placeholderColors = listOf(
        ColorDrawable(ContextCompat.getColor(context, color.colorRandomPlaceholderOne)),
        ColorDrawable(ContextCompat.getColor(context, color.colorRandomPlaceholderTwo)),
        ColorDrawable(ContextCompat.getColor(context, color.colorRandomPlaceholderThree)),
        ColorDrawable(ContextCompat.getColor(context, color.colorRandomPlaceholderFour)),
    )

    fun setFavoriteItems(newList: List<Gif>) {
        if (newList.size > this.items.size) {
            clear()
            setItems(newList)
        } else if (newList.size < this.items.size) {
            val newListSet = setOf(*newList.toTypedArray())
            val oldListSet = setOf(*this.items.toTypedArray())

            val itemToDelete = oldListSet.minus(newListSet).elementAt(0)
            val indexToDelete = this.items.indexOfFirst {
                it.id == itemToDelete.id
            }
            this.items.removeAt(indexToDelete)
            notifyItemRemoved(indexToDelete)
        }
    }

    fun clear() {
        this.items.clear()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Gif>) {
        this.items.apply {
            addAll(items)
        }
        notifyDataSetChanged()
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

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        if (position >= itemCount - 1) {
            onLoadMore?.let { it() }
        }
        holder.setGif(
            items[position]
        )
    }

    override fun getItemCount(): Int = items.size

    inner class TrendingViewHolder(
        private val binding: ItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setGif(gif: Gif) {
            binding.apply {

                gifImageView.setOnClickListener {
                    onItemClicked(gif)
                }

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
                    .placeholder(
                        placeholderColors[Random.nextInt(placeholderColors.size)]
                    )
                    .load(gif.image.url)
                    .into(gifImageView)
            }
        }
    }
}