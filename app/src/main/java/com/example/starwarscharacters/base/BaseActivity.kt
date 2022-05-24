package com.example.starwarscharacters.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>: AppCompatActivity() {

    protected open lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getViewBinding()
        @Suppress("UNCHECKED_CAST")
        this.binding = binding as VB
        setContentView(binding.root)
    }

    protected abstract fun getViewBinding(): ViewBinding
}