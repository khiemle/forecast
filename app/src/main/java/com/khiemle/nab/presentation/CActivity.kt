package com.khiemle.nab.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.khiemle.nab.R
import com.khiemle.nab.databinding.ActivityBBinding
import com.khiemle.nab.databinding.ActivityCBinding

class CActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCBinding.inflate(layoutInflater)

        with(binding) {
            launch.setOnClickListener {
                val intent = Intent(this@CActivity, DActivity::class.java)
                startActivity(intent)
            }
            launchA.setOnClickListener {
                val intent = Intent(this@CActivity, AActivity::class.java)
                startActivity(intent)
            }
        }

        setContentView(binding.root)
    }
}