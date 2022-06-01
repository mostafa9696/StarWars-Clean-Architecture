package com.example.starwarscharacters.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.starwarscharacters.R
import com.example.starwarscharacters.adapters.createFilmsAdapter
import com.example.starwarscharacters.adapters.createSpeciesAdapter
import com.example.starwarscharacters.base.BaseActivity
import com.example.starwarscharacters.databinding.ActivityCharacterDetailsBinding
import com.example.starwarscharacters.models.*
import com.example.starwarscharacters.utils.remove
import com.example.starwarscharacters.utils.show
import com.example.starwarscharacters.utils.showToast
import com.example.starwarscharacters.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsActivity : BaseActivity<ActivityCharacterDetailsBinding>() {

    private val viewmodel by viewModel<CharacterDetailsViewModel>()

    private val filmsAdapter = createFilmsAdapter()

    private val speciesAdapter = createSpeciesAdapter()
    private lateinit var characterPresentation: CharacterPresentation

    private var isFav = false
    private lateinit var favoritePresentation: FavoritePresentation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkSourceOfOpen()

        binding.ivFav.setOnClickListener {
            if (isFav)
                viewmodel.deleteFavorite(characterPresentation.name)
            else
                viewmodel.saveFavorite(favoritePresentation)
        }

        observeOnLiveData()
    }

    private fun checkSourceOfOpen() {
        // open from favorite item
        if(intent.hasExtra("favorite_character")) {
            favoritePresentation = intent.getParcelableExtra("favorite_character")!!
            characterPresentation = favoritePresentation.characterPresentation
            this.isFav = true
            viewmodel.setCharacterDetails(favoritePresentation)
        } else {  // open from search item
            characterPresentation = intent.getParcelableExtra("character")!!

            viewmodel.getCharacterUrlsDetails(characterPresentation.url)
            viewmodel.isCharacterFavorite(characterPresentation.name)
        }
    }

    private fun observeOnLiveData() {
        viewmodel.characterDetailsLiveData.observe(this) {
            when (it) {
                is DataResource.Loading -> binding.progressBar.show()
                is DataResource.Success -> {
                    binding.progressBar.remove()
                    binding.ivFav.show()
                    bindCharacterBasicInfo()
                    bindSpecies(it.data?.species)
                    bindFilms(it.data?.films)
                    bindPlanet(it.data?.planet)
                    setFavoriteModel(it.data!!)
                }
                is DataResource.Error -> {
                    binding.progressBar.remove()
                    showToast(getString(it.error?.messageID!!))
                }

            }
        }

        viewmodel.isFavoriteLiveData.observe(this) { isFav ->
            this.isFav = isFav
            if (isFav)
                binding.ivFav.setImageResource(R.drawable.ic_fav_fill)
            else
                binding.ivFav.setImageResource(R.drawable.ic_fav)
        }
    }

    private fun setFavoriteModel(data: CharacterDetailsPresentation) {
        favoritePresentation =
            FavoritePresentation(characterPresentation, data.planet, data.species, data.films)
    }

    @SuppressLint("SetTextI18n")
    private fun bindPlanet(planet: PlanetPresentation?) {
        planet?.let {
            binding.apply {
                tvPlanetName.text = planet.name
                tvPlanetPopulation.text = "Population :" + planet.population
            }
        }
    }

    private fun bindFilms(films: List<FilmPresentation>?) {
        films?.let {
            binding.characterDetailsFilmsRecyclerView.adapter =
                filmsAdapter.apply { submitList(films) }
        }
    }

    private fun bindSpecies(species: List<SpeciePresentation>?) {
        species?.let {
            if (species.isNotEmpty()) {
                binding.characterDetailsSpeciesRecyclerView.adapter =
                    speciesAdapter.apply { submitList(species) }
            } else binding.noSpeciesTextView.show()
        }
    }

    private fun bindCharacterBasicInfo() {
        binding.tvCharacterName.text = characterPresentation.name
        binding.tvBirthYear.text = characterPresentation.birthYear
        binding.tvHeightInCm.text = characterPresentation.heightInCm + " cm"
    }

    override fun getViewBinding(): ViewBinding =
        ActivityCharacterDetailsBinding.inflate(layoutInflater)
}