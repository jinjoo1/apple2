package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_list)
    }
}