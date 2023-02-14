package com.uttec.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.uttec.myapplication.R
import com.uttec.myapplication.databinding.FragmentNuevoAlumnoBinding
import com.uttec.myapplication.extras.Models

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "json_alumnos"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NuevoAlumnoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NuevoAlumnoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var json_alumnos: String? = null
    private var param2: String? = null

    private var _binding: FragmentNuevoAlumnoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            json_alumnos = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_nuevo_alumno, container, false)
        _binding = FragmentNuevoAlumnoBinding.inflate(inflater, container, false)
        val view = binding.root

        if (json_alumnos!=null){
            var gson= Gson()
            var objAlumno=gson.fromJson(json_alumnos, Models.Alumno::class.java)
            binding.txtNombre.editText?.setText(objAlumno.nombre)
            binding.txtMatricula.editText?.setText(objAlumno.matricula)
            binding.txtCarrera.editText?.setText(objAlumno.carrera)
            binding.txtEdad.editText?.setText(objAlumno.edad.toString())
            binding.txtApp.editText?.setText(objAlumno.app)
            binding.txtApm.editText?.setText(objAlumno.apm)
        }

        //binding.btnGuardar.setOnClickListener {  }



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NuevoAlumnoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NuevoAlumnoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}