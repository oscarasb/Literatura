package com.oasb.literatura.API.Model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class AutorDTO {

        @JsonAlias("name")
        private String nombre;

        @JsonAlias("birth_year")
        private Integer fechaDeNacimiento;

        public AutorDTO() {}

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Integer getFechaDeNacimiento() {
            return fechaDeNacimiento;
        }

        public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
            this.fechaDeNacimiento = fechaDeNacimiento;
        }
    }

