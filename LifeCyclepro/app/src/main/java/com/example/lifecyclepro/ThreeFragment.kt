package com.example.lifecyclepro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifecyclepro.MainActivity
import com.example.lifecyclepro.R
import com.example.lifecyclepro.databinding.FragmentThreeBinding


class ThreeFragment : Fragment() {
    lateinit var binding: FragmentThreeBinding
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
                binding = FragmentThreeBinding.inflate(layoutInflater)
                binding.tvMessage3.text = arguments?.getString("tvMessage2")
                binding.btnsend3.setOnClickListener {
                    mainActivity.changeFragment("FRAG2", binding.edtName3.text.toString())
                }

            return binding.root
        }

    }


