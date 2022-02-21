package com.exercise.ej31.estudiante.application;

import com.exercise.ej31.estudiante.infrastructure.EstudianteOutputDTO;
import com.exercise.ej31.estudiante.infrastructure.EstudiantePersonaInputDTO;
import com.exercise.ej31.estudiante.infrastructure.EstudiantePersonaOutputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaInputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaOutputDTO;

import java.util.List;

public interface IEstudiante {

    List<EstudiantePersonaOutputDTO> findAll();

    EstudiantePersonaOutputDTO getFullById(String id) throws Exception;

    EstudianteOutputDTO getSimpleById(String id) throws Exception;

    EstudiantePersonaOutputDTO addEstudiante(EstudiantePersonaInputDTO inputDTO) throws Exception;

    List<EstudianteAsignaturaOutputDTO> addAsignaturas(String id_estudiante, List<EstudianteAsignaturaInputDTO> listaDTO) throws Exception;

    List<EstudianteAsignaturaOutputDTO> delAsignaturas(String id_estudiante, List<String> id_asignaturas) throws Exception;

    EstudiantePersonaOutputDTO patchProfesor(String id_estudiante, String id_profesor) throws Exception;

    EstudiantePersonaOutputDTO putEstudiante(String id, EstudiantePersonaInputDTO inputDTO) throws Exception;

    EstudiantePersonaOutputDTO delEstudiante(String id) throws Exception;
}
