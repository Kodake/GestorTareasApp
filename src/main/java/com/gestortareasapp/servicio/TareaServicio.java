package com.gestortareasapp.servicio;

import com.gestortareasapp.modelo.Tarea;
import com.gestortareasapp.repositorio.ITareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServicio implements ITareaServicio {
    @Autowired
    private ITareaRepositorio tareaRepositorio;

    @Override
    public List<Tarea> listar() {
        return tareaRepositorio.findAll();
    }

    @Override
    public Tarea buscarPorId(Integer idTarea) {
        return tareaRepositorio.findById(idTarea).orElse(null);
    }

    @Override
    public void guardar(Tarea tarea) {
        tareaRepositorio.save(tarea);
    }

    @Override
    public void eliminar(Tarea tarea) {
        tareaRepositorio.delete(tarea);
    }
}
