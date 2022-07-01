package com.example.codialup.models

class Student {
    var id: Int? = null
    var name: String? = null
    var lastName:String? = null
    var fatherName:String? = null
    var regKuni:String? = null
    var group:Guruhlar? = null

    constructor(
        id: Int?,
        name: String?,
        lastName: String?,
        fatherName: String?,
        regKuni: String?,
        group: Guruhlar?
    ) {
        this.id = id
        this.name = name
        this.lastName = lastName
        this.fatherName = fatherName
        this.regKuni = regKuni
        this.group = group
    }

    constructor(
        name: String?,
        lastName: String?,
        fatherName: String?,
        regKuni: String?,
        group: Guruhlar?
    ) {
        this.name = name
        this.lastName = lastName
        this.fatherName = fatherName
        this.regKuni = regKuni
        this.group = group
    }


}