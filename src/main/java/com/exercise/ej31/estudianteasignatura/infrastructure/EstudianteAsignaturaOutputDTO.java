package com.exercise.ej31.estudianteasignatura.infrastructure;

import com.exercise.ej31.estudianteasignatura.domain.EstudianteAsignatura;
import lombok.Data;

import java.util.Date;

@Data
public class EstudianteAsignaturaOutputDTO {

    private String id_asignatura;
    private String id_estudiante;
    private String id_profesor;
    private String asignatura;
    private String comments;
    private Date initial_date;
    private Date finish_date;

    public EstudianteAsignaturaOutputDTO(EstudianteAsignatura estudianteAsignatura){
        this.id_asignatura = estudianteAsignatura.getId_asignatura();
        this.id_estudiante = estudianteAsignatura.getEstudiante().getId_student();
        this.id_profesor = estudianteAsignatura.getProfesor().getId_profesor();
        this.asignatura = estudianteAsignatura.getAsignatura();
        this.comments = estudianteAsignatura.getComments();
        this.initial_date = estudianteAsignatura.getInitial_date();
        this.finish_date = estudianteAsignatura.getFinish_date();
    }
}
