package com.example.starwarscharacters

import android.app.Application
import com.example.starwarscharacters.di.networkModule
import com.example.starwarscharacters.di.repositoryModule
import com.example.starwarscharacters.di.useCasesModule
import com.example.starwarscharacters.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StarWarsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StarWarsApplication)
            modules(
                networkModule,
                useCasesModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}