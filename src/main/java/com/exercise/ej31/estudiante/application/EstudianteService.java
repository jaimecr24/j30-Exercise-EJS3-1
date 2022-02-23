package com.exercise.ej31.estudiante.application;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.estudiante.infrastructure.*;
import com.exercise.ej31.estudianteasignatura.domain.EstudianteAsignatura;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaInputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaListaOutputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaOutputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaRepo;
import com.exercise.ej31.persona.domain.Persona;
import com.exercise.ej31.profesor.domain.Profesor;
import com.exercise.ej31.profesor.infrastructure.ProfesorRepo;
import com.exercise.ej31.shared.NotFoundException;
import com.exercise.ej31.shared.UnprocesableException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService implements IEstudiante {

    private final EstudianteRepo estudianteRepo;
    private final ProfesorRepo profesorRepo;
    private final EstudianteAsignaturaRepo estudianteAsignaturaRepo;

    public EstudianteService(
            EstudianteRepo estudianteRepo,
            ProfesorRepo profesorRepo,
            EstudianteAsignaturaRepo estudianteAsignaturaRepo){
        super();
        this.estudianteRepo = estudianteRepo;
        this.profesorRepo = profesorRepo;
        this.estudianteAsignaturaRepo = estudianteAsignaturaRepo;
    }

    @Override
    public EstudiantePersonaListaOutputDTO findAll() {
        List<Estudiante> listaEstudiante = estudianteRepo.findAll();
        List<EstudiantePersonaOutputDTO> listaDTO = listaEstudiante.stream()
                .map(EstudiantePersonaOutputDTO::new).collect(Collectors.toList());
        //for (Estudiante estudiante:listaEstudiante) listaDTO.add(new EstudiantePersonaOutputDTO(estudiante));
        return new EstudiantePersonaListaOutputDTO(listaDTO,listaDTO.size());
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
        Profesor profesor;
        if (inputDTO.getId_profesor()!=null)
            profesor = profesorRepo.findById(inputDTO.getId_profesor())
                    .orElseThrow(()->new NotFoundException("id_profesor: "+inputDTO.getId_profesor()+" not found."));
        else
            profesor = null; // No profesor assigned
        Estudiante estudiante = inputDTO.toEstudiante(profesor);
        estudiante.getPersona().setCreated_date(new Date()); // created_date to now.
        estudianteRepo.save(estudiante);
        return new EstudiantePersonaOutputDTO(estudiante);
    }

    @Transactional  // Si hay una excepción dentro del método se deshace la transacción
    @Override
    public EstudianteAsignaturaListaOutputDTO addAsignaturas(String id_estudiante, List<EstudianteAsignaturaInputDTO> listaDTO) throws NotFoundException {
        // Añade una lista de asignaturas a un estudiante
        Estudiante estudiante = estudianteRepo.findById(id_estudiante)
                .orElseThrow(()->new NotFoundException("id_estudiante: "+id_estudiante+" not found."));
        List<EstudianteAsignaturaOutputDTO> listaOuputDTO = new ArrayList<>();
        for (EstudianteAsignaturaInputDTO inputDTO:listaDTO) {
            validateAsignatura(inputDTO); // Check the necessary fields hasn't null value
            Profesor profesor = profesorRepo.findById(inputDTO.getId_profesor())
                    .orElseThrow(()->new NotFoundException("asignatura "+inputDTO.getAsignatura()+" has no valid profesor assigned"));
            // We create a new EstudianteAsignatura object with his profesor and estudiante
            EstudianteAsignatura asignatura = inputDTO.toEstudianteAsignatura(estudiante,profesor);
            estudianteAsignaturaRepo.save(asignatura);
            listaOuputDTO.add(new EstudianteAsignaturaOutputDTO(asignatura));
        }
        return new EstudianteAsignaturaListaOutputDTO(listaOuputDTO,listaOuputDTO.size());
    }

    @Transactional
    @Override
    public EstudianteAsignaturaListaOutputDTO delAsignaturas(String id_estudiante, List<String> id_asignaturas)
            throws NotFoundException,UnprocesableException
    {
        List<EstudianteAsignaturaOutputDTO> listaOutputDTO = new ArrayList<>();
        for (String id:id_asignaturas) {
            EstudianteAsignatura asignatura = estudianteAsignaturaRepo.findById(id)
                    .orElseThrow(()->new NotFoundException("id_asignatura: "+id+" not found."));
            // Comprobamos que la asignatura pertenece al estudiante.
            if (!id_estudiante.equals(asignatura.getEstudiante().getId_student()))
                throw new UnprocesableException("Estudiante "+id_estudiante+" not has asignatura "+id);
            // Añadimos DTO a la lista y borramos la asignatura.
            listaOutputDTO.add(new EstudianteAsignaturaOutputDTO(asignatura));
            estudianteAsignaturaRepo.delete(asignatura);
        }
        return new EstudianteAsignaturaListaOutputDTO(listaOutputDTO,listaOutputDTO.size());
    }


    @Override
    public EstudiantePersonaOutputDTO patchProfesor(String id_estudiante, String id_profesor) throws NotFoundException {
        Estudiante estudiante = estudianteRepo.findById(id_estudiante)
                .orElseThrow(()->new NotFoundException("id_estudiante: "+id_estudiante+" not found."));
        Profesor profesor = profesorRepo.findById(id_profesor)
                .orElseThrow(()->new NotFoundException("id_profesor: "+id_profesor+" not found."));
        estudiante.setProfesor(profesor);
        estudianteRepo.save(estudiante);
        return new EstudiantePersonaOutputDTO(estudiante);
    }

    @Override
    public EstudiantePersonaOutputDTO putEstudiante(String id, EstudiantePersonaInputDTO inputDTO) throws NotFoundException,UnprocesableException {
        // Modifies data except profesor asigned.
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
        //persona.setCreated_date(inputDTO.getCreated_date());
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
        //if (inputDTO.getCreated_date()==null) throw new UnprocesableException("Error: Created_date is null");
        if (inputDTO.getNum_hours_week()==null) throw new UnprocesableException("Error: num_hours_week is null");
        if (inputDTO.getBranch()==null) throw new UnprocesableException("Error: branch is null");
    }

    private void validateAsignatura(EstudianteAsignaturaInputDTO inputDTO) throws UnprocesableException {
        if (inputDTO.getAsignatura()==null) throw new UnprocesableException("Error: asignatura is null");
        if (inputDTO.getInitial_date()==null) throw new UnprocesableException("Error: initial_date of "+inputDTO.getAsignatura()+" is null");
        if (inputDTO.getId_profesor()==null) throw new UnprocesableException("Error: id_profesor of "+inputDTO.getAsignatura()+" is null");
    }
}
