package com.exercise.ej31.estudianteasignatura.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.estudianteasignatura.domain.EstudianteAsignatura;
import com.exercise.ej31.profesor.domain.Profesor;
import lombok.Data;

import java.util.Date;

@Data
public class EstudianteAsignaturaInputDTO {

    private String id_estudiante;
    private String id_profesor;
    private String asignatura;
    private String comments;
    private Date initial_date;
    private Date finish_date;

    public EstudianteAsignatura toEstudianteAsignatura(Estudiante estudiante, Profesor profesor) {
        EstudianteAsignatura estudianteAsignatura = new EstudianteAsignatura();
        estudianteAsignatura.setEstudiante(estudiante);
        estudianteAsignatura.setProfesor(profesor);
        estudianteAsignatura.setAsignatura(asignatura);
        estudianteAsignatura.setComments(comments);
        estudianteAsignatura.setInitial_date(initial_date);
        estudianteAsignatura.setFinish_date(finish_date);
        return estudianteAsignatura;
    }
}
