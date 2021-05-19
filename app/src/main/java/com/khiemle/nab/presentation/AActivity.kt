package com.khiemle.nab.presentation

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.khiemle.nab.databinding.ActivityABinding
import kotlinx.coroutines.delay


class MyIntentService: IntentService("khiem") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("khiemlq","onHandleIntent thread = ${Thread.currentThread().name}")
        for (i in 0..10000) {
            Log.d("khiemlq","$i")
        }
    }
}

class AActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("khiemlq", "Activity A onCreate")
        val binding = ActivityABinding.inflate(layoutInflater)

        with(binding) {
            launch.setOnClickListener {
//                val intent = Intent(this@AActivity, BActivity::class.java)
//                startActivity(intent)
                val intent = Intent(this@AActivity, MyIntentService::class.java)
                startService(intent)
            }
        }

        setContentView(binding.root)
    }


    override fun onRestart() {
        super.onRestart()
        Log.d("khiemlq", "Activity A onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("khiemlq", "Activity A onStart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("khiemlq", "Activity A onNewIntent")
    }

    override fun onResume() {
        super.onResume()
        Log.d("khiemlq", "Activity A onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("khiemlq", "Activity A onPause")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.d("khiemlq", "Activity A onSaveInstanceState")
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("khiemlq", "Activity A onSaveInstanceState old version")
    }

    override fun onStop() {
        super.onStop()
        Log.d("khiemlq", "Activity A onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("khiemlq", "Activity A onDestroy")
    }

}