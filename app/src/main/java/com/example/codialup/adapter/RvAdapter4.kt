package com.example.codialup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codialup.databinding.ItemRv3Binding
import com.example.codialup.databinding.ItemRvBinding
import com.example.codialup.models.Guruhlar
import com.example.codialup.models.Student

class RvAdapter4(var list: List<Student>, val onClick2: OnClick2) :
    RecyclerView.Adapter<RvAdapter4.Vh>() {

    inner class Vh(var itemRv: ItemRv3Binding) : RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(student: Student) {
            itemRv.name.text = student.name
            itemRv.sana.text = student.regKuni
            itemRv.delete.setOnClickListener {
                onClick2.removeClick(student)
            }
            itemRv.edit.setOnClickListener {
                onClick2.editClick(student)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRv3Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnClick2 {
        fun removeClick(student: Student)
        fun editClick(student: Student)
    }

}