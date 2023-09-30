package com.Equipo4.ProyectoIntegradorEquipo4.service;

import com.Equipo4.ProyectoIntegradorEquipo4.entities.Categorias_x_Recurso;
import com.Equipo4.ProyectoIntegradorEquipo4.entities.Categorias_x_RecursoRespuesta;

import java.util.List;
import java.util.Optional;

public interface ICategorias_x_RecursosService {
    public List<Categorias_x_Recurso> findAll();
    public int save(Categorias_x_Recurso categorias_x_recurso);
    public int update(Categorias_x_Recurso categorias_x_recurso);
    public int deleteById(int id);
    Optional<Categorias_x_Recurso> findById(int id);
    public List<Categorias_x_RecursoRespuesta> devolverPuntajesPorRecurso(Integer idRecurso) throws Exception;
}
