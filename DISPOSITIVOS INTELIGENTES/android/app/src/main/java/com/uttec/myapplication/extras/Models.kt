package com.uttec.myapplication.extras

class Models {
    data class RespuestaLogin(
        var acceso:String,
        var error:String,
        var token:String
    )
}