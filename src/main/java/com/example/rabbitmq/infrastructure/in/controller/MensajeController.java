package com.example.rabbitmq.infrastructure.in.controller;

import com.example.rabbitmq.domain.model.Mensaje;
import com.example.rabbitmq.domain.port.in.ServicioMensajeriaInputPort;
import com.example.rabbitmq.infrastructure.persistence.MensajeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    private final ServicioMensajeriaInputPort servicioMensajeria;

    public MensajeController(ServicioMensajeriaInputPort servicioMensajeria) {
        this.servicioMensajeria = servicioMensajeria;
    }

    @PostMapping
    public ResponseEntity<Void> enviarMensaje(@RequestBody MensajeEntity request) {
        Mensaje mensaje = new Mensaje(request.getContenido());
        servicioMensajeria.procesarMensaje(mensaje);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Mensaje>> listarMensajes() {
        return ResponseEntity.ok(servicioMensajeria.listarMensajes());
    }
}
