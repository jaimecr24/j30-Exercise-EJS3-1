package com.exercise.ej31.profesor.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.estudiante.infrastructure.EstudianteAsignaturasDoneOutputDTO;
import com.exercise.ej31.persona.infrastructure.PersonaOutputDTO;
import com.exercise.ej31.profesor.domain.Profesor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProfesorExtendedOutputDTO extends PersonaOutputDTO {

    private String id_profesor;
    private String comments;
    private String branch;
    // Estudiantes a su cargo con las asignaturas finalizadas.
    private List<EstudianteAsignaturasDoneOutputDTO> estudiantes = new ArrayList<>();

    public ProfesorExtendedOutputDTO(Profesor profesor) {
        super(profesor.getPersona());
        this.comments = profesor.getComments();
        this.branch = profesor.getBranch();
        List<Estudiante> listaEstudiantes = profesor.getEstudiantes();
        for (Estudiante e:listaEstudiantes) {
            estudiantes.add(new EstudianteAsignaturasDoneOutputDTO(e));
        }
    }
}
