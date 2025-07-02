package com.example.rabbitmq.infrastructure.in.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MensajeRabbitListener {

    @RabbitListener(queues = "mensaje.queue")
    public void recibirMensaje(String contenido) {
        System.out.println("Mensaje recibido: " + contenido);
        // Aquí puedes añadir lógica para procesar el mensaje si quieres
    }
}
