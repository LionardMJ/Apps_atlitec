package com.uttec.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.uttec.myapplication.databinding.FragmentHomeBinding
import com.uttec.myapplication.extras.AlumnosAdapter
import com.uttec.myapplication.extras.Models
import com.uttec.myapplication.extras.VariablesGlobales
import okhttp3.*
import java.io.IOException
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        obtenerDatos()
        return root
    }

    fun obtenerDatos(){

        var url = VariablesGlobales.url_almnos

        val request = Request.Builder()
            .url(url)
            .header("Accept", "application/json")
            //.header("Authorization", "Bearer " + TOKEN)
            .get()
            .build()
        val client = OkHttpClient()
        var gson = Gson()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity?.runOnUiThread {
                    Toast.makeText(context, "Ocurrio un error: " + e.message.toString(), Toast.LENGTH_LONG).show();
                }
            }

            override fun onResponse(call: Call, response: Response) {
                var respuesta = response.body?.string()

                println("Respuesta: " + respuesta)

                activity?.runOnUiThread {
                    var listItems = gson.fromJson(respuesta, Array<Models.Alumno>::class.java)

                    val adapter = AlumnosAdapter(listItems)
                    binding.rvDatos.layoutManager = LinearLayoutManager(context)
                    binding.rvDatos.adapter = adapter
                }

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}