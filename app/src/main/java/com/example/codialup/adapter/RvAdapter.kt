package com.example.codialup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.codialup.databinding.ItemRvBinding
import com.example.codialup.models.Kurslar

class RvAdapter(var list: List<Kurslar>, val rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(user: Kurslar) {
            var a: String? = null
            var b: String? = null

            val c = user.name.toString() + " "

            a = c.substring(0, c.indexOf(" "))
            b = c.substring(
                c.indexOf(" "),
                c.length
            )

            itemRv.name1.text = a
            itemRv.name2.text = b

            itemRv.root.setOnClickListener {
                rvClick.onClick(user)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface RvClick {
        fun onClick (kurslar: Kurslar)
    }

}

