package com.exercise.ej31.profesor.application;

import com.exercise.ej31.profesor.infrastructure.ProfesorPersonaInputDTO;
import com.exercise.ej31.profesor.infrastructure.ProfesorPersonaOutputDTO;

import java.util.List;

public interface IProfesor {

    List<ProfesorPersonaOutputDTO> findAll();

    ProfesorPersonaOutputDTO getById(String id) throws Exception;

    ProfesorPersonaOutputDTO addProfesor(ProfesorPersonaInputDTO inputDTO) throws Exception;

    ProfesorPersonaOutputDTO putProfesor(String id, ProfesorPersonaInputDTO inputDTO) throws Exception;

    ProfesorPersonaOutputDTO delProfesor(String id) throws Exception;
}
