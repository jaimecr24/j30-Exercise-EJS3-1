package com.exercise.ej31.profesor.infrastructure;

import com.exercise.ej31.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepo extends JpaRepository<Profesor,String> {
}
