package com.example.codialup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.codialup.adapter.RvAdapter3
import com.example.codialup.databinding.FragmentItemPager1Binding
import com.example.codialup.databinding.ItemDialog2Binding
import com.example.codialup.databinding.ItemDialog3Binding
import com.example.codialup.db.MyDbHelper
import com.example.codialup.models.Guruhlar
import com.example.codialup.models.Mentorlar2
import com.example.codialup.models.Student
import com.example.codialup.utils.Mydata

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [itemPagerFragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class itemPagerFragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var myDbHelper: MyDbHelper
    lateinit var adapter: RvAdapter3
    lateinit var binding: FragmentItemPager1Binding
    lateinit var list: ArrayList<Guruhlar>
    lateinit var listGuruhlar: ArrayList<Guruhlar>
    lateinit var listMentorlar: ArrayList<Mentorlar2>
    lateinit var listKursMentor: ArrayList<Mentorlar2>
    lateinit var listStudent: ArrayList<Student>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemPager1Binding.inflate(layoutInflater)
        list = ArrayList()
        myDbHelper = MyDbHelper(binding.root.context)
        listGuruhlar = ArrayList()
        listGuruhlar.addAll(myDbHelper.getAllGuruh())
        listStudent = ArrayList()
        listStudent.addAll(myDbHelper.getAllStudent())



        listGuruhlar.forEach {
            if (it.mentor?.kusr?.id == Mydata.kurslar?.id && it.type == 1) {
                list.add(it)
            }
        }



        adapter = RvAdapter3(list, object : RvAdapter3.OnClick {
            override fun showClick(guruhlar: Guruhlar) {
                Mydata.guruhlar = guruhlar
                findNavController().navigate(R.id.showPupils)
            }

            override fun editClick(guruhlar: Guruhlar) {
                var tekshir = false
                var tekshir2 = false
                val list2 = ArrayList<String>()
                val list3 = ArrayList<String>()
                val alertDialog = AlertDialog.Builder(binding.root.context).create()
                val itemDialogBinding = ItemDialog2Binding.inflate(layoutInflater)
                alertDialog.setView(itemDialogBinding.root)
                alertDialog.show()
                itemDialogBinding.guruhName.setText(guruhlar.name)
                itemDialogBinding.mentorTv.setText(guruhlar.mentor?.name)

                listKursMentor = ArrayList()

                listMentorlar = ArrayList()
                listMentorlar.addAll(myDbHelper.getAllMentor())

                listMentorlar.forEach {
                    if (it.kusr?.id == Mydata.kurslar?.id) {
                        listKursMentor.add(it)
                        list2.add(it.name.toString())
                    }
                }

                itemDialogBinding.spinnerMentor.adapter = ArrayAdapter<String>(
                    binding.root.context,
                    android.R.layout.simple_expandable_list_item_1,
                    list2
                )

                itemDialogBinding.spinnerMentor.setOnItemSelectedListener(object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (!tekshir2) {
                            itemDialogBinding.mentorTv.setText(guruhlar.mentor?.name)
                            tekshir2 = true
                        } else {
                            itemDialogBinding.mentorTv.setText(list2[position])
                        }
                        view?.visibility = View.INVISIBLE
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                })

                itemDialogBinding.timeTv.setText(guruhlar.vaqti)

                list3.add("10:00 - 12:00")
                list3.add("12:00 - 14:00")
                list3.add("14:00 - 16:00")
                list3.add("16:00 - 18:00")
                list3.add("18:00 - 20:00")

                itemDialogBinding.spinnerTime.adapter = ArrayAdapter<String>(
                    binding.root.context,
                    android.R.layout.simple_expandable_list_item_1,
                    list3
                )



                itemDialogBinding.spinnerTime.setOnItemSelectedListener(object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (!tekshir) {
                            itemDialogBinding.timeTv.setText(guruhlar.kuni)
                            tekshir = true
                        } else {
                            itemDialogBinding.timeTv.setText(list3[position])
                        }

                        view?.visibility = View.INVISIBLE
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                })


                itemDialogBinding.qoshishTxt.setOnClickListener {
                    guruhlar.name = itemDialogBinding.guruhName.text.toString()
                    guruhlar.mentor = listKursMentor[itemDialogBinding.spinnerMentor.selectedItemPosition]
                    guruhlar.kuni = list3[itemDialogBinding.spinnerTime.selectedItemPosition]
                    myDbHelper.updateGuruh(guruhlar)
                    alertDialog.cancel()
                    adapter.notifyItemChanged(list.indexOf(guruhlar))
                    Toast.makeText(binding.root.context, "edited", Toast.LENGTH_SHORT).show()
                }

                itemDialogBinding.yopishTxt.setOnClickListener {
                    alertDialog.cancel()
                }


            }


            override fun removeClick(guruhlar: Guruhlar) {

                val alertDialog = AlertDialog.Builder(binding.root.context)
                alertDialog.setTitle("REMOVE")
                alertDialog.setMessage("Rostdan ham ${guruhlar.name} ni o'chirmoqchimisiz? ")
                alertDialog.setNegativeButton(
                    "No"
                ) { p0, p1 ->

                }
                alertDialog.setPositiveButton(
                    "Yes"
                ) { p0, p1 ->

                    val position = list.indexOf(guruhlar)

                    listStudent.forEach {
                        if (guruhlar.id == it.group?.id){
                            myDbHelper.deleteStudent(it)
                        }
                    }

                    myDbHelper.deleteGuruh(guruhlar)
                    list.remove(guruhlar)
                    adapter.notifyItemRemoved(position)
                    Toast.makeText(binding.root.context, "removed", Toast.LENGTH_SHORT).show()
                }

                alertDialog.show()


            }


        },myDbHelper)
        binding.rv.adapter = adapter

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment itemPagerFragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            itemPagerFragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}