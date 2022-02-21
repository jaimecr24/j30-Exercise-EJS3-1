package com.exercise.ej31.estudiante.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.persona.infrastructure.PersonaOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EstudiantePersonaOutputDTO extends PersonaOutputDTO {
    // inherit from PersonaOutputDTO permits return this class also as a PersonaOutputDTO

    private String id_estudiante;
    private Integer num_hours_week;
    private String comments;
    private String branch;
    private String id_profesor;

    public EstudiantePersonaOutputDTO(Estudiante estudiante){
        super(estudiante.getPersona());
        this.id_estudiante = estudiante.getId_student();
        this.num_hours_week = estudiante.getNum_hours_week();
        this.comments = estudiante.getComments();
        this.branch = estudiante.getBranch();
        this.id_profesor = (estudiante.getProfesor()==null) ? null : estudiante.getProfesor().getId_profesor();
    }
}
