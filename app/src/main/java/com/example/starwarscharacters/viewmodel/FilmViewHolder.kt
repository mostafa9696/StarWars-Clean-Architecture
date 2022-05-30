package com.example.starwarscharacters.viewmodel

import android.view.View
import com.example.starwarscharacters.databinding.ItemFilmBinding
import com.example.starwarscharacters.models.FilmPresentation
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class FilmViewHolder(
    view: View
) : RecyclerViewHolder<FilmPresentation>(view) {
    val binding = ItemFilmBinding.bind(view)

    override fun bind(position: Int, item: FilmPresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}