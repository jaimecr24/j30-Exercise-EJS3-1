package com.exercise.ej31.estudianteasignatura.domain;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.profesor.domain.Profesor;
import com.exercise.ej31.shared.StringSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
public class EstudianteAsignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudianteasignatura_seq")
    @GenericGenerator(
            name = "estudianteasignatura_seq",
            strategy = "com.exercise.ej31.shared.StringSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    //@Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    //@Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    private String id_asignatura;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "profesor_id")
    //Profesor profesor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student") // Nombre que tendrá la columna en esta tabla
    private Estudiante estudiante;

    private String asignatura;

    private String comments;

    @NotBlank(message = "initial_date es nulo")
    private Date initial_date;

    private Date finish_date;

}
