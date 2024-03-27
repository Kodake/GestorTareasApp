package com.gestortareasapp.servicio;

import com.gestortareasapp.modelo.Tarea;
import java.util.List;

public interface ITareaServicio {
    public List<Tarea> listar();
    public Tarea buscarPorId(Integer idTarea);
    public void guardar(Tarea tarea);
    public void eliminar(Tarea tarea);
}
