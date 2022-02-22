package com.exercise.ej31.profesor.application;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.estudiante.infrastructure.EstudianteOutputDTO;
import com.exercise.ej31.estudianteasignatura.domain.EstudianteAsignatura;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaOutputDTO;
import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.profesor.domain.Profesor;
import com.exercise.ej31.profesor.infrastructure.ProfesorPersonaInputDTO;
import com.exercise.ej31.profesor.infrastructure.ProfesorPersonaOutputDTO;
import com.exercise.ej31.profesor.infrastructure.ProfesorRepo;
import com.exercise.ej31.shared.NotFoundException;
import com.exercise.ej31.shared.UnprocesableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProfesorService implements IProfesor {

    private final ProfesorRepo profesorRepo;

    public ProfesorService(ProfesorRepo profesorRepo){
        super();
        this.profesorRepo = profesorRepo;
    }

    @Override
    public List<ProfesorPersonaOutputDTO> findAll() {
        List<Profesor> listaProfesor = profesorRepo.findAll();
        List<ProfesorPersonaOutputDTO> listaDTO = new ArrayList<>();
        for (Profesor profesor:listaProfesor) listaDTO.add(new ProfesorPersonaOutputDTO(profesor));
        return listaDTO;
    }

    @Override
    public ProfesorPersonaOutputDTO getById(String id) throws NotFoundException {
        Profesor profesor = profesorRepo.findById(id).orElseThrow(()->new NotFoundException("id_profesor: "+id+" not found."));
        return new ProfesorPersonaOutputDTO(profesor);
    }

    @Override
    public List<EstudianteOutputDTO> getEstudiantes(String id) throws NotFoundException {
        Profesor profesor = profesorRepo.findById(id).orElseThrow(()->new NotFoundException("id_profesor: "+id+" not found."));
        List<EstudianteOutputDTO> listaOutputDTO = new ArrayList<>();
        List<Estudiante> listaEstudiantes = profesor.getEstudiantes();
        for (Estudiante e:listaEstudiantes) listaOutputDTO.add(new EstudianteOutputDTO(e));
        return listaOutputDTO;
    }

    @Override
    public List<EstudianteAsignaturaOutputDTO> getAsignaturas(String id) throws NotFoundException {
        Profesor profesor = profesorRepo.findById(id).orElseThrow(()->new NotFoundException("id_profesor: "+id+" not found."));
        List<EstudianteAsignaturaOutputDTO> listaOutputDTO = new ArrayList<>();
        List<EstudianteAsignatura> listaAsignaturas = profesor.getAsignaturas();
        for (EstudianteAsignatura e:listaAsignaturas) listaOutputDTO.add(new EstudianteAsignaturaOutputDTO(e));
        return listaOutputDTO;
    }

    @Override
    public ProfesorPersonaOutputDTO addProfesor(ProfesorPersonaInputDTO inputDTO) throws UnprocesableException {
        this.validate(inputDTO);
        Profesor profesor = inputDTO.toProfesor();
        profesor.getPersona().setCreated_date(new Date()); // Created_date to now.
        profesorRepo.save(profesor);
        return new ProfesorPersonaOutputDTO(profesor);
    }

    @Override
    public ProfesorPersonaOutputDTO putProfesor(String id, ProfesorPersonaInputDTO inputDTO) throws NotFoundException,UnprocesableException {
        this.validate(inputDTO);
        Profesor profesor = profesorRepo.findById(id).orElseThrow(()->new NotFoundException("id_profesor: "+id+" not found."));
        Persona persona = profesor.getPersona();
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
        //persona.setCreated_date(inputDTO.getCreated_date());
        persona.setImagen_url(inputDTO.getImagen_url());
        persona.setTermination_date(inputDTO.getTermination_date());
        profesor.setComments(inputDTO.getComments());
        profesor.setBranch(inputDTO.getBranch());
        profesorRepo.save(profesor);
        return new ProfesorPersonaOutputDTO(profesor);
    }

    @Override
    public ProfesorPersonaOutputDTO delProfesor(String id) throws NotFoundException, UnprocesableException {
        Profesor profesor = profesorRepo.findById(id).orElseThrow(()->new NotFoundException("id_profesor: "+id+" not found."));
        if (!profesor.getEstudiantes().isEmpty()) {
            throw new UnprocesableException("Error: Profesor "+id+" has students assigned");
        }
        if (!profesor.getAsignaturas().isEmpty()) {
            throw new UnprocesableException("Error: Profesor "+id+" has asignaturas assigned");
        }
        ProfesorPersonaOutputDTO outputDTO = new ProfesorPersonaOutputDTO(profesor);
        profesorRepo.delete(profesor);
        return outputDTO;
    }

    private void validate(ProfesorPersonaInputDTO inputDTO) throws UnprocesableException {
        String usuario = inputDTO.getUsuario();
        if (usuario==null) throw new UnprocesableException("Error: user is null.");
        if (usuario.length()<6 || usuario.length()>10) throw new UnprocesableException("Error: user length must be between 6 and 10 characters");
        if (inputDTO.getPassword()==null) throw new UnprocesableException("Error: password is null.");
        if (inputDTO.getName()==null) throw new UnprocesableException("Error: name is null.");
        if (inputDTO.getCompany_email()==null) throw new UnprocesableException("Error: Company_email is null.");
        if (inputDTO.getPersonal_email()==null) throw new UnprocesableException("Error: Personal_email is null.");
        if (inputDTO.getCity()==null) throw new UnprocesableException("Error: City is null.");
        if (inputDTO.getActive()==null) throw new UnprocesableException("Error: Active is null");
        //if (inputDTO.getCreated_date()==null) throw new UnprocesableException("Error: Created_date is null");
        if (inputDTO.getBranch()==null) throw new UnprocesableException("Error: branch is null");
    }
}
