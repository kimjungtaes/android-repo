package com.example.lifecyclepro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifecyclepro.databinding.FragmentThreeBinding


class ThreeFragment : Fragment() {
    lateinit var binding: FragmentThreeBinding

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {


            return inflater.inflate(R.layout.fragment_three, container, false)
        }

    }


