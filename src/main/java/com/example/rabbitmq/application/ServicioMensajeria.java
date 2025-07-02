package com.example.rabbitmq.application;

import com.example.rabbitmq.domain.model.Mensaje;
import com.example.rabbitmq.domain.port.in.ServicioMensajeriaInputPort;
import com.example.rabbitmq.domain.port.out.EnviarMensajeOutputPort;
import com.example.rabbitmq.domain.port.out.GuardarMensajeOutputPort;
import com.example.rabbitmq.infrastructure.persistence.MensajeEntity;
import com.example.rabbitmq.infrastructure.persistence.MensajeJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioMensajeria implements ServicioMensajeriaInputPort {

    private final EnviarMensajeOutputPort envioRabbit;
    private final GuardarMensajeOutputPort guardarMensaje;
    private final MensajeJpaRepository mensajeJpaRepository;

    public ServicioMensajeria(EnviarMensajeOutputPort envioRabbit, GuardarMensajeOutputPort guardarMensaje,
                              MensajeJpaRepository mensajeJpaRepository) {
        this.envioRabbit = envioRabbit;
        this.guardarMensaje = guardarMensaje;
        this.mensajeJpaRepository = mensajeJpaRepository;
    }

    @Override
    public void procesarMensaje(Mensaje mensaje) {
//        var mensaje = new Mensaje(mensaje);
        var guardado = guardarMensaje.guardar(mensaje);
        envioRabbit.enviar(guardado);
    }

    @Override
    public List<Mensaje> listarMensajes() {
        List<MensajeEntity> entidades = mensajeJpaRepository.findAll();
        return entidades.stream()
                .map(e -> new Mensaje(e.getId(), e.getContenido()))
                .collect(Collectors.toList());
    }
}
