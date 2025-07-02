package com.example.rabbitmq.infrastructure.out.rabbit;

import com.example.rabbitmq.domain.model.Mensaje;
import com.example.rabbitmq.domain.port.out.EnviarMensajeOutputPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EnviarMensajeRabbitAdapter implements EnviarMensajeOutputPort {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "mensaje.exchange";
    private static final String ROUTING_KEY = "mensaje.ruta";

    public EnviarMensajeRabbitAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void enviar(Mensaje mensaje) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, mensaje.getContenido());
    }
}
