package com.exercise.ej31.profesor.application;

import com.exercise.ej31.estudiante.infrastructure.EstudianteListaOutputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaListaOutputDTO;
import com.exercise.ej31.profesor.infrastructure.ProfesorPersonaInputDTO;
import com.exercise.ej31.profesor.infrastructure.ProfesorPersonaListaOutputDTO;
import com.exercise.ej31.profesor.infrastructure.ProfesorPersonaOutputDTO;

public interface IProfesor {

    ProfesorPersonaListaOutputDTO findAll();

    ProfesorPersonaOutputDTO getById(String id) throws Exception;

    EstudianteListaOutputDTO getEstudiantes(String id) throws Exception;

    EstudianteAsignaturaListaOutputDTO getAsignaturas(String id) throws Exception;

    ProfesorPersonaOutputDTO addProfesor(ProfesorPersonaInputDTO inputDTO) throws Exception;

    ProfesorPersonaOutputDTO putProfesor(String id, ProfesorPersonaInputDTO inputDTO) throws Exception;

    ProfesorPersonaOutputDTO delProfesor(String id) throws Exception;
}
