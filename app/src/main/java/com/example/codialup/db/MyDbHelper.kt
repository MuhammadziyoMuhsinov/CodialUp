package com.example.codialup.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.codialup.models.Guruhlar
import com.example.codialup.models.Kurslar
import com.example.codialup.models.Mentorlar2
import com.example.codialup.models.Student

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1),
    MyServiceInterface {

    companion object {

        const val DB_NAME = "codial_up"


        const val TABLE_KURS = "kurs"
        const val ID_KURS = "id"
        const val NAME_KURS = "name"
        const val ABOUT_KURS = "about"


        const val TABLE_MENTOR = "mentor"
        const val ID_MENTOR = "id"
        const val NAME_MENTOR = "name"
        const val LASTNAME_MENTOR = "lastname"
        const val FATHERNAME_MENTOR = "fathername"
        const val KURS_ID = "kurs_id"

        const val TABLE_GURUH = "guruh"
        const val ID_GURUH = "id"
        const val NAME_GURUH = "name"
        const val MENTOR_ID = "mentor_id"
        const val KUN_GURUH = "kun_guruh"
        const val VAQT_GURUH = "vaqt_guruh"
        const val TYPE_GURUH = "type_guruh"

        const val TABLE_STUDENT = "student"
        const val ID_STUDENT = "id"
        const val NAME_STUDENT = "name"
        const val LASTNAME_STUDENT = "surname"
        const val FATHERNAME_STUDENT = "fathername"
        const val GURUH_ID = "guruh_id"
        const val REG_STUDENT = "regData"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createKurs =
            "CREATE TABLE $TABLE_KURS ($ID_KURS INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME_KURS TEXT NOT NULL, $ABOUT_KURS TEXT NOT NULL)"

        val createMentor =
            "CREATE TABLE $TABLE_MENTOR ($ID_MENTOR INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME_MENTOR TEXT NOT NULL, $LASTNAME_MENTOR TEXT NOT NULL, $FATHERNAME_MENTOR TEXT NOT NULL, $KURS_ID INTEGER NOT NULL, FOREIGN KEY ($KURS_ID) REFERENCES $TABLE_KURS ($ID_KURS))"

        val createGuruh =
            "CREATE TABLE $TABLE_GURUH ($ID_GURUH INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME_GURUH TEXT NOT NULL, $MENTOR_ID INTEGER NOT NULL, $KUN_GURUH TEXT NOT NULL, $VAQT_GURUH TEXT NOT NULL, $TYPE_GURUH INTEGER NOT NULL, FOREIGN KEY ($MENTOR_ID) REFERENCES $TABLE_MENTOR ($ID_MENTOR))"

        val createStudent =
            "CREATE TABLE $TABLE_STUDENT ($ID_STUDENT INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME_STUDENT TEXT NOT NULL, $LASTNAME_STUDENT TEXT NOT NULL, $FATHERNAME_STUDENT TEXT NOT NULL, $REG_STUDENT TEXT NOT NULL, $GURUH_ID INTEGER NOT NULL, FOREIGN KEY ($GURUH_ID) REFERENCES $TABLE_GURUH ($ID_GURUH))"

        db?.execSQL(createKurs)
        db?.execSQL(createMentor)
        db?.execSQL(createGuruh)
        db?.execSQL(createStudent)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    override fun addKurs(kurslar: Kurslar) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME_KURS, kurslar.name)
        contentValue.put(ABOUT_KURS, kurslar.about)
        database.insert(TABLE_KURS, null, contentValue)
        database.close()
    }

    override fun addMentor(mentorlar2: Mentorlar2) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME_MENTOR, mentorlar2.name)
        contentValue.put(LASTNAME_MENTOR, mentorlar2.lastname)
        contentValue.put(FATHERNAME_MENTOR, mentorlar2.fathername)
        contentValue.put(KURS_ID, mentorlar2.kusr?.id)
        database.insert(TABLE_MENTOR, null, contentValue)
        database.close()
    }

    override fun addGuruh(guruhlar: Guruhlar) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME_GURUH, guruhlar.name)
        contentValue.put(MENTOR_ID, guruhlar.mentor?.id)
        contentValue.put(KUN_GURUH, guruhlar.kuni)
        contentValue.put(VAQT_GURUH, guruhlar.vaqti)
        contentValue.put(TYPE_GURUH, guruhlar.type)
        database.insert(TABLE_GURUH, null, contentValue)
        database.close()
    }

    override fun addStudent(student: Student) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME_STUDENT, student.name)
        contentValue.put(LASTNAME_STUDENT, student.lastName)
        contentValue.put(FATHERNAME_STUDENT, student.fatherName)
        contentValue.put(REG_STUDENT, student.regKuni)
        contentValue.put(GURUH_ID, student.group?.id)
        database.insert(TABLE_STUDENT, null, contentValue)
        database.close()
    }


    override fun getAllKurs(): List<Kurslar> {
        val list = ArrayList<Kurslar>()
        val query = "select * from $TABLE_KURS"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val kurs = Kurslar(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(kurs)
            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getAllMentor(): List<Mentorlar2> {
        val list = ArrayList<Mentorlar2>()
        val query = "select * from $TABLE_MENTOR"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val mentor = Mentorlar2(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    getKursById(cursor.getInt(4))
                )
                list.add(mentor)
            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getAllGuruh(): List<Guruhlar> {
        val list = ArrayList<Guruhlar>()
        val query = "select * from $TABLE_GURUH"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val guruh = Guruhlar(
                    cursor.getInt(0),
                    cursor.getString(1),
                    getMentorById(cursor.getInt(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
                )
                list.add(guruh)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getAllStudent(): List<Student> {
        val list = ArrayList<Student>()
        val query = "select * from $TABLE_STUDENT"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val student = Student(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    getGuruhById(cursor.getInt(5))
                )
                list.add(student)
            } while (cursor.moveToNext())
        }
        return list
    }


    override fun getKursById(id: Int): Kurslar {
        val database = this.readableDatabase
        val cursor = database.query(
            TABLE_KURS,
            arrayOf(ID_KURS, NAME_KURS, ABOUT_KURS),
            "$ID_KURS = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val kurs = Kurslar(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
        )

        return kurs
    }

    override fun getMentorById(id: Int): Mentorlar2 {

        val database = this.readableDatabase
        val cursor = database.query(
            TABLE_MENTOR,
            arrayOf(ID_MENTOR, NAME_MENTOR, LASTNAME_MENTOR, FATHERNAME_MENTOR, KURS_ID),
            "$ID_MENTOR = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val mentor = Mentorlar2(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            getKursById(cursor.getInt(4))
        )

        return mentor

    }

    override fun getGuruhById(id: Int): Guruhlar {
        val database = this.readableDatabase
        val cursor = database.query(
            TABLE_GURUH,
            arrayOf(ID_GURUH, NAME_GURUH, MENTOR_ID, KUN_GURUH, VAQT_GURUH, TYPE_GURUH),
            "$ID_GURUH = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val guruh = Guruhlar(
            cursor.getInt(0),
            cursor.getString(1),
            getMentorById(cursor.getInt(2)),
            cursor.getString(3),
            cursor.getString(4),
            cursor.getInt(5)
        )

        return guruh
    }

    override fun deleteMentor(mentorlar2: Mentorlar2) {
        val database = this.writableDatabase
        database.delete(TABLE_MENTOR, "$ID_MENTOR = ?", arrayOf(mentorlar2.id.toString()))
        database.close()
    }

    override fun deleteGuruh(guruhlar: Guruhlar) {
        val database = this.writableDatabase
        database.delete(TABLE_GURUH, "$ID_GURUH = ?", arrayOf(guruhlar.id.toString()))
        database.close()
    }

    override fun deleteStudent(student: Student) {
        val database = this.writableDatabase
        database.delete(TABLE_STUDENT, "$ID_STUDENT = ?", arrayOf(student.id.toString()))
        database.close()
    }


    override fun updateMentor(mentorlar2: Mentorlar2): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID_MENTOR,mentorlar2.id)
        contentValues.put(NAME_MENTOR, mentorlar2.name)
        contentValues.put(LASTNAME_MENTOR, mentorlar2.lastname)
        contentValues.put(FATHERNAME_MENTOR, mentorlar2.fathername)
        return database.update(TABLE_MENTOR, contentValues, "$ID_MENTOR = ?", arrayOf(mentorlar2.id.toString()))
    }

    override fun updateGuruh(guruhlar: Guruhlar): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID_GURUH,guruhlar.id)
        contentValues.put(NAME_GURUH, guruhlar.name)
        contentValues.put(MENTOR_ID, guruhlar.mentor?.id)
        contentValues.put(KUN_GURUH, guruhlar.kuni)
        contentValues.put(VAQT_GURUH, guruhlar.vaqti)
        contentValues.put(TYPE_GURUH, guruhlar.type)
        return database.update(TABLE_GURUH, contentValues, "$ID_GURUH = ?", arrayOf(guruhlar.id.toString()))
    }

    override fun updateStudent(student: Student): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID_STUDENT,student.id)
        contentValues.put(NAME_STUDENT, student.name)
        contentValues.put(LASTNAME_STUDENT, student.lastName)
        contentValues.put(FATHERNAME_STUDENT, student.fatherName)
        contentValues.put(REG_STUDENT, student.regKuni)
        contentValues.put(GURUH_ID, student.group?.id)
        return database.update(TABLE_STUDENT, contentValues, "$ID_STUDENT = ?", arrayOf(student.id.toString()))
    }


}