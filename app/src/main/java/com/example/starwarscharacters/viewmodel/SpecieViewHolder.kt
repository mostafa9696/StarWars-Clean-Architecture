package com.example.starwarscharacters.viewmodel

import android.view.View
import com.example.starwarscharacters.databinding.ItemSpecieBinding
import com.example.starwarscharacters.models.SpeciePresentation
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class SpecieViewHolder(
    view: View
) : RecyclerViewHolder<SpeciePresentation>(view) {
    val binding = ItemSpecieBinding.bind(view)
    override fun bind(position: Int, item: SpeciePresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}