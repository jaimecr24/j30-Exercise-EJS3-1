package com.exercise.ej31.estudianteasignatura.application;

import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaInputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaListaOutputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaOutputDTO;


public interface IEstudianteAsignatura {

    EstudianteAsignaturaListaOutputDTO findAll();

    EstudianteAsignaturaListaOutputDTO findByEstudiante(String id) throws Exception;

    EstudianteAsignaturaOutputDTO findById(String id) throws Exception;

    EstudianteAsignaturaOutputDTO addAsignatura(EstudianteAsignaturaInputDTO inputDTO) throws Exception;

    EstudianteAsignaturaOutputDTO putAsignatura(String id, EstudianteAsignaturaInputDTO inputDTO) throws Exception;

    EstudianteAsignaturaOutputDTO delAsignatura(String id) throws Exception;

}
