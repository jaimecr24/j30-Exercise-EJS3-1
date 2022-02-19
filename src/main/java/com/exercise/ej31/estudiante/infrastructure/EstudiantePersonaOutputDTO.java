package com.exercise.ej31.estudiante.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.persona.domain.Persona;
import lombok.Data;

import java.util.Date;

@Data
public class EstudiantePersonaOutputDTO {
    // Data like person:
    private String id_estudiante;
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
    // Specific data like student:
    private Integer num_hours_week;
    private String comments;
    private String branch;

    public EstudiantePersonaOutputDTO(Estudiante estudiante){
        // Data like persona
        Persona persona = estudiante.getPersona();
        this.id_estudiante = estudiante.getId_student();
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
        // Specific data like student
        this.num_hours_week = estudiante.getNum_hours_week();
        this.comments = estudiante.getComments();
        this.branch = estudiante.getBranch();
    }
}
