package com.khiemle.nab.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khiemle.nab.ForecastApplication
import com.khiemle.nab.databinding.FragmentMainBinding
import leakcanary.AppWatcher

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("khiemlq", "MainFragment called onCreate")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentMainBinding

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("khiemlq", "MainFragment called onCreateView")
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        AppWatcher.objectWatcher.expectWeaklyReachable(binding.tvText, "khiemlq view was detached")
        (context?.applicationContext as? ForecastApplication)?.let {
            it.leakView = binding.tvText
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("khiemlq", "MainFragment called onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("khiemlq", "MainFragment called onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d("khiemlq", "MainFragment called onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("khiemlq", "MainFragment called onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("khiemlq", "MainFragment called onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("khiemlq", "MainFragment called onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("khiemlq", "MainFragment called onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("khiemlq", "MainFragment called onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("khiemlq", "MainFragment called onDestroy")
    }

    override fun onDetach() {
        Log.d("khiemlq", "MainFragment called onDetach")
        super.onDetach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("khiemlq", "MainFragment called onAttach")
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}