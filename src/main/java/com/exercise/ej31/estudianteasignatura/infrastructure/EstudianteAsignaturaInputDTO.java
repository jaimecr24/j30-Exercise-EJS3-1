package com.exercise.ej31.estudianteasignatura.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.estudianteasignatura.domain.EstudianteAsignatura;
import lombok.Data;

import java.util.Date;

@Data
public class EstudianteAsignaturaInputDTO {

    private String id_estudiante;
    private String asignatura;
    private String comments;
    private Date initial_date;
    private Date finish_date;

    public EstudianteAsignatura toEstudianteAsignatura(Estudiante estudiante) {
        EstudianteAsignatura estudianteAsignatura = new EstudianteAsignatura();
        estudianteAsignatura.setEstudiante(estudiante);
        estudianteAsignatura.setAsignatura(asignatura);
        estudianteAsignatura.setComments(comments);
        estudianteAsignatura.setInitial_date(initial_date);
        estudianteAsignatura.setFinish_date(finish_date);
        return estudianteAsignatura;
    }
}
