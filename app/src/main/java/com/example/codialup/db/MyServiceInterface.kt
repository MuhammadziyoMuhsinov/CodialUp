package com.example.codialup.db

import com.example.codialup.models.Guruhlar
import com.example.codialup.models.Kurslar
import com.example.codialup.models.Mentorlar2
import com.example.codialup.models.Student

interface MyServiceInterface {

    fun addKurs(kurslar: Kurslar)
    fun addMentor(mentorlar2: Mentorlar2)
    fun addGuruh(guruhlar: Guruhlar)
    fun addStudent(student: Student)

    fun getAllKurs():List<Kurslar>
    fun getAllMentor():List<Mentorlar2>
    fun getAllGuruh():List<Guruhlar>
    fun getAllStudent():List<Student>

    fun getKursById(id:Int):Kurslar
    fun getMentorById(id:Int):Mentorlar2
    fun getGuruhById(id:Int):Guruhlar

    fun deleteMentor(mentorlar2: Mentorlar2)
    fun deleteGuruh(guruhlar: Guruhlar)
    fun deleteStudent(student: Student)

    fun updateMentor(mentorlar2: Mentorlar2) : Int
    fun updateGuruh(guruhlar: Guruhlar) : Int
    fun updateStudent(student: Student) : Int

}