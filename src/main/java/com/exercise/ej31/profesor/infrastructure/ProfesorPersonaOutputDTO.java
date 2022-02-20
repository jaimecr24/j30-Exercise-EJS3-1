package com.exercise.ej31.profesor.infrastructure;

import com.exercise.ej31.persona.infrastructure.PersonaOutputDTO;
import com.exercise.ej31.profesor.domain.Profesor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorPersonaOutputDTO extends PersonaOutputDTO {

    // Specific data like professor:
    private String id_profesor;
    private String comments;
    private String branch;

    public ProfesorPersonaOutputDTO(Profesor profesor){
        super(profesor.getPersona());
        // Specific data like professor
        this.id_profesor = profesor.getId_profesor();
        this.comments = profesor.getComments();
        this.branch = profesor.getBranch();
    }
}
