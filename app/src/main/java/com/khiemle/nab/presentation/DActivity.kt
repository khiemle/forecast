package com.khiemle.nab.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.khiemle.nab.databinding.ActivityDBinding

class DActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("khiemlq", "Activity D onCreate")
        val binding = ActivityDBinding.inflate(layoutInflater)

        with(binding) {
            launch.setOnClickListener {
                val intent = Intent(this@DActivity, DActivity::class.java)
                intent.putExtra("new", "khiem")
                startActivity(intent)
            }
            launchB.setOnClickListener {
                val intent = Intent(this@DActivity, BActivity::class.java)
                intent.putExtra("new", "khiem")
                startActivity(intent)
            }
        }

        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("khiemlq", "Activity D onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("khiemlq", "Activity D onStart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("khiemlq", "Activity D onNewIntent")
    }

    override fun onResume() {
        super.onResume()
        Log.d("khiemlq", "Activity D onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("khiemlq", "Activity D onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("khiemlq", "Activity D onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("khiemlq", "Activity D onDestroy")
    }
}