package com.example.rabbitmq.domain.model;

public class Mensaje {

    private Long id;
    private String contenido;

    public Mensaje(String contenido) {
        this.contenido = contenido;
    }

    public Mensaje(Long id, String contenido) {
        this.id = id;
        this.contenido = contenido;
    }

    public Long getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }
}
