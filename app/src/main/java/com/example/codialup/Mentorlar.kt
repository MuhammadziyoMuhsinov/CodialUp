package com.example.codialup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codialup.adapter.RvAdapter
import com.example.codialup.databinding.FragmentMentorlarBinding
import com.example.codialup.db.MyDbHelper
import com.example.codialup.models.Kurslar
import com.example.codialup.utils.Mydata


class Mentorlar : Fragment() {

    lateinit var binding: FragmentMentorlarBinding
    lateinit var adapter: RvAdapter
    lateinit var myDbHelper: MyDbHelper
    lateinit var list:ArrayList<Kurslar>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMentorlarBinding.inflate(layoutInflater)
        myDbHelper = MyDbHelper(binding.root.context)
        list = ArrayList()
        list.addAll(myDbHelper.getAllKurs())

        adapter = RvAdapter(list, object : RvAdapter.RvClick{
            override fun onClick(kurslar: Kurslar) {
                findNavController().navigate(R.id.mentorlarRoyxatiFragment)
                Mydata.kurslar = kurslar
            }

        })

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rv.adapter = adapter


        return binding.root
    }


}