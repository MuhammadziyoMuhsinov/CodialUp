package com.example.codialup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.codialup.Guruhlar
import com.example.codialup.databinding.FragmentItemPager1Binding
import com.example.codialup.itemPagerFragment1
import com.example.codialup.itemPagerFragment2

class VpagerAapter( fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return if (position % 2 == 0) {
            itemPagerFragment1.newInstance("null","null")
        } else {
            itemPagerFragment2.newInstance("null","null")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val list = arrayListOf<String>("Ochilgan                     guruhlar", "Ochilayotgan guruhlar")
        return list[position]
    }


}