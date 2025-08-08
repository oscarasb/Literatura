package com.oasb.literatura.API.Repository;

import com.oasb.literatura.API.Model.DatosAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<DatosAutor, Long> {
    List<DatosAutor> findByFechaDeNacimientoLessThanEqual(Integer anio);

}
