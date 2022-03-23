package com.exercise.ej31.persona.infrastructure;

import com.exercise.ej31.persona.domain.Persona;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonaOutputDTO extends OutputDTO {
    private String id_persona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private Boolean admin;

    public PersonaOutputDTO(Persona persona){
        super();
        this.id_persona = persona.getId_persona();
        this.usuario = persona.getUsuario();
        this.password = persona.getPassword();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.company_email = persona.getCompany_email();
        this.personal_email = persona.getPersonal_email();
        this.city = persona.getCity();
        this.active = persona.getActive();
        this.created_date = persona.getCreated_date();
        this.imagen_url = persona.getImagen_url();
        this.termination_date = persona.getTermination_date();
        this.admin = persona.getAdmin();
    }
}

