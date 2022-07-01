package com.example.codialup

import android.R
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codialup.databinding.FragmentAddGruppaBinding
import com.example.codialup.db.MyDbHelper
import com.example.codialup.models.Guruhlar
import com.example.codialup.models.Mentorlar2
import com.example.codialup.utils.Mydata
import java.lang.Exception


class addGruppaFragment : Fragment() {

    lateinit var binding: FragmentAddGruppaBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var list1: ArrayList<String>
    lateinit var listMentor: ArrayList<Mentorlar2>
    lateinit var listKursMentor: ArrayList<Mentorlar2>
    lateinit var list2: ArrayList<String>
    lateinit var list3: ArrayList<String>
    var tekshir = false
    var tekshir2 = false
    var tekshir3 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGruppaBinding.inflate(layoutInflater)
        myDbHelper = MyDbHelper(binding.root.context)
        listMentor = ArrayList()
        list2 = arrayListOf()
        list3 = arrayListOf()
        list1 = arrayListOf()
        listMentor.addAll(myDbHelper.getAllMentor())

        Start()


        binding.spinner2.adapter = ArrayAdapter<String>(
            binding.root.context,
            R.layout.simple_expandable_list_item_1,
            list1
        )

        binding.spinner3.adapter = ArrayAdapter<String>(
            binding.root.context,
            R.layout.simple_expandable_list_item_1,
            list2
        )

        binding.spinner4.adapter = ArrayAdapter<String>(
            binding.root.context,
            R.layout.simple_expandable_list_item_1,
            list3
        )




        binding.spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (tekshir) {
                    if (position == 0) {
                        binding.textView2.text = null
                    } else {
                        binding.textView2.text = list1[position]
                    }


                } else {
                    tekshir = true
                }


                view?.visibility = View.INVISIBLE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })




        binding.spinner3.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (tekshir2) {
                    if (position == 0) {
                        binding.textView3.text = null
                    } else {
                        binding.textView3.text = list2[position]
                    }
                } else {
                    tekshir2 = true
                }
                view?.visibility = View.INVISIBLE

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })


        binding.spinner4.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (tekshir3) {
                    if (position == 0) {
                        binding.textView4.text = null
                    } else {
                        binding.textView4.text = list3[position]
                    }
                } else {
                    tekshir3 = true
                }
                view?.visibility = View.INVISIBLE

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.save.setOnClickListener {


            if (binding.guruhName.text.toString() == "" || binding.textView2.text == "" || binding.textView3.text == "" || binding.textView4.text == "") {
                Toast.makeText(binding.root.context, "empty", Toast.LENGTH_SHORT).show()
            } else {

                val Mentor = listKursMentor.elementAt(binding.spinner2.selectedItemPosition-1)

                myDbHelper.addGuruh(
                    Guruhlar(
                        binding.guruhName.text.toString(),
                        Mentor,
                        binding.textView3.text.toString(),
                        binding.textView4.text.toString(),
                        0
                    )
                )
                Toast.makeText(binding.root.context, "Saved", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }


        }






        return binding.root
    }

    fun Start() {

        listKursMentor = ArrayList()

        list1.add("Empty")
        listMentor.forEach {
            if (it.kusr?.id == Mydata.kurslar?.id) {
                listKursMentor.add(it)
                list1.add(it.name.toString())
            }
        }

        list2.add("Empty")
        list2.add("10:00 - 12:00")
        list2.add("12:00 - 14:00")
        list2.add("14:00 - 16:00")
        list2.add("16:00 - 18:00")
        list2.add("18:00 - 20:00")


        list3.add("Empty")
        list3.add("Dushanba/Chorshanba/Juma")
        list3.add("Seshanba/Payshanba/Shanba")

    }


}

