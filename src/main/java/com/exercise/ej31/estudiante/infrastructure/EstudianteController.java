package com.exercise.ej31.estudiante.infrastructure;

import com.exercise.ej31.estudiante.application.IEstudiante;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaInputDTO;
import com.exercise.ej31.estudianteasignatura.infrastructure.EstudianteAsignaturaOutputDTO;
import com.exercise.ej31.shared.UnprocesableException;
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
    public ResponseEntity<Object> getById(@PathVariable String id, @RequestParam(name = "outputType") String outputType) throws Exception
    {
        if (outputType.equals("simple")) {
            return new ResponseEntity<>(estudianteService.getSimpleById(id), HttpStatus.OK);
        }
        else if (outputType.equals("full"))
        {
            return new ResponseEntity<>(estudianteService.getFullById(id), HttpStatus.OK);
        }
        else throw new UnprocesableException("Error: wrong value in outputType");
    }

    @PostMapping
    public ResponseEntity<EstudiantePersonaOutputDTO> add(@RequestBody EstudiantePersonaInputDTO inputDTO) throws Exception
    {
        return new ResponseEntity<>(estudianteService.addEstudiante(inputDTO), HttpStatus.OK);
    }

    @PostMapping("{id}/asignaturas")
    public ResponseEntity<List<EstudianteAsignaturaOutputDTO>> addAsignaturas(
            @PathVariable String id,
            @RequestBody List<EstudianteAsignaturaInputDTO> listDTO) throws Exception
    {
        return new ResponseEntity<>(estudianteService.addAsignaturas(id,listDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}/asignaturas")
    public ResponseEntity<List<EstudianteAsignaturaOutputDTO>> delAsignaturas(
            @PathVariable String id,
            @RequestBody List<String> id_asignaturas) throws Exception
    {
        //No es necesario el id del estudiante porque ya tenemos los id de las asignaturas.
        //Tan sólo se necesita para comprobar que pertenecen a ese estudiante
        //Si no es así se lanza excepción
        return new ResponseEntity<>(estudianteService.delAsignaturas(id, id_asignaturas), HttpStatus.OK);
    }


    @PatchMapping("{id_estudiante}/profesor/{id_profesor}")
    public ResponseEntity<EstudiantePersonaOutputDTO> patch(@PathVariable String id_estudiante, @PathVariable String id_profesor) throws Exception
    {
        // Changes the professor assigned to the student
        return new ResponseEntity<>(estudianteService.patchProfesor(id_estudiante,id_profesor), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EstudiantePersonaOutputDTO> put(@PathVariable String id, @RequestBody EstudiantePersonaInputDTO inputDTO) throws Exception
    {
        // Modifies data except professor assigned.
        return new ResponseEntity<>(estudianteService.putEstudiante(id,inputDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EstudiantePersonaOutputDTO> delById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(estudianteService.delEstudiante(id), HttpStatus.OK);
    }
}
