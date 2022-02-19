package com.exercise.ej31;

import com.exercise.ej31.profesor.domain.Profesor;
import lombok.Data;

import java.util.Date;

@Data
//@Entity
public class EstudianteAsignatura {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    String id_asignatura;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "profesor_id")
    Profesor profesor;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "id_student")
    String id_student;

    String asignatura;

    String comments;

    //@NotBlank(message = "initial_date es nulo")
    Date initial_date;

    Date finish_date;

}
