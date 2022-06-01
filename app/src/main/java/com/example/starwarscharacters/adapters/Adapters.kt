package com.example.starwarscharacters.adapters

import com.example.starwarscharacters.R
import com.example.starwarscharacters.models.CharacterPresentation
import com.example.starwarscharacters.models.FavoritePresentation
import com.example.starwarscharacters.models.FilmPresentation
import com.example.starwarscharacters.models.SpeciePresentation
import com.example.starwarscharacters.viewmodel.FilmViewHolder
import com.example.starwarscharacters.viewmodel.SpecieViewHolder
import me.ibrahimyilmaz.kiel.adapterOf

internal inline fun createSearchResultAdapter(noinline onClick: (CharacterPresentation) -> Unit) =
    adapterOf<CharacterPresentation> {
        diff(
            areItemsTheSame = { old, new -> old === new },
            areContentsTheSame = { old, new -> old.url == new.url }
        )
        register(
            layoutResource = R.layout.item_character,
            viewHolder = ::SearchedCharacterViewHolder,
            onBindViewHolder = { viewHolder, _, character ->
                viewHolder.binding.character = character
                viewHolder.itemView.setOnClickListener {
                    onClick(character)
                }
            }
        )
    }


internal inline fun createSpeciesAdapter() = adapterOf<SpeciePresentation> {
    diff(
        areItemsTheSame = { old, new -> old === new },
        areContentsTheSame = { old, new -> old.name == new.name }
    )
    register(
        layoutResource = R.layout.item_specie,
        viewHolder = ::SpecieViewHolder,
        onBindViewHolder = { specieViewHolder, _, speciePresentation ->
            specieViewHolder.binding.species = speciePresentation
        }
    )
}

internal inline fun createFilmsAdapter() = adapterOf<FilmPresentation> {
    diff(
        areItemsTheSame = { old, new -> old === new },
        areContentsTheSame = { old, new -> old.title == new.title }
    )
    register(
        layoutResource = R.layout.item_film,
        viewHolder = ::FilmViewHolder,
        onBindViewHolder = { viewHolder, _, item ->
            viewHolder.binding.film = item
        }
    )
}

internal inline fun createFavoriteAdapter(noinline onClick: (FavoritePresentation) -> Unit) =
    adapterOf<FavoritePresentation> {
        diff(
            areItemsTheSame = { old, new -> old === new },
            areContentsTheSame = { old, new -> old.characterPresentation.url == new.characterPresentation.url }
        )
        register(
            layoutResource = R.layout.item_character,
            viewHolder = ::FavoriteViewHolder,
            onBindViewHolder = { viewHolder, _, favoritePresentation ->
                viewHolder.binding.character = favoritePresentation.characterPresentation
                viewHolder.itemView.setOnClickListener {
                    onClick(favoritePresentation)
                }
            }
        )
    }

