package com.example.starwarscharacters.adapters

import com.example.starwarscharacters.R
import com.example.starwarscharacters.models.CharacterPresentation
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