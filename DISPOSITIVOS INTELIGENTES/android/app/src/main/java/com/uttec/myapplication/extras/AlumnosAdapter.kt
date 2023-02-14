package com.uttec.myapplication.extras
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.uttec.myapplication.R

class AlumnosAdapter (private val dataSet: Array<Models.Alumno>) :
    RecyclerView.Adapter<AlumnosAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtNombre: TextView
        val txtEdad: TextView

        init {
            // Define click listener for the ViewHolder's View.
            txtNombre = view.findViewById(R.id.txtNombre)
            txtEdad = view.findViewById(R.id.txtEdad)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.itemView.setOnClickListener {

            var objGson = Gson()
            var json_alumnos=objGson.toJson(dataSet[position])
            val bundle= bundleOf("json_alumnos" to json_alumnos)

            var navController= Navigation.findNavController(it)
            navController.navigate(R.id.nav_nuevo_alumno,bundle)
        }

        viewHolder.txtNombre.text = dataSet[position]?.nombre
        viewHolder.txtEdad.text = dataSet[position]?.edad.toString()

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}