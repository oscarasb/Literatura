package com.oasb.literatura.API.Repository;

import com.oasb.literatura.API.Model.DatosLibros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<DatosLibros, Long> {
    List<DatosLibros>findByTituloIgnoreCaseContains(String titulo);
}