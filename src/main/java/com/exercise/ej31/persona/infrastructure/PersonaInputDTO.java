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
    Date created_date;
    String imagen_url;
    Date termination_date;

    public Persona toPersona(){
        Persona persona = new Persona();
        persona.setPassword(password);
        persona.setUsuario(usuario);
        persona.setName(name);
        persona.setSurname(surname);
        persona.setCompany_email(company_email);
        persona.setPersonal_email(personal_email);
        persona.setCity(city);
        persona.setActive(active);
        persona.setCreated_date(created_date);
        persona.setImagen_url(imagen_url);
        persona.setTermination_date(termination_date);
        return persona;
    }
}

