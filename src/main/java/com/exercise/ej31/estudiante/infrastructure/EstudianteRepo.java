package com.exercise.ej31.estudiante.infrastructure;

import com.exercise.ej31.estudiante.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepo extends JpaRepository<Estudiante,String> {
}
