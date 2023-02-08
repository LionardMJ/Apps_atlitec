package com.uttec.myapplication.extras

class Models {
    data class RespuestaLogin(
        var acceso:String,
        var error:String,
        var token:String
    )

    data class Alumno(
        var Id:Int,
        var nombre:String,
        var app:String,
        var apm:String,
        var matricula:String,
        var carrera:String,
        var edad:Int,
    )
}
