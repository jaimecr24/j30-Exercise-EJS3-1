package com.exercise.ej31.estudianteasignatura.application;

import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaInputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaOutputDTO;

import java.util.List;

public interface IEstudianteAsignatura {

    List<EstudianteAsignaturaOutputDTO> findAll();

    List<EstudianteAsignaturaOutputDTO> findByEstudiante(String id_estudiante);

    EstudianteAsignaturaOutputDTO findById(String id) throws Exception;

    EstudianteAsignaturaOutputDTO addAsignatura(EstudianteAsignaturaInputDTO inputDTO) throws Exception;

    EstudianteAsignaturaOutputDTO putAsignatura(String id, EstudianteAsignaturaInputDTO inputDTO) throws Exception;

    EstudianteAsignaturaOutputDTO delAsignatura(String id) throws Exception;

}
