package com.exercise.ej31.estudiante.application;

import com.exercise.ej31.estudiante.infrastructure.EstudianteOutputDTO;
import com.exercise.ej31.estudiante.infrastructure.EstudiantePersonaInputDTO;
import com.exercise.ej31.estudiante.infrastructure.EstudiantePersonaOutputDTO;

import java.util.List;

public interface IEstudiante {

    List<EstudiantePersonaOutputDTO> findAll();

    EstudiantePersonaOutputDTO getFullById(String id) throws Exception;

    EstudianteOutputDTO getSimpleById(String id) throws Exception;

    EstudiantePersonaOutputDTO addEstudiante(EstudiantePersonaInputDTO inputDTO) throws Exception;

    EstudiantePersonaOutputDTO putEstudiante(String id, EstudiantePersonaInputDTO inputDTO) throws Exception;

    EstudiantePersonaOutputDTO delEstudiante(String id) throws Exception;
}
