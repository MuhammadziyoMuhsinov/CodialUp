package com.example.codialup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.codialup.adapter.RvAdapter4
import com.example.codialup.databinding.FragmentShowPupilsBinding
import com.example.codialup.db.MyDbHelper
import com.example.codialup.models.Student
import com.example.codialup.utils.Mydata


class showPupils : Fragment() {

    lateinit var adapter: RvAdapter4
    lateinit var binding: FragmentShowPupilsBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var listPupils: ArrayList<Student>

    private lateinit var list: ArrayList<Student>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowPupilsBinding.inflate(layoutInflater)

        if (Mydata.guruhlar?.type==1){
            binding.startLessonBtn.visibility = View.GONE
        }


        myDbHelper = MyDbHelper(binding.root.context)
        listPupils = ArrayList()
        listPupils.addAll(myDbHelper.getAllStudent())
        list = ArrayList()
        listPupils.forEach {
            if (Mydata.guruhlar?.id == it.group?.id) {
                list.add(it)
            }
        }


        adapter = RvAdapter4(list, object : RvAdapter4.OnClick2 {
            override fun removeClick(student: Student) {
                val alertDialog = AlertDialog.Builder(binding.root.context)
                alertDialog.setTitle("REMOVE")
                alertDialog.setMessage("Rostdan ham ${student.name} ni o'chirmoqchimisiz? ")
                alertDialog.setNegativeButton(
                    "No"
                ) { p0, p1 ->

                }
                alertDialog.setPositiveButton(
                    "Yes"
                ) { p0, p1 ->
                    val position = list.indexOf(student)
                    list.remove(student)
                    myDbHelper.deleteStudent(student)
                    adapter.notifyItemRemoved(position)
                    Toast.makeText(binding.root.context, "removed!", Toast.LENGTH_SHORT).show()
                }

                alertDialog.show()





            }

            override fun editClick(student: Student) {
                findNavController().navigate(R.id.addPupils)
                Mydata.tekshir3=true
                Mydata.student = student
            }

        })
        binding.rv.adapter = adapter


        binding.apply {
            toolbarTitle.text = Mydata.guruhlar?.name
            groupName.text = Mydata.guruhlar?.name
            time.text = Mydata.guruhlar?.vaqti
            pupilsCount.text = "O'quvchilar soni: ${list.size}"

            back.setOnClickListener {
                findNavController().popBackStack()
            }

            add.setOnClickListener {
                findNavController().navigate(R.id.addPupils)
            }

            startLessonBtn.setOnClickListener {
                findNavController().popBackStack()
                Mydata.guruhlar?.type = 1
                myDbHelper.updateGuruh(Mydata.guruhlar!!)
                Mydata.tekshir2 = true
            }

        }


        return binding.root
    }


}