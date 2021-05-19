package com.khiemle.nab.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.khiemle.nab.databinding.ActivityBBinding

class BActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("khiemlq", "Activity B onCreate")
        val binding = ActivityBBinding.inflate(layoutInflater)

        with(binding) {
            launch.setOnClickListener {
                val intent = Intent(this@BActivity, CActivity::class.java)
                startActivity(intent)
            }
        }

        setContentView(binding.root)
    }


    override fun onRestart() {
        super.onRestart()
        Log.d("khiemlq", "Activity B onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("khiemlq", "Activity B onStart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("khiemlq", "Activity B onNewIntent")
    }

    override fun onResume() {
        super.onResume()
        Log.d("khiemlq", "Activity B onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("khiemlq", "Activity B onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("khiemlq", "Activity B onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("khiemlq", "Activity B onDestroy")
    }
}