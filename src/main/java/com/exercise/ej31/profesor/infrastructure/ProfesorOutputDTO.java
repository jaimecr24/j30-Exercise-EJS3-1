package com.exercise.ej31.profesor.infrastructure;

import com.exercise.ej31.profesor.domain.Profesor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorOutputDTO {

    private String id_profesor;
    private String comments;
    private String branch;

    public ProfesorOutputDTO(Profesor profesor){
        this.id_profesor = profesor.getId_profesor();
        this.comments = profesor.getComments();
        this.branch = profesor.getBranch();
    }
}
