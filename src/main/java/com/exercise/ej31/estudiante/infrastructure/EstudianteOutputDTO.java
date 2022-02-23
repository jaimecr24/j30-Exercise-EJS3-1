package com.exercise.ej31.estudiante.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.persona.infrastructure.OutputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudianteOutputDTO extends OutputDTO {

    private String id_estudiante;
    private Integer num_hours_week;
    private String comments;
    private String branch;
    private String id_profesor;

    public EstudianteOutputDTO(Estudiante estudiante){
        super();
        this.id_estudiante = estudiante.getId_student();
        this.num_hours_week = estudiante.getNum_hours_week();
        this.comments = estudiante.getComments();
        this.branch = estudiante.getBranch();
        this.id_profesor = (estudiante.getProfesor()==null) ? null : estudiante.getProfesor().getId_profesor();
    }
}
