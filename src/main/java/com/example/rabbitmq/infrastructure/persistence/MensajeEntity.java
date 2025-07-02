package com.example.rabbitmq.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "mensajes")
public class MensajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    public MensajeEntity() {
    }

    public MensajeEntity(String contenido) {
        this.contenido = contenido;
    }

    public MensajeEntity(Long id, String contenido) {
        this.id = id;
        this.contenido = contenido;
    }

    public Long getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
