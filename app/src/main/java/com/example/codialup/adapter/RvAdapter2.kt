package com.example.codialup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codialup.Mentorlar
import com.example.codialup.databinding.ItemRv4Binding
import com.example.codialup.models.Mentorlar2

class RvAdapter2(var list: List<Mentorlar2>, val rvClick2: RvClick2) :
    RecyclerView.Adapter<RvAdapter2.Vh>() {

    inner class Vh(var itemRv: ItemRv4Binding) : RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(mentor: Mentorlar2) {
            itemRv.name.text = mentor.name.toString()

            itemRv.edit.setOnClickListener {
                rvClick2.editClick(mentor)
            }
            itemRv.delete.setOnClickListener {
                rvClick2.deleteClick(mentor)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRv4Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface RvClick2{
        fun editClick(mentor: Mentorlar2)
        fun deleteClick(mentor: Mentorlar2)
    }

}