package com.example.rabbitmq.domain.port.in;

import com.example.rabbitmq.domain.model.Mensaje;

import java.util.List;

public interface ServicioMensajeriaInputPort {
    void procesarMensaje(Mensaje mensaje);
    List<Mensaje> listarMensajes();
}
