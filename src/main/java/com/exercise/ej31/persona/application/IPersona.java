package com.exercise.ej31.persona.application;

import com.exercise.ej31.persona.infrastructure.PersonaInputDTO;
import com.exercise.ej31.persona.infrastructure.PersonaListaOutputDTO;
import com.exercise.ej31.persona.infrastructure.PersonaOutputDTO;

import java.util.List;

public interface IPersona {

    PersonaListaOutputDTO findAll(String outputType);
    PersonaListaOutputDTO getByUser(String usuario, String outputType);
    PersonaOutputDTO getById(String id, String outputType) throws Exception;
    PersonaOutputDTO getExtendedById(String id, String outputType) throws Exception;
    PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO putPersona(String id, PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO delPersona(String id) throws Exception;
}
