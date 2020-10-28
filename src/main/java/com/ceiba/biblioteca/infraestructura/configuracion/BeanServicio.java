package com.ceiba.biblioteca.infraestructura.configuracion;

import com.ceiba.biblioteca.dominio.repositorio.RepositorioLibro;
import com.ceiba.biblioteca.dominio.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.dominio.servicio.bibliotecario.ServicioBibliotecario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioBibliotecario servicioCrearUsuario2(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
        return new ServicioBibliotecario(repositorioLibro, repositorioPrestamo);
    }
}
