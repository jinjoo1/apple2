package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.applemarket.databinding.ItemBinding

class item : AppCompatActivity() {

    private lateinit var binding: ItemBinding


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            binding = ItemBinding.inflate(layoutInflater)
            setContentView(binding.root)

        setContentView(R.layout.item)
    }
}