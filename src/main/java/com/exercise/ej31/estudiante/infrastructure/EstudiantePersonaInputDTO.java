package com.exercise.ej31.estudiante.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.persona.domain.Persona;

import com.exercise.ej31.persona.infrastructure.PersonaInputDTO;
import com.exercise.ej31.profesor.domain.Profesor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstudiantePersonaInputDTO extends PersonaInputDTO {

    // Specific fields like student:
    private Integer num_hours_week;
    private String comments;
    private String branch;
    private String id_profesor;

    public Estudiante toEstudiante(Profesor profesor) {
        Persona persona = super.toPersona();
        Estudiante estudiante = new Estudiante();
        estudiante.setPersona(persona);
        estudiante.setNum_hours_week(num_hours_week);
        estudiante.setComments(comments);
        estudiante.setBranch(branch);
        estudiante.setProfesor(profesor);
        return estudiante;
    }
}
