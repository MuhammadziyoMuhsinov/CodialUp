package com.example.codialup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codialup.databinding.ItemRv2Binding
import com.example.codialup.db.MyDbHelper
import com.example.codialup.models.Guruhlar
import com.example.codialup.models.Mentorlar2
import com.example.codialup.models.Student

class RvAdapter3(var list: ArrayList<Guruhlar>, val onClick: OnClick,val myDbHelper: MyDbHelper) :
    RecyclerView.Adapter<RvAdapter3.Vh>() {

    inner class Vh(var itemRv: ItemRv2Binding) : RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(guruhlar: Guruhlar) {
            itemRv.name.text = guruhlar.name.toString()

            itemRv.studentCount.text = countStudent(guruhlar,myDbHelper)

            itemRv.show.setOnClickListener {
                onClick.showClick(guruhlar)
            }
            itemRv.edit.setOnClickListener {
                onClick.editClick(guruhlar)
            }
            itemRv.delete.setOnClickListener {
                onClick.removeClick(guruhlar)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRv2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnClick {
        fun showClick(guruhlar: Guruhlar)
        fun editClick(guruhlar: Guruhlar)
        fun removeClick(guruhlar: Guruhlar)
    }

    fun countStudent(guruhlar: Guruhlar, myDbHelper1: MyDbHelper) : String{

        val listStudent = myDbHelper1.getAllStudent()
        val list = ArrayList<Student>()


        listStudent.forEach {
            if (it.group?.id==guruhlar.id){
                list.add(it)
            }
        }
        return "O'quvchilar soni: ${list.size}"
    }

}