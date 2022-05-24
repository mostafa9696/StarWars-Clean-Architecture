package com.example.starwarscharacters

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.viewbinding.ViewBinding
import com.example.starwarscharacters.base.BaseActivity
import com.example.starwarscharacters.databinding.ActivityCharactersBinding
import com.example.starwarscharacters.models.DataResource
import com.example.starwarscharacters.utils.hide
import com.example.starwarscharacters.utils.show
import com.example.starwarscharacters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : BaseActivity<ActivityCharactersBinding>() {

    private val charactersViewModel by viewModel<CharactersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeOnViewsListeners()

        observeOnLiveData()
    }

    private fun observeOnLiveData() {
        charactersViewModel.searchLiveData.observe(this) {
            when (it) {
                is DataResource.Loading -> binding.searchResultsProgressBar.show()
                is DataResource.Success -> {
                    binding.searchResultsProgressBar.hide()
                    Log.d("gg3",  it.data?.size.toString())
                }
                is DataResource.Error -> {
                    binding.searchResultsProgressBar.hide()
                    Toast.makeText(this, getString(it.error?.messageID!!), Toast.LENGTH_LONG).show()
                }

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