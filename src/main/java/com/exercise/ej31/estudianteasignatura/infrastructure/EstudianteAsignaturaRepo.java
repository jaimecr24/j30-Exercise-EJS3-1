package com.exercise.ej31.estudianteasignatura.infrastructure;

import com.exercise.ej31.estudianteasignatura.domain.EstudianteAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteAsignaturaRepo extends JpaRepository<EstudianteAsignatura,String> {
}
