package com.exercise.ej31.persona.application;

import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.persona.infrastructure.PersonaInputDTO;
import com.exercise.ej31.persona.infrastructure.PersonaOutputDTO;

import java.util.List;

public interface IPersona {
    List<PersonaOutputDTO> findAll();
    List<PersonaOutputDTO> getByUser(String usuario);
    PersonaOutputDTO getById(String id) throws Exception;
    PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO putPersona(String id, PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO delPersona(String id) throws Exception;

    Persona getObject(String id) throws Exception;
}
