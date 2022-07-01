package com.example.codialup

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.codialup.adapter.RvAdapter2
import com.example.codialup.databinding.*
import com.example.codialup.db.MyDbHelper
import com.example.codialup.models.Guruhlar
import com.example.codialup.models.Kurslar
import com.example.codialup.models.Mentorlar2
import com.example.codialup.models.Student
import com.example.codialup.utils.Mydata
import com.example.codialup.utils.Mydata.guruhlar

class MentorlarRoyxatiFragment : Fragment() {
    private lateinit var binding: FragmentMentorlarRoyxatiBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var adapter: RvAdapter2
    private lateinit var list: ArrayList<Mentorlar2>
    private lateinit var listGuruhlar: ArrayList<Guruhlar>
    private lateinit var listStudent: ArrayList<Student>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMentorlarRoyxatiBinding.inflate(layoutInflater)
        myDbHelper = MyDbHelper(binding.root.context)
        list = ArrayList()
        listGuruhlar = ArrayList()
        listGuruhlar.addAll(myDbHelper.getAllGuruh())
        listStudent = ArrayList()
        listStudent.addAll(myDbHelper.getAllStudent())
        val listMentor = ArrayList<Mentorlar2>()
        listMentor.addAll(myDbHelper.getAllMentor())

        listMentor.forEach {
            if (it.kusr?.id == Mydata.kurslar?.id) {
                list.add(it)
            }
        }



        adapter = RvAdapter2(list, object : RvAdapter2.RvClick2 {
            override fun editClick(mentor: Mentorlar2) {

                val alertDialog = AlertDialog.Builder(binding.root.context).create()
                val itemDialog = ItemDialog3Binding.inflate(layoutInflater)
                alertDialog.setView(itemDialog.root)
                itemDialog.name.setText(mentor.name)
                itemDialog.lastName.setText(mentor.lastname)
                itemDialog.fatherName.setText(mentor.fathername)
                alertDialog.show()


                itemDialog.qoshishTxt.setOnClickListener {
                    val index = list.indexOf(mentor)
                    mentor.name = itemDialog.name.text.toString()
                    mentor.lastname = itemDialog.lastName.text.toString()
                    mentor.fathername = itemDialog.fatherName.text.toString()


                    list[index] = mentor

                    adapter.notifyItemChanged(index)

                    myDbHelper.updateMentor(mentor)

                    alertDialog.cancel()
                }
                itemDialog.yopishTxt.setOnClickListener {
                    alertDialog.cancel()
                }

            }

            override fun deleteClick(mentor: Mentorlar2) {

                val alertDialog = AlertDialog.Builder(binding.root.context)
                alertDialog.setTitle("REMOVE")
                alertDialog.setMessage("Rostdan ham ${mentor.name} ni o'chirmoqchimisiz? ")
                alertDialog.setNegativeButton(
                    "No"
                ) { p0, p1 ->

                }
                alertDialog.setPositiveButton(
                    "Yes"
                ) { p0, p1 ->
                    val position = list.indexOf(mentor)

                    val removeallguruh = ArrayList<Guruhlar>()

                    listGuruhlar.forEach {
                        if (it.mentor?.id == mentor.id) {
                            myDbHelper.deleteGuruh(it)
                            removeallguruh.add(it)
                        }
                    }

                    removeallguruh.forEach {
                        val a = it
                        listStudent.forEach {
                            if (a.id == it.group?.id) {
                                myDbHelper.deleteStudent(it)
                            }
                        }

                    }



                    myDbHelper.deleteMentor(mentor)
                    list.remove(mentor)
                    adapter.notifyItemRemoved(position)
                    Toast.makeText(binding.root.context, "deleted", Toast.LENGTH_SHORT).show()

                }

                alertDialog.show()


            }
        })

        binding.rv.adapter = adapter

        binding.toolbarTitle.text = Mydata.kurslar?.name
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.addMentorFragment)
        }



        return binding.root
    }


}