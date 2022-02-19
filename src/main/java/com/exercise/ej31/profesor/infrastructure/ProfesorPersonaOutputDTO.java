package com.exercise.ej31.profesor.infrastructure;

import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.profesor.domain.Profesor;
import lombok.Data;

import java.util.Date;

@Data
public class ProfesorPersonaOutputDTO {

    // Data like person:
    private String id_profesor;
    private String id_persona;
    private String usuario;
    //private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    // Specific data like professor:
    private String comments;
    private String branch;

    public ProfesorPersonaOutputDTO(Profesor profesor){
        // Data like persona
        Persona persona = profesor.getPersona();
        this.id_profesor = profesor.getId_profesor();
        this.id_persona = persona.getId_persona();
        this.usuario = persona.getUsuario();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.company_email = persona.getCompany_email();
        this.personal_email = persona.getPersonal_email();
        this.city = persona.getCity();
        this.active = persona.getActive();
        this.created_date = persona.getCreated_date();
        this.imagen_url = persona.getImagen_url();
        this.termination_date = persona.getTermination_date();
        // Specific data like professor
        this.comments = profesor.getComments();
        this.branch = profesor.getBranch();
    }
}
