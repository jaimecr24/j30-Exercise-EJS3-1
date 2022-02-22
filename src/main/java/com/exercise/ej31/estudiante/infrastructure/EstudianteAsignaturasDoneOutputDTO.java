package com.exercise.ej31.estudiante.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.estudianteasignatura.domain.EstudianteAsignatura;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaOutputDTO;
import com.exercise.ej31.profesor.infrastructure.ProfesorOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EstudianteAsignaturasDoneOutputDTO {

    private String id_estudiante;
    private Integer num_hours_week;
    private String comments;
    private String branch;
    private String id_profesor;
    private List<EstudianteAsignaturaOutputDTO> estudios = new ArrayList<>(); // Estudios realizados.

    public EstudianteAsignaturasDoneOutputDTO(Estudiante estudiante){
        this.id_estudiante = estudiante.getId_student();
        this.num_hours_week = estudiante.getNum_hours_week();
        this.comments = estudiante.getComments();
        this.branch = estudiante.getBranch();
        this.id_profesor = (estudiante.getProfesor()==null) ? null : estudiante.getProfesor().getId_profesor();
        List<EstudianteAsignatura> asignaturas = estudiante.getEstudianteAsignaturaList();
        for (EstudianteAsignatura asignatura:asignaturas) {
            if (asignatura.getFinish_date()!=null) { // Asignatura finalizada
                estudios.add(new EstudianteAsignaturaOutputDTO(asignatura));
            }
        }
    }
}
