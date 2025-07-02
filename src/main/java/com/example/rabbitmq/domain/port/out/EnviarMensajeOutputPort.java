package com.example.rabbitmq.domain.port.out;

import com.example.rabbitmq.domain.model.Mensaje;

public interface EnviarMensajeOutputPort {
    void enviar(Mensaje mensaje);
}
