package com.exercise.ej31.estudiante.infrastructure;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstudianteListaOutputDTO {
    int total_items;
    List<EstudianteOutputDTO> items;

    public EstudianteListaOutputDTO(List<EstudianteOutputDTO> listaDTO, int total_items){
        this.total_items = total_items;
        this.items = listaDTO;
    }
}
