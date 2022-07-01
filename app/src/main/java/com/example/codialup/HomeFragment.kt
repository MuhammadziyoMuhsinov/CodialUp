package com.example.codialup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codialup.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)


        binding.apply {
            kurslar.setOnClickListener {
                findNavController().navigate(R.id.kursFragment)
            }
            guruhlar.setOnClickListener {
                findNavController().navigate(R.id.guruhlar2)
            }
            mentorlar.setOnClickListener {
                findNavController().navigate(R.id.mentorlar2)
            }
        }

        return binding.root

    }


}