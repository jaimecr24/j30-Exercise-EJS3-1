package com.exercise.ej31.persona.infrastructure;

import com.exercise.ej31.persona.application.IPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    IPersona personaService;

    @GetMapping
    public ResponseEntity<PersonaListaOutputDTO> findAll(
            @RequestParam(name="outputType", required = false, defaultValue = "simple") String outputType)
    {
        return new ResponseEntity<>(personaService.findAll(outputType), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonaOutputDTO> getById(
            @PathVariable String id,
            @RequestParam(name="outputType", required = false, defaultValue = "simple") String outputType) throws Exception
    {
        return new ResponseEntity<>(personaService.getExtendedById(id, outputType), HttpStatus.OK);
    }

    @GetMapping("/{usuario}/usuario")
    public ResponseEntity<PersonaListaOutputDTO> getByUser(
            @PathVariable String usuario,
            @RequestParam(name="outputType", required = false, defaultValue = "simple") String outputType)
    {
        return new ResponseEntity<>(personaService.getByUser(usuario,outputType), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonaOutputDTO> add(@RequestBody PersonaInputDTO personaInputDTO) throws Exception
    {
        return new ResponseEntity<>(personaService.addPersona(personaInputDTO), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonaOutputDTO> put(@RequestBody PersonaInputDTO personaInputDTO, @PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(personaService.putPersona(id, personaInputDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PersonaOutputDTO> delById(@PathVariable String id) throws Exception
    {
        return new ResponseEntity<>(personaService.delPersona(id), HttpStatus.OK);
    }
}
