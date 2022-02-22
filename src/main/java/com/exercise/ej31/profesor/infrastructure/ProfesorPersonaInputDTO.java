package com.exercise.ej31.profesor.infrastructure;

import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.persona.infrastructure.PersonaInputDTO;
import com.exercise.ej31.profesor.domain.Profesor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfesorPersonaInputDTO extends PersonaInputDTO {
    // Specific fields like profesor:
    private String comments;
    private String branch;

    public Profesor toProfesor() {
        // We create object Estudiante
        Profesor profesor = new Profesor();
        Persona persona = super.toPersona();
        profesor.setPersona(persona);
        profesor.setComments(comments);
        profesor.setBranch(branch);
        return profesor;
    }
}
