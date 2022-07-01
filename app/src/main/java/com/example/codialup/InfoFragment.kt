package com.example.codialup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codialup.databinding.FragmentInfoBinding
import com.example.codialup.utils.Mydata

class InfoFragment : Fragment() {

    lateinit var binding:FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)

        binding.toolbarTitle.text = Mydata.kurslar?.name
        binding.infoKurs.text = Mydata.kurslar?.about

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.kursFragment)
        }


        return binding.root
    }


}