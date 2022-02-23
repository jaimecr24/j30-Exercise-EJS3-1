package com.exercise.ej31.persona.application;

import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.persona.infrastructure.PersonaInputDTO;
import com.exercise.ej31.persona.infrastructure.PersonaListaOutputDTO;
import com.exercise.ej31.persona.infrastructure.PersonaOutputDTO;
import com.exercise.ej31.persona.infrastructure.PersonaRepo;
import com.exercise.ej31.estudiante.infrastructure.EstudianteExtendedOutputDTO;
import com.exercise.ej31.estudiante.infrastructure.EstudiantePersonaOutputDTO;
import com.exercise.ej31.profesor.infrastructure.ProfesorExtendedOutputDTO;
import com.exercise.ej31.profesor.infrastructure.ProfesorPersonaOutputDTO;
import com.exercise.ej31.shared.NotFoundException;
import com.exercise.ej31.shared.UnprocesableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService implements IPersona {

    private final PersonaRepo personaRepo;

    public PersonaService(PersonaRepo personaRepo){
        super();
        this.personaRepo = personaRepo;
    }

    @Override
    public PersonaListaOutputDTO findAll(String outputType) {
        List<Persona> listaPersona = personaRepo.findAll();
        return convertToListaOutputDTO(listaPersona,outputType);
    }

    @Override
    public PersonaListaOutputDTO getByUser(String usuario, String outputType) {
        List<Persona> listaPersona = personaRepo.findByUsuario(usuario);
        return convertToListaOutputDTO(listaPersona,outputType);
    }

    private PersonaListaOutputDTO convertToListaOutputDTO(List<Persona> lista, String outputType){
        List<PersonaOutputDTO> listaDTO = lista.stream()
                .map(e -> (outputType!=null && outputType.equals("full"))
                        ? (e.getProfesor()!=null)
                        ? new ProfesorPersonaOutputDTO(e.getProfesor()) // if (outputType=="full" and persona.getProfesor!=null)
                        : new EstudiantePersonaOutputDTO(e.getEstudiante()) // if (outputType=="full" and persona.getProfesor==null)
                        : new PersonaOutputDTO(e)) // if (outputType==null || outputType!="full")
                .collect(Collectors.toList());
        return new PersonaListaOutputDTO(listaDTO,listaDTO.size());
    }

    @Override
    public PersonaOutputDTO getById(String id, String outputType) throws NotFoundException {
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id_persona: "+id+" not found."));
        if (outputType!=null && outputType.equals("full"))
            if (persona.getProfesor()!=null) return new ProfesorPersonaOutputDTO(persona.getProfesor());
            else return new EstudiantePersonaOutputDTO(persona.getEstudiante());
        else return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO getExtendedById(String id, String outputType) throws NotFoundException {
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id_persona: "+id+" not found."));
        if (outputType!=null && outputType.equals("full"))
            if (persona.getProfesor()!=null) return new ProfesorExtendedOutputDTO(persona.getProfesor());
            else return new EstudianteExtendedOutputDTO(persona.getEstudiante());
        else return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws UnprocesableException {
        this.validate(personaInputDTO);
        Persona persona = personaInputDTO.toPersona();
        persona.setCreated_date(new Date()); // Created date to now.
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
        //persona.setCreated_date(personaInputDTO.getCreated_date());
        persona.setImagen_url(personaInputDTO.getImagen_url());
        persona.setTermination_date(personaInputDTO.getTermination_date());
        personaRepo.save(persona);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO delPersona(String id) throws NotFoundException, UnprocesableException {
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id_persona: "+id+" not found."));
        // Check for relations
        if (persona.getProfesor()!=null) throw new UnprocesableException("Error: Persona "+id+" has an associated Profesor");
        if (persona.getEstudiante()!=null) throw new UnprocesableException("Error: Persona "+id+" has an associated Estudiante");
        // Construct DTO && delete
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);
        personaRepo.delete(persona);
        return personaOutputDTO;
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
        //if (personaInputDTO.getCreated_date()==null) throw new UnprocesableException("Error: Created_date is null");
    }
}

