package com.example.starwarscharacters.activities

import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.viewbinding.ViewBinding
import com.example.starwarscharacters.adapters.createFavoriteAdapter
import com.example.starwarscharacters.adapters.createSearchResultAdapter
import com.example.starwarscharacters.base.BaseActivity
import com.example.starwarscharacters.databinding.ActivityCharactersBinding
import com.example.starwarscharacters.models.DataResource
import com.example.starwarscharacters.utils.hide
import com.example.starwarscharacters.utils.show
import com.example.starwarscharacters.utils.showToast
import com.example.starwarscharacters.utils.startActivity
import com.example.starwarscharacters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : BaseActivity<ActivityCharactersBinding>() {

    private val charactersViewModel by viewModel<CharactersViewModel>()

    private val searchResultAdapter = createSearchResultAdapter {
        startActivity<CharacterDetailsActivity> {
            putExtra("character", it)
        }
    }

    private val favoritesAdapter = createFavoriteAdapter {
        startActivity<CharacterDetailsActivity> {
            putExtra("favorite_character", it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        charactersViewModel.getFavorites()
        observeOnViewsListeners()

        observeOnLiveData()
    }

    private fun observeOnLiveData() {
        charactersViewModel.searchLiveData.observe(this) {
            when (it) {
                is DataResource.Loading -> binding.searchResultsProgressBar.show()
                is DataResource.Success -> {
                    binding.searchResultsProgressBar.hide()
                    searchResultAdapter.submitList(it.data)
                    binding.rvSearchResults.adapter = searchResultAdapter
                }
                is DataResource.Error -> {
                    binding.searchResultsProgressBar.hide()
                    showToast(getString(it.error?.messageID!!))
                }
            }
        }

        charactersViewModel.favoritesLiveData.observe(this) { favorites ->
            if (favorites.isEmpty()) {
                binding.tvNoFavorites.show()
            } else {
                binding.tvNoFavorites.hide()
                favoritesAdapter.submitList(favorites)
                binding.rvFavorites.adapter = favoritesAdapter
            }
        }
    }

    private fun observeOnViewsListeners() {
        binding.ivBackToStartState.setOnClickListener {
            binding.searchLayout.transitionToStart()
        }

        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            text?.let { name ->
                if (name.length > 1) {
                    charactersViewModel.searchCharacters(name.toString())
                }
            }
        }
    }

    override fun getViewBinding(): ViewBinding =
        ActivityCharactersBinding.inflate(layoutInflater)
}