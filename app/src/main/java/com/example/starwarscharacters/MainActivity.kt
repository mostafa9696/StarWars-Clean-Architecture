package com.example.starwarscharacters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.starwarscharacters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val charactersViewModel by viewModel<CharactersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        charactersViewModel.logg()

    }
}