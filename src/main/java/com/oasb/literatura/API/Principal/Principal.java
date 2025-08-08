package com.oasb.literatura.API.Principal;

import com.oasb.literatura.API.Model.DatosLibros;
import com.oasb.literatura.API.Model.DatosAutor;
import com.oasb.literatura.API.Service.LibroService;
import com.oasb.literatura.API.Service.AutorService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final LibroService libroService;
    private final AutorService autorService;

    public Principal(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void muestraElMenu() {
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("""
                 📚📚📚📚📚📚📖📖 MENÚ PRINCIPAL 📖📖 📚📚📚📚📚📚📚📚📚
                            SELECCIONE UNA OPCION PARA SEGUIR
                
                    1. BUSCARDOR DE LIBROS POR TÍTULO
                    2. LISTAR TODOS LOS LIBROS REGISTRADOS
                    3. LISTAR TODOS LOS AUTORES REGISTRADOS
                    4. LISTAR AUTORES QUE AUN VIVEN DE UN AÑO ESPECÍFICO
                    9. SALIR DE LA APLICACION
                 """  );

            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(" ❌ ❌  ENTRADA INVÁLIDA. USA NÚMEROS PARA LAS OPCIONES. ❌ ❌ ");
                teclado.nextLine();
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print(" INGRESE EL TÍTULO DEL LIBRO A BUSCAR: ");
                    String titulo = teclado.nextLine();
                    DatosLibros libro = libroService.buscarLibroPorTitulo(titulo);

                    if (libro != null) {
                        System.out.println("RESULTADO: ");
                        System.out.println("TITULO: " + libro.getTitulo());
                        System.out.println("LENGUAJE: " + libro.getLenguajes());
                        System.out.println("DESCARGAS: " + libro.getNumeroDeDescargas());
                    } else {
                        System.out.println(" LIBRO NO ENCONTRADO ");
                    }
                }

                case 2 -> {
                    List<DatosLibros> libros = libroService.listarLibros();
                    System.out.println(" LIBROS REGISTRADOS: ");
                    libros.forEach(libro -> System.out.println("- " + libro.getTitulo()));
                }

                case 3 -> {
                    List<DatosAutor> autores = autorService.listarAutores();
                    System.out.println(" AUTORES REGISTRADOS: ");
                    autores.forEach(autor -> System.out.println("- " + autor.getNombre()));
                }

                case 4 -> {
                    System.out.print(" INGRESE EL AÑO CON UN LIMITE: ");
                    int anio = teclado.nextInt();
                    teclado.nextLine(); // limpia buffer

                    List<DatosAutor> vivos = autorService.autoresVivosHasta(anio);
                    System.out.println(" AUTORES NACIDOS HASTA " + anio + ":");
                    vivos.forEach(autor -> System.out.println("- " + autor.getNombre() + " (" + autor.getFechaDeNacimiento() + ")"));
                }

                case 9 -> System.out.println(" ¡GRACIAS POR USAR NUESTROS SERVICIOS DE APLICACIÓN!,  ");
                default -> System.out.println(" OPCIÓN NO VALIDA, INTENTA DE NUEVO. ");
            }

            System.out.println(); // INTERVALO ENTRE  CADA ciclo O CICLOS
        }
    }

}