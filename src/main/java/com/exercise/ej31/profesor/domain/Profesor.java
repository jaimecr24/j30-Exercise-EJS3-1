package com.exercise.ej31.profesor.domain;

import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.shared.StringSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesor_seq")
    @GenericGenerator(
            name = "profesor_seq",
            strategy = "com.exercise.ej31.shared.StringSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    //@Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    //@Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    private String id_profesor;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "id_persona")
    private Persona persona;

    private String comments;

    @NotBlank(message = "branch es nulo")
    String branch;

    //@OneToMany
    //List<Estudiante> estudiantes;

    //@OneToMany
    //List<EstudianteAsignatura> estudianteAsignaturaList;

}