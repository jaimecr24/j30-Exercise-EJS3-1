package com.exercise.ej31.persona.infrastructure;

import com.exercise.ej31.persona.domain.Persona;
import lombok.Data;

import java.util.Date;

@Data
public class PersonaInputDTO {
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    //Date created_date; // Lo asigna automáticamente a Now() al crearlo. Nunca será nulo.
    String imagen_url;
    Date termination_date;
    Boolean admin;

    public Persona toPersona(){
        Persona persona = new Persona();
        persona.setPassword(this.password);
        persona.setUsuario(this.usuario);
        persona.setName(this.name);
        persona.setSurname(this.surname);
        persona.setCompany_email(this.company_email);
        persona.setPersonal_email(this.personal_email);
        persona.setCity(this.city);
        persona.setActive(this.active);
        //persona.setCreated_date(created_date);
        persona.setImagen_url(this.imagen_url);
        persona.setTermination_date(this.termination_date);
        persona.setAdmin(this.admin);
        return persona;
    }
}

