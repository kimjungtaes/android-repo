package com.example.appbarpro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appbarpro.databinding.FragmentTwoBinding


class TwoFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentTwoBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(layoutInflater)
        binding.ivHouse.setOnClickListener {
            Toast.makeText(mainActivity, "하우스", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }
}

