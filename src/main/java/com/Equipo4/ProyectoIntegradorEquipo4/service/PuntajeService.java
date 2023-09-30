package com.Equipo4.ProyectoIntegradorEquipo4.service;

import com.Equipo4.ProyectoIntegradorEquipo4.entities.Puntaje;
import com.Equipo4.ProyectoIntegradorEquipo4.entities.PuntajeRespuesta;
import com.Equipo4.ProyectoIntegradorEquipo4.entities.Recursos;
import com.Equipo4.ProyectoIntegradorEquipo4.entities.Usuario;
import com.Equipo4.ProyectoIntegradorEquipo4.repository.IPuntajeRepository;
import com.Equipo4.ProyectoIntegradorEquipo4.repository.IRecursosRepository;
import com.Equipo4.ProyectoIntegradorEquipo4.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntajeService implements IPuntajeService {

    private final IPuntajeRepository puntajeRepository;
    private final IRecursosRepository recursosRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PuntajeService(IPuntajeRepository puntajeRepository, IRecursosRepository recursosRepository, UsuarioRepository usuarioRepository) {
        this.puntajeRepository = puntajeRepository;
        this.recursosRepository = recursosRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Puntaje guardarPuntaje(Puntaje puntaje) throws Exception {
        validarPuntaje(puntaje);

        Recursos recurso = recursosRepository.findById(puntaje.getIdRecurso())
                .orElseThrow(() -> new Exception("El recurso no existe"));

        Usuario usuario = usuarioRepository.findById(puntaje.getIdUsuario())
                .orElseThrow(() -> new Exception("El usuario no existe"));

        Puntaje nuevoPuntaje = new Puntaje();
        nuevoPuntaje.setIdUsuario(usuario.getIdUsuario());
        nuevoPuntaje.setIdRecurso(recurso.getIdRecurso());
        nuevoPuntaje.setPuntuacion(puntaje.getPuntuacion());
        nuevoPuntaje.setComentario(puntaje.getComentario());
        nuevoPuntaje.setFecha_valoracion(puntaje.getFecha_valoracion());

        int resultKey = puntajeRepository.save(nuevoPuntaje);
        nuevoPuntaje.setIdPuntuacion(resultKey);
        System.out.println("INFO:" + nuevoPuntaje.toString());
        System.out.println("INFO:" + nuevoPuntaje.getIdPuntuacion());

        return nuevoPuntaje;
    }

    public List<PuntajeRespuesta> devolverPuntajesPorRecurso(Integer idRecurso) throws Exception {
        Recursos recurso = recursosRepository.findById(idRecurso)
                .orElseThrow(() -> new Exception("El recurso no existe"));

        List<PuntajeRespuesta> puntajes = puntajeRepository.findAllByRecurso(idRecurso);


        if (puntajes.isEmpty()) {
            throw new Exception("El recurso no tiene valoraciones");
        }

        return puntajes;
    }

    public Double calculateAverageByRecurso(Integer idRecurso) throws Exception {

        Recursos recurso = recursosRepository.findById(idRecurso)
                .orElseThrow(() -> new Exception("El recurso no existe"));
        /*List<PuntajeRespuesta> puntajes = puntajeRepository.findAllByRecurso(idRecurso);

        if (puntajes.isEmpty()) {
            throw new Exception("El recurso no tiene valoraciones");
        }

        double sumaPuntuaciones = 0;
        for (Puntaje puntaje : puntajes) {
            sumaPuntuaciones += puntaje.getPuntuacion();
        }
        return sumaPuntuaciones / puntajes.size();*/
        Double promedio = puntajeRepository.calculateAverageByRecurso(idRecurso);
        return promedio;
    }

    private void validarPuntaje(Puntaje puntaje) throws Exception {
        if (puntaje == null || puntaje.getComentario() == null || puntaje.getPuntuacion() == null) {
            throw new Exception("El puntaje debe contener comentario y puntuación");
        }
    }
}