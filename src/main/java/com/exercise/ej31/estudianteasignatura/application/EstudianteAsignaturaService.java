package com.exercise.ej31.estudianteasignatura.application;

import com.exercise.ej31.estudiante.domain.Estudiante;
import com.exercise.ej31.estudiante.infrastructure.EstudianteRepo;
import com.exercise.ej31.estudianteasignatura.domain.EstudianteAsignatura;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaInputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaOutputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaRepo;
import com.exercise.ej31.profesor.domain.Profesor;
import com.exercise.ej31.profesor.infrastructure.ProfesorRepo;
import com.exercise.ej31.shared.NotFoundException;
import com.exercise.ej31.shared.UnprocesableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteAsignaturaService implements IEstudianteAsignatura{

    private final EstudianteAsignaturaRepo estudianteAsignaturaRepo;
    private final EstudianteRepo estudianteRepo;
    private final ProfesorRepo profesorRepo;

    public EstudianteAsignaturaService(
            EstudianteAsignaturaRepo estudianteAsignaturaRepo,
            EstudianteRepo estudianteRepo, ProfesorRepo profesorRepo){
        super();
        this.estudianteAsignaturaRepo = estudianteAsignaturaRepo;
        this.estudianteRepo = estudianteRepo;
        this.profesorRepo = profesorRepo;
    }

    @Override
    public List<EstudianteAsignaturaOutputDTO> findAll() {
        List<EstudianteAsignatura> asignaturas = estudianteAsignaturaRepo.findAll();
        List<EstudianteAsignaturaOutputDTO> listaDTO = new ArrayList<>();
        for (EstudianteAsignatura asignatura:asignaturas) listaDTO.add(new EstudianteAsignaturaOutputDTO(asignatura));
        return listaDTO;
    }

    @Override
    public List<EstudianteAsignaturaOutputDTO> findByEstudiante(String id) throws NotFoundException {
        Estudiante estudiante = estudianteRepo.findById(id).orElseThrow(()->new NotFoundException("id_estudiante: "+id+" not found."));
        List<EstudianteAsignatura> asignaturas = estudiante.getEstudianteAsignaturaList();
        List<EstudianteAsignaturaOutputDTO> listaDTO = new ArrayList<>();
        for (EstudianteAsignatura asignatura:asignaturas) listaDTO.add(new EstudianteAsignaturaOutputDTO(asignatura));
        return listaDTO;
    }

    @Override
    public EstudianteAsignaturaOutputDTO findById(String id) throws NotFoundException {
        EstudianteAsignatura estudianteAsignatura = estudianteAsignaturaRepo.findById(id)
                .orElseThrow(()->new NotFoundException("id_asignatura: "+id+" not found."));
        return new EstudianteAsignaturaOutputDTO(estudianteAsignatura);
    }

    @Override
    public EstudianteAsignaturaOutputDTO addAsignatura(EstudianteAsignaturaInputDTO inputDTO) throws NotFoundException,UnprocesableException {
        this.validate(inputDTO,true);
        // Get estudiante from database.
        Estudiante estudiante = estudianteRepo.findById(inputDTO.getId_estudiante())
                .orElseThrow(()->new NotFoundException("id_estudiante: "+inputDTO.getId_estudiante()+" not found"));
        // Get profesor from database.
        Profesor profesor = profesorRepo.findById(inputDTO.getId_profesor())
                .orElseThrow(()->new NotFoundException("id_profesor: "+inputDTO.getId_profesor()+" not found"));
        // Construct new estudianteAsignatura && save
        EstudianteAsignatura estudianteAsignatura = inputDTO.toEstudianteAsignatura(estudiante,profesor);
        estudianteAsignaturaRepo.save(estudianteAsignatura);
        return new EstudianteAsignaturaOutputDTO(estudianteAsignatura);
    }

    @Override
    public EstudianteAsignaturaOutputDTO putAsignatura(String id, EstudianteAsignaturaInputDTO inputDTO) throws NotFoundException,UnprocesableException {
        this.validate(inputDTO,false);
        EstudianteAsignatura estudianteAsignatura = estudianteAsignaturaRepo.findById(id)
                .orElseThrow(()->new NotFoundException("id_asignatura: "+id+" not found."));
        estudianteAsignatura.setAsignatura(inputDTO.getAsignatura());
        estudianteAsignatura.setComments(inputDTO.getComments());
        estudianteAsignatura.setInitial_date(inputDTO.getInitial_date());
        estudianteAsignatura.setFinish_date(inputDTO.getFinish_date());
        estudianteAsignaturaRepo.save(estudianteAsignatura);
        return new EstudianteAsignaturaOutputDTO(estudianteAsignatura);
    }

    @Override
    public EstudianteAsignaturaOutputDTO delAsignatura(String id) throws NotFoundException {
        EstudianteAsignatura estudianteAsignatura = estudianteAsignaturaRepo.findById(id)
                .orElseThrow(()->new NotFoundException("id_asignatura: "+id+" not found."));
        EstudianteAsignaturaOutputDTO outputDTO = new EstudianteAsignaturaOutputDTO(estudianteAsignatura);
        estudianteAsignaturaRepo.delete(estudianteAsignatura);
        return outputDTO;
    }

    private void validate(EstudianteAsignaturaInputDTO inputDTO, boolean isAdd) throws UnprocesableException {
        if (isAdd && inputDTO.getId_estudiante()==null) throw new UnprocesableException("Error: id_estudiante is null");
        if (inputDTO.getAsignatura()==null) throw new UnprocesableException("Error: asignatura is null");
        if (inputDTO.getInitial_date()==null) throw new UnprocesableException("Error: initial date is null");
    }
}
