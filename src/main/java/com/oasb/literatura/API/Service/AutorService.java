package com.oasb.literatura.API.Service;

import com.oasb.literatura.API.Model.DatosAutor;
import com.oasb.literatura.API.Repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public List<DatosAutor> listarAutores() {
        List<DatosAutor> autores = autorRepository.findAll();
        autores.forEach(autor -> System.out.println( autor.getNombre() + " - Libros: " + autor.getLibros().size()));
        return autores;
    }

    // 🔍 Buscar autores vivos en cierto año
    public List<DatosAutor> autoresVivosHasta(int anioLimite) {
        return autorRepository.findByFechaDeNacimientoLessThanEqual(anioLimite);
    }
}