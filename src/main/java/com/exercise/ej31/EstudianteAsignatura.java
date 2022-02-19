package com.exercise.ej31;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

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
