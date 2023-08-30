package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityItemBinding

class item : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            binding = ActivityItemBinding.inflate(layoutInflater)
            setContentView(binding.root)

        setContentView(R.layout.activity_item)
    }
}