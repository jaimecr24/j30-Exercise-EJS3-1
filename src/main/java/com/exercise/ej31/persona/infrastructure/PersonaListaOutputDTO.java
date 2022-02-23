package com.exercise.ej31.persona.infrastructure;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonaListaOutputDTO {

    int total_items;
    List<PersonaOutputDTO> items;

    public PersonaListaOutputDTO(List<PersonaOutputDTO> listaDTO, int total_items){
        this.total_items = total_items;
        this.items = listaDTO;
    }
}
