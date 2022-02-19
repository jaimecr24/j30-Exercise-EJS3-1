package com.exercise.ej31.profesor.infrastructure;

import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.profesor.domain.Profesor;
import lombok.Data;

import java.util.Date;

@Data
public class ProfesorPersonaInputDTO {
    // Data like person:
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
    // Data like profesor:
    private String comments;
    private String branch;

    public Profesor toProfesor() {
        // We create object Persona
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
        // We create object Estudiante
        Profesor profesor = new Profesor();
        profesor.setPersona(persona);
        profesor.setComments(comments);
        profesor.setBranch(branch);
        return profesor;
    }
}
