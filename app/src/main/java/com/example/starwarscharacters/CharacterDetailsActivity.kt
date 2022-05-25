package com.example.starwarscharacters

import android.os.Bundle
import android.util.Log
import androidx.viewbinding.ViewBinding
import com.example.starwarscharacters.base.BaseActivity
import com.example.starwarscharacters.databinding.ActivityCharacterDetailsBinding
import com.example.starwarscharacters.models.CharacterPresentation
import com.example.starwarscharacters.models.DataResource
import com.example.starwarscharacters.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsActivity : BaseActivity<ActivityCharacterDetailsBinding>() {

    private val characterDetailsActivity by viewModel<CharacterDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val character =
            intent.getParcelableExtra<CharacterPresentation>("character")

        if (character != null) {
            characterDetailsActivity.getCharacterUrlsDetails(character.url)
        }

        observeOnLiveData()

    }

    private fun observeOnLiveData() {
        characterDetailsActivity.characterDetailsLiveData.observe(this) {
            when (it) {
              //  is DataResource.Loading -> binding.searchResultsProgressBar.show()
                is DataResource.Success -> {
                    Log.d("ww3", it.data?.planet.toString())
                    Log.d("ww3", it.data?.specie?.size.toString())
                    Log.d("ww3", it.data?.filmPresentation?.size.toString())
                }
              /*  is DataResource.Error -> {
                    binding.searchResultsProgressBar.hide()
                    showToast(getString(it.error?.messageID!!))
                }*/

            }
        }
    }

    override fun getViewBinding(): ViewBinding =
        ActivityCharacterDetailsBinding.inflate(layoutInflater)
}