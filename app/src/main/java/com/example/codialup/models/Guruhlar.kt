package com.example.codialup.models

class Guruhlar {
    var id:Int? = null
    var name:String? = null
    var mentor:Mentorlar2? = null
    var kuni:String? = null
    var vaqti:String? = null
    var type:Int? = null

    constructor(id: Int?, name: String?, mentor: Mentorlar2?, kuni: String?, vaqti: String?, type:Int?) {
        this.id = id
        this.name = name
        this.mentor = mentor
        this.kuni = kuni
        this.vaqti = vaqti
        this.type = type
    }

    constructor(name: String?, mentor: Mentorlar2?, kuni: String?, vaqti: String?, type:Int?) {
        this.name = name
        this.mentor = mentor
        this.kuni = kuni
        this.vaqti = vaqti
        this.type = type
    }


}