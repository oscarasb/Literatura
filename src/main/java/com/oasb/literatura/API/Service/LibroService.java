package com.oasb.literatura.API.Service;

import com.oasb.literatura.API.Model.AutorDTO;
import com.oasb.literatura.API.Model.DatosAutor;
import com.oasb.literatura.API.Model.DatosLibros;
import com.oasb.literatura.API.Model.Datos;
import com.oasb.literatura.API.Repository.AutorRepository;
import com.oasb.literatura.API.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private static final String URL_API = "https://gutendex.com/books/";

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ConsumoApi consumoApi;

    @Autowired
    private ConvierteDatos convierteDatos;
    @Autowired
    private AutorRepository autorRepository;


    // 🔍 Buscar por título, desde BD o API
    public DatosLibros buscarLibroPorTitulo(String titulo) {

        List<DatosLibros> libroEnBD = libroRepository.findByTituloIgnoreCaseContains(titulo);
        {
            if (!libroEnBD.isEmpty()) {
                System.out.println("LIBRO ENCONTRADO EN LA BASE DE DATOS.");
                return libroEnBD.get(0);
            }

            System.out.println("LIBRO NO ENCONTRADO ");
            System.out.println("\n ... CONSULTANDO API... ");
            String json = consumoApi.obtenerDatos(URL_API + "?search=" + titulo.replace(" ", ""));
            Datos datos = convierteDatos.obtenerDatos(json, Datos.class);

            List<DatosLibros> libros = datos.resultado();
            if (!libros.isEmpty()) {
                DatosLibros libroEncontrado = libros.get(0);

                //  AutorDTO → DatosAutor
                List<AutorDTO> autoresDTO = libroEncontrado.getAuthors(); // este campo debe existir en DatosLibros
                List<DatosAutor> autoresConvertidos = autoresDTO.stream()
                        .map(dto -> {
                            DatosAutor autor = new DatosAutor();
                            autor.setNombre(dto.getNombre());
                            autor.setFechaDeNacimiento(dto.getFechaDeNacimiento());
                            return autor;
                        })
                        .toList();

                autoresConvertidos.forEach(autorRepository::save);
                libroEncontrado.setAutores(autoresConvertidos);
                libroRepository.save(libroEncontrado);

                System.out.println("LIBRO GUARDADO EN LA BASE DE DATOS 💾💾💾");
                return libroEncontrado;
            }

            System.out.println("....LIBRO NO ENCONTRADO EN LA API....");
            return null;
        }

    }

    // 📋 Listar todos los libros
    public List<DatosLibros> listarLibros() {
        return libroRepository.findAll();
    }
}