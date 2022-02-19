package com.exercise.ej31.estudiante.domain;

import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.shared.StringSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @GenericGenerator(
            name = "student_seq",
            strategy = "com.exercise.ej31.shared.StringSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    //@Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    //@Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    private String id_student;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @NotBlank(message = "num_hours_week es nulo")
    private Integer num_hours_week;

    private String comments;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id_profesor")
    //private String id_profesor;

    @NotBlank(message = "branch es nulo")
    private String branch;

    //@OneToMany
    //List<EstudianteAsignatura> estudianteAsignaturaList;

}
