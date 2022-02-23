package com.exercise.ej31.estudianteasignatura.infrastructure;

import com.exercise.ej31.estudianteasignatura.application.IEstudianteAsignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asignatura")
public class EstudianteAsignaturaController {

    @Autowired
    IEstudianteAsignatura estudianteAsignaturaService;

    @GetMapping
    public ResponseEntity<EstudianteAsignaturaListaOutputDTO> findAll()
    {
        return new ResponseEntity<>(estudianteAsignaturaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EstudianteAsignaturaOutputDTO> findById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(estudianteAsignaturaService.findById(id), HttpStatus.OK);
    }

    @GetMapping("{id}/estudiante")
    public ResponseEntity<EstudianteAsignaturaListaOutputDTO> findByIdEstudiante(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(estudianteAsignaturaService.findByEstudiante(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstudianteAsignaturaOutputDTO> add(@RequestBody EstudianteAsignaturaInputDTO inputDTO) throws Exception
    {
        return new ResponseEntity<>(estudianteAsignaturaService.addAsignatura(inputDTO), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EstudianteAsignaturaOutputDTO> put(@PathVariable String id, @RequestBody EstudianteAsignaturaInputDTO inputDTO) throws Exception
    {
        return new ResponseEntity<>(estudianteAsignaturaService.putAsignatura(id,inputDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EstudianteAsignaturaOutputDTO> delete(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(estudianteAsignaturaService.delAsignatura(id), HttpStatus.OK);
    }
}
