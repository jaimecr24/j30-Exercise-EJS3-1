package com.exercise.ej31.persona.application;

import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.persona.infrastructure.PersonaInputDTO;
import com.exercise.ej31.persona.infrastructure.PersonaOutputDTO;
import com.exercise.ej31.persona.infrastructure.PersonaRepo;
import com.exercise.ej31.shared.NotFoundException;
import com.exercise.ej31.shared.UnprocesableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonaService implements IPersona {

    private final PersonaRepo personaRepo;

    public PersonaService(PersonaRepo personaRepo){
        super();
        this.personaRepo = personaRepo;
    }

    @Override
    public List<PersonaOutputDTO> findAll() {
        List<Persona> listaPersona = personaRepo.findAll();
        List<PersonaOutputDTO> listaPersonaDTO = new ArrayList<>();
        for (Persona persona:listaPersona) listaPersonaDTO.add(new PersonaOutputDTO(persona));
        return listaPersonaDTO;
    }

    @Override
    public List<PersonaOutputDTO> getByUser(String usuario) {
        List<Persona> listaPersona = personaRepo.findByUsuario(usuario);
        List<PersonaOutputDTO> listaPersonaDTO = new ArrayList<>();
        for (Persona persona:listaPersona) listaPersonaDTO.add(new PersonaOutputDTO(persona));
        return listaPersonaDTO;
    }

    @Override
    public PersonaOutputDTO getById(String id) throws NotFoundException {
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id_persona: "+id+" not found."));
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws UnprocesableException {
        this.validate(personaInputDTO);
        Persona persona = personaInputDTO.toPersona();
        persona.setCreated_date(new Date());
        personaRepo.save(persona);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO putPersona(String id, PersonaInputDTO personaInputDTO) throws NotFoundException,UnprocesableException {
        this.validate(personaInputDTO);
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id_persona: "+id+" not found."));
        persona.setPassword(personaInputDTO.getPassword());
        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setName(personaInputDTO.getName());
        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setName(personaInputDTO.getName());
        persona.setSurname(personaInputDTO.getSurname());
        persona.setCompany_email(personaInputDTO.getCompany_email());
        persona.setPersonal_email(personaInputDTO.getPersonal_email());
        persona.setCity(personaInputDTO.getCity());
        persona.setActive(personaInputDTO.getActive());
        persona.setCreated_date(personaInputDTO.getCreated_date());
        persona.setImagen_url(personaInputDTO.getImagen_url());
        persona.setTermination_date(personaInputDTO.getTermination_date());
        personaRepo.save(persona);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO delPersona(String id) throws NotFoundException {
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id_persona: "+id+" not found."));
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);
        personaRepo.delete(persona);
        return personaOutputDTO;
    }

    @Override
    public Persona getObject(String id) throws NotFoundException {
        return personaRepo.findById(id).orElseThrow(()->new NotFoundException("id_persona: "+id+" not found."));
    }

    private void validate(PersonaInputDTO personaInputDTO) throws UnprocesableException{
        String usuario = personaInputDTO.getUsuario();
        if (usuario==null) throw new UnprocesableException("Error: user is null.");
        if (usuario.length()<6 || usuario.length()>10) throw new UnprocesableException("Error: user length must be between 6 and 10 characters");
        if (personaInputDTO.getPassword()==null) throw new UnprocesableException("Error: password is null.");
        if (personaInputDTO.getName()==null) throw new UnprocesableException("Error: name is null.");
        if (personaInputDTO.getCompany_email()==null) throw new UnprocesableException("Error: Company_email is null.");
        if (personaInputDTO.getPersonal_email()==null) throw new UnprocesableException("Error: Personal_email is null.");
        if (personaInputDTO.getCity()==null) throw new UnprocesableException("Error: City is null.");
        if (personaInputDTO.getActive()==null) throw new UnprocesableException("Error: Active is null");
        if (personaInputDTO.getCreated_date()==null) throw new UnprocesableException("Error: Created_date is null");
    }
}

