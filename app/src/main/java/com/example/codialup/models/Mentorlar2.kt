package com.example.codialup.models

class Mentorlar2 {

    var id:Int? = null
    var name:String? = null
    var lastname:String? = null
    var fathername:String? = null
    var kusr:Kurslar? = null


    constructor(id: Int?, name: String?, lastname: String?, fathername: String?, kusr: Kurslar?) {
        this.id = id
        this.name = name
        this.lastname = lastname
        this.fathername = fathername
        this.kusr = kusr
    }

    constructor(name: String?, lastname: String?, fathername: String?, kusr: Kurslar?) {
        this.name = name
        this.lastname = lastname
        this.fathername = fathername
        this.kusr = kusr
    }


}