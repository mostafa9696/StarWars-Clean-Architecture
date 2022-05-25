package com.example.starwarscharacters.adapters

import android.view.View
import com.example.starwarscharacters.databinding.ItemCharacterBinding
import com.example.starwarscharacters.models.CharacterPresentation
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class SearchedCharacterViewHolder(
    view: View
) : RecyclerViewHolder<CharacterPresentation>(view) {
    val binding = ItemCharacterBinding.bind(view)

    override fun bind(position: Int, item: CharacterPresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}