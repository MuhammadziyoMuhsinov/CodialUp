package com.example.codialup

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.codialup.databinding.FragmentAddPupilsBinding
import com.example.codialup.db.MyDbHelper
import com.example.codialup.models.Student
import com.example.codialup.utils.Mydata


class AddPupils : Fragment() {

    private lateinit var binding: FragmentAddPupilsBinding
    private lateinit var myDbHelper: MyDbHelper
    var date = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPupilsBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        if (Mydata.tekshir3) {
            binding.toolbarTitle.text = "Edit student"

            binding.name.setText(Mydata.student?.name)
            binding.surname.setText(Mydata.student?.lastName)
            binding.fatherName.setText(Mydata.student?.fatherName)
            date = Mydata.student?.regKuni.toString()
            binding.textView.text = date

        }

        binding.date.setOnClickListener {
            val dataPicker = DatePickerDialog(binding.root.context)
            dataPicker.setOnCancelListener {
                date = ""
            }
            dataPicker.setOnDateSetListener(object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    date = "$year/$month/$dayOfMonth"
                    binding.textView.text = date
                }
            })
            dataPicker.show()
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
            Mydata.tekshir3 = false
        }

        binding.save.setOnClickListener {

            if (Mydata.tekshir3) {
                if (date == "" || binding.name.text.toString() == "" || binding.surname.text.toString() == "" || binding.fatherName.text.toString() == "") {
                    Toast.makeText(binding.root.context, "Empty", Toast.LENGTH_SHORT).show()
                } else {
                    Mydata.student?.name = binding.name.text.toString()
                    Mydata.student?.lastName = binding.surname.text.toString()
                    Mydata.student?.fatherName = binding.fatherName.text.toString()
                    Mydata.student?.regKuni = date

                    myDbHelper.updateStudent(
                        Mydata.student!!
                    )

                    findNavController().popBackStack()
                    Toast.makeText(binding.root.context, "edited", Toast.LENGTH_SHORT).show()
                    Mydata.tekshir3 = false
                }
            } else {
                if (date == "" || binding.name.text.toString() == "" || binding.surname.text.toString() == "" || binding.fatherName.text.toString() == "") {
                    Toast.makeText(binding.root.context, "Empty", Toast.LENGTH_SHORT).show()
                } else {
                    myDbHelper.addStudent(
                        Student(
                            binding.name.text.toString(),
                            binding.surname.text.toString(),
                            binding.fatherName.text.toString(),
                            date,
                            Mydata.guruhlar
                        )
                    )

                    findNavController().popBackStack()
                    Toast.makeText(binding.root.context, "saved", Toast.LENGTH_SHORT).show()
                }
            }


        }


        return binding.root
    }


}