package com.example.codialup

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.codialup.adapter.VpagerAapter
import com.example.codialup.databinding.FragmentGuruhlarRoyhatiBinding
import com.example.codialup.db.MyDbHelper
import com.example.codialup.utils.Mydata
import com.google.android.material.tabs.TabLayout


class guruhlarRoyhatiFragment : Fragment() {

    lateinit var myDbHelper: MyDbHelper
    lateinit var adapter: VpagerAapter
    lateinit var binding: FragmentGuruhlarRoyhatiBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuruhlarRoyhatiBinding.inflate(layoutInflater)
        adapter = VpagerAapter(childFragmentManager)
        binding.myTab.setupWithViewPager(binding.myViewpager)
        myDbHelper = MyDbHelper(binding.root.context)
        binding.myViewpager.adapter = adapter

        binding.toolbarTitle.text = Mydata.kurslar?.name



        binding.myViewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 0) {
                    binding.add.visibility = View.INVISIBLE
                } else {
                    binding.add.visibility = View.VISIBLE
                }
                if (Mydata.tekshir2){
                    adapter.notifyDataSetChanged()
                    Mydata.tekshir2 = false
                }



            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.add.visibility = View.INVISIBLE
                } else {
                    binding.add.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })




        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.addGruppaFragment)
        }

        return binding.root
    }


}