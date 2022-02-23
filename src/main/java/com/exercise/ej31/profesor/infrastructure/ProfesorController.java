package com.exercise.ej31.profesor.infrastructure;

import com.exercise.ej31.estudiante.infrastructure.EstudianteListaOutputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaListaOutputDTO;
import com.exercise.ej31.profesor.application.IProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    IProfesor profesorService;

    @GetMapping
    public ResponseEntity<ProfesorPersonaListaOutputDTO> findAll()
    {
        return new ResponseEntity<>(profesorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfesorPersonaOutputDTO> getById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(profesorService.getById(id), HttpStatus.OK);
    }

    @GetMapping("{id}/estudiantes")
    public ResponseEntity<EstudianteListaOutputDTO> getEstudiantes(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(profesorService.getEstudiantes(id), HttpStatus.OK);
    }

    @GetMapping("{id}/asignaturas")
    public ResponseEntity<EstudianteAsignaturaListaOutputDTO> getAsignaturas(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(profesorService.getAsignaturas(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfesorPersonaOutputDTO> add(@RequestBody ProfesorPersonaInputDTO inputDTO) throws Exception
    {
        return new ResponseEntity<>(profesorService.addProfesor(inputDTO), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfesorPersonaOutputDTO> put(@RequestBody ProfesorPersonaInputDTO inputDTO, @PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(profesorService.putProfesor(id,inputDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProfesorPersonaOutputDTO> delById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(profesorService.delProfesor(id), HttpStatus.OK);
    }
}
