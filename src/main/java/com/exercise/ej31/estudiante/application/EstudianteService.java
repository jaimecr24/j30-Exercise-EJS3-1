package com.exercise.ej31.estudiante.application;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.estudiante.infrastructure.*;
import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.shared.NotFoundException;
import com.exercise.ej31.shared.UnprocesableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EstudianteService implements IEstudiante {

    private final EstudianteRepo estudianteRepo;

    public EstudianteService(EstudianteRepo estudianteRepo){
        super();
        this.estudianteRepo = estudianteRepo;
    }

    @Override
    public List<EstudiantePersonaOutputDTO> findAll() {
        List<Estudiante> listaEstudiante = estudianteRepo.findAll();
        List<EstudiantePersonaOutputDTO> listaDTO = new ArrayList<>();
        for (Estudiante estudiante:listaEstudiante) listaDTO.add(new EstudiantePersonaOutputDTO(estudiante));
        return listaDTO;
    }

    @Override
    public EstudiantePersonaOutputDTO getFullById(String id) throws NotFoundException {
        Estudiante estudiante = estudianteRepo.findById(id).orElseThrow(()->new NotFoundException("id_estudiante: "+id+" not found."));
        return new EstudiantePersonaOutputDTO(estudiante);
    }

    @Override
    public EstudianteOutputDTO getSimpleById(String id) throws NotFoundException {
        Estudiante estudiante = estudianteRepo.findById(id).orElseThrow(()->new NotFoundException("id_estudiante: "+id+" not found."));
        return new EstudianteOutputDTO(estudiante);
    }

    @Override
    public EstudiantePersonaOutputDTO addEstudiante(EstudiantePersonaInputDTO inputDTO) throws UnprocesableException {
        this.validate(inputDTO);
        Estudiante estudiante = inputDTO.toEstudiante();
        estudiante.getPersona().setCreated_date(new Date()); // created_date to now.
        estudianteRepo.save(estudiante);
        return new EstudiantePersonaOutputDTO(estudiante);
    }

    @Override
    public EstudiantePersonaOutputDTO putEstudiante(String id, EstudiantePersonaInputDTO inputDTO) throws NotFoundException,UnprocesableException {
        this.validate(inputDTO);
        Estudiante estudiante = estudianteRepo.findById(id).orElseThrow(()->new NotFoundException("id_estudiante: "+id+" not found."));
        Persona persona = estudiante.getPersona();
        persona.setPassword(inputDTO.getPassword());
        persona.setUsuario(inputDTO.getUsuario());
        persona.setName(inputDTO.getName());
        persona.setUsuario(inputDTO.getUsuario());
        persona.setName(inputDTO.getName());
        persona.setSurname(inputDTO.getSurname());
        persona.setCompany_email(inputDTO.getCompany_email());
        persona.setPersonal_email(inputDTO.getPersonal_email());
        persona.setCity(inputDTO.getCity());
        persona.setActive(inputDTO.getActive());
        persona.setCreated_date(inputDTO.getCreated_date());
        persona.setImagen_url(inputDTO.getImagen_url());
        persona.setTermination_date(inputDTO.getTermination_date());
        estudiante.setNum_hours_week(inputDTO.getNum_hours_week());
        estudiante.setComments(inputDTO.getComments());
        estudiante.setBranch(inputDTO.getBranch());
        estudianteRepo.save(estudiante);
        return new EstudiantePersonaOutputDTO(estudiante);
    }

    @Override
    public EstudiantePersonaOutputDTO delEstudiante(String id) throws NotFoundException {
        Estudiante estudiante = estudianteRepo.findById(id).orElseThrow(()->new NotFoundException("id_estudiante: "+id+" not found."));
        EstudiantePersonaOutputDTO outputDTO = new EstudiantePersonaOutputDTO(estudiante);
        estudianteRepo.delete(estudiante);
        return outputDTO;
    }

    private void validate(EstudiantePersonaInputDTO inputDTO) throws UnprocesableException {
        String usuario = inputDTO.getUsuario();
        if (usuario==null) throw new UnprocesableException("Error: user is null.");
        if (usuario.length()<6 || usuario.length()>10) throw new UnprocesableException("Error: user length must be between 6 and 10 characters");
        if (inputDTO.getPassword()==null) throw new UnprocesableException("Error: password is null.");
        if (inputDTO.getName()==null) throw new UnprocesableException("Error: name is null.");
        if (inputDTO.getCompany_email()==null) throw new UnprocesableException("Error: Company_email is null.");
        if (inputDTO.getPersonal_email()==null) throw new UnprocesableException("Error: Personal_email is null.");
        if (inputDTO.getCity()==null) throw new UnprocesableException("Error: City is null.");
        if (inputDTO.getActive()==null) throw new UnprocesableException("Error: Active is null");
        if (inputDTO.getCreated_date()==null) throw new UnprocesableException("Error: Created_date is null");
        if (inputDTO.getNum_hours_week()==null) throw new UnprocesableException("Error: num_hours_week is null");
        if (inputDTO.getBranch()==null) throw new UnprocesableException("Error: branch is null");
    }
}
