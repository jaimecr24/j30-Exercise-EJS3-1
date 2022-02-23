package com.exercise.ej31.estudiante.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstudiantePersonaListaOutputDTO {

    int total_items;
    List<EstudiantePersonaOutputDTO> items;

    public EstudiantePersonaListaOutputDTO(List<EstudiantePersonaOutputDTO> listaDTO, int total_items) {
        this.total_items = total_items;
        this.items = listaDTO;
    }
}
