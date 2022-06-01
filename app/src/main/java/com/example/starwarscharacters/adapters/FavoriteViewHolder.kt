package com.example.starwarscharacters.adapters

import android.view.View
import com.example.starwarscharacters.databinding.ItemCharacterBinding
import com.example.starwarscharacters.models.FavoritePresentation
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class FavoriteViewHolder(
    view: View
) : RecyclerViewHolder<FavoritePresentation>(view) {
    val binding = ItemCharacterBinding.bind(view)

    override fun bind(position: Int, item: FavoritePresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}