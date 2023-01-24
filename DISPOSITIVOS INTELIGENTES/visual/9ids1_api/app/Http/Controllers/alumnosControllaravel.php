<?php

namespace App\Http\Controllers;

use App\Models\m_alumnos;
use Illuminate\Http\Request;

class alumnosControllaravel extends Controller
{
    public function guardar(Request $request){

        if($request->id>0){
            $alumno=m_alumnos::find($request->id);
        }else{
            $alumno=new m_alumnos();
        }

        $alumno->nombre=$request->nombre;
        $alumno->matricula=$request->matricula;
        $alumno->carrera=$request->carrera;
        $alumno->edad=$request->edad;
        $alumno->app=$request->app;
        $alumno->apm=$request->apm;
        $alumno->save();

        return $alumno;
    }

    public function eliminar(Request $request){
        $alumno=m_alumnos::find($request->id);
        $alumno->delete();
        return "ok";
    }
}
