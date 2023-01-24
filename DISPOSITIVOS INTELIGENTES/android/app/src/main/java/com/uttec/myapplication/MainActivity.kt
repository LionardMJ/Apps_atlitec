package com.uttec.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.uttec.myapplication.databinding.ActivityMainBinding
import java.io.IOException
import com.uttec.myapplication.extras.*
import okhttp3.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/5087/5087579.png").into(binding.imgLogin)

        //binding.imgLogin
        //binding.txtTexto.text="holaaa"

        binding.btnAccesar.setOnClickListener{
            validaDatos()
        }

    }

    fun validaDatos(){

        var url="http://192.168.100.174:8000/api/login"

        val formBody: RequestBody = FormBody.Builder()
            .add("email",binding.txtUsuario.editText?.text.toString())
            .add("password",binding.txtPassword.text.toString())
            .build()

        val request = Request.Builder().url(url).post(formBody).build()
        val client = OkHttpClient()
        var objGson = Gson()

       client.newCall(request).enqueue(object: Callback {
           override fun onFailure(call: Call, e:IOException){
               println("FALLO: "+e.message.toString())
           }
           override fun onResponse(call:Call, response: Response){
               val respuesta=response.body?.string()
               println(respuesta)

               var objRespuesta=objGson.fromJson(respuesta, Models.RespuestaLogin::class.java)
               println("OK: "+ response.body?.toString())

               if (objRespuesta.acceso=="Ok"){
                   println("TOKEN"+objRespuesta.token)
               }else{
                   println("Error: "+objRespuesta.error)
               }
           }
       })
    }
}