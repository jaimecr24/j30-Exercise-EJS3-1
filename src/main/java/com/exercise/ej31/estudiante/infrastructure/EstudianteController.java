package com.exercise.ej31.estudiante.infrastructure;

import com.exercise.ej31.estudiante.application.IEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    IEstudiante estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudiantePersonaOutputDTO>> findAll()
    {
        return new ResponseEntity<>(estudianteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EstudiantePersonaOutputDTO> getById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(estudianteService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstudiantePersonaOutputDTO> add(@RequestBody EstudiantePersonaInputDTO inputDTO) throws Exception
    {
        return new ResponseEntity<>(estudianteService.addEstudiante(inputDTO), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EstudiantePersonaOutputDTO> put(@RequestBody EstudiantePersonaInputDTO inputDTO, @PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(estudianteService.putEstudiante(id,inputDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EstudiantePersonaOutputDTO> delById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(estudianteService.delEstudiante(id), HttpStatus.OK);
    }
}
