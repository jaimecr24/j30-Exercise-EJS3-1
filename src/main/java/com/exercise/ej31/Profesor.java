package com.exercise.ej31;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
//@Entity
public class Profesor {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    String id_profesor;

    //@OneToOne
    //@JoinColumn(name = "id_persona")
    String id_persona;

    String comments;

    //@NotBlank(message = "branch es nulo")
    String branch;

    //@OneToMany
    //List<Estudiante> estudiantes;

    //@OneToMany
    //List<EstudianteAsignatura> estudianteAsignaturaList;

}