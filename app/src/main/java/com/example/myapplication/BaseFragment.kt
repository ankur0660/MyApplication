package com.example.myapplication

import android.Manifest
import android.content.*
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class BaseFragment : Fragment() {
    var string: String = ""

    companion object {
        @JvmStatic
        fun newInstance(string: String) = BaseFragment().apply { this.string = string }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onActivityCreated $string")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onCreateView $string")
        return inflater.inflate(R.layout.activity_frag, container, false)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onAttachFragment $string")
        super.onAttachFragment(childFragment)
    }

    override fun onAttach(context: Context) {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onAttach $string")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onCreate $string")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onViewCreated $string")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onStart $string")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onResume $string")
        super.onResume()
    }


    override fun onPause() {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onPause $string")
        super.onPause()
    }

    override fun onStop() {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onStop $string")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onDestroyView $string")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onDestroy $string")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("Fragment State :", " ${this.javaClass.simpleName} : onDetach $string")
        super.onDetach()
    }


}
