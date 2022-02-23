package com.exercise.ej31.profesor.infrastructure;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfesorPersonaListaOutputDTO {

    int total_items;
    List<ProfesorPersonaOutputDTO> items;

    public ProfesorPersonaListaOutputDTO(List<ProfesorPersonaOutputDTO> listaDTO, int total_items){
        this.total_items = total_items;
        this.items = listaDTO;
    }
}
