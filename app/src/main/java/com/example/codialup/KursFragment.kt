package com.example.codialup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.codialup.adapter.RvAdapter
import com.example.codialup.databinding.DialogItemBinding
import com.example.codialup.databinding.FragmentKursBinding
import com.example.codialup.db.MyDbHelper
import com.example.codialup.models.Kurslar
import com.example.codialup.utils.Mydata

class KursFragment : Fragment() {

    private lateinit var MyDbHelper: MyDbHelper
    private lateinit var binding: FragmentKursBinding
    private lateinit var adapter: RvAdapter
    private lateinit var list: ArrayList<Kurslar>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKursBinding.inflate(layoutInflater)

        MyDbHelper = MyDbHelper(binding.root.context)

        list = ArrayList()
        list.addAll(MyDbHelper.getAllKurs())
        adapter = RvAdapter(list, object : RvAdapter.RvClick{
            override fun onClick(kurslar: Kurslar) {
                findNavController().navigate(R.id.infoFragment)
                Mydata.kurslar = kurslar
            }

        })
        binding.rv.adapter = adapter

        binding.add.setOnClickListener {
            val alertDialog = AlertDialog.Builder(binding.root.context).create()
            val itemDialog = DialogItemBinding.inflate(layoutInflater)
            alertDialog.setView(itemDialog.root)
            alertDialog.show()

            itemDialog.qoshishTxt.setOnClickListener {
            val kurs = Kurslar(
                itemDialog.kursNomi.text.toString(),
                itemDialog.kursHaqida.text.toString()
            )
                MyDbHelper.addKurs(kurs)
                list.add(kurs)
                adapter.notifyItemInserted(list.size-1)

                alertDialog.cancel()

            }
            itemDialog.yopishTxt.setOnClickListener {
                alertDialog.cancel()
            }

        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }



        return binding.root
    }


}