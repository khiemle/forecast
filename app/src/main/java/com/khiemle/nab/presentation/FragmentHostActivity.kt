package com.khiemle.nab.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.khiemle.nab.R
import com.khiemle.nab.databinding.ActivityFragmentHostBinding

class FragmentHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFragmentHostBinding.inflate(layoutInflater)

        val mainFragmentTag = "main"
        val replacedFragmentTag = "replaced"

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainFragment>(R.id.mainFragment, tag = mainFragmentTag)
            }
        }

        binding.btnReplace.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<DetailsFragment>(R.id.mainFragment, tag = replacedFragmentTag)

                addToBackStack("main")
            }
        }

        binding.btnRemove.setOnClickListener {
            val mainFragment = supportFragmentManager.findFragmentByTag(mainFragmentTag)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                mainFragment?.let {
                    remove(mainFragment)
                }
                addToBackStack("main")
            }
        }

        setContentView(binding.root)
    }
}