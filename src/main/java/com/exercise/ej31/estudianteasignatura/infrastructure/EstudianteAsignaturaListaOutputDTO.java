package com.exercise.ej31.estudianteasignatura.infrastructure;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstudianteAsignaturaListaOutputDTO {

    int total_items;
    List<EstudianteAsignaturaOutputDTO> items;

    public EstudianteAsignaturaListaOutputDTO(List<EstudianteAsignaturaOutputDTO> listaDTO, int total_items){
        this.total_items = total_items;
        this.items = listaDTO;
    }
}
