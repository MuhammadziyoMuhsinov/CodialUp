package com.example.codialup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.codialup.databinding.FragmentAddMentorBinding
import com.example.codialup.db.MyDbHelper
import com.example.codialup.models.Mentorlar2
import com.example.codialup.utils.Mydata


class addMentorFragment : Fragment() {
    lateinit var binding:FragmentAddMentorBinding
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMentorBinding.inflate(layoutInflater)
        myDbHelper = MyDbHelper(binding.root.context)

        binding.add.setOnClickListener {
            myDbHelper.addMentor(
                Mentorlar2(
                    binding.name.text.toString(),
                    binding.surname.text.toString(),
                    binding.fatherName.text.toString(),
                    Mydata.kurslar
                )
            )
            Toast.makeText(context, "Mentor added", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        return binding.root
    }


}