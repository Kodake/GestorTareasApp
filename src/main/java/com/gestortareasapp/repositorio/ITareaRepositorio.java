package com.gestortareasapp.repositorio;

import com.gestortareasapp.modelo.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITareaRepositorio extends JpaRepository<Tarea, Integer> {
}
