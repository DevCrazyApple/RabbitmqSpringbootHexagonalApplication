package com.example.rabbitmq.infrastructure.persistence;

import com.example.rabbitmq.domain.model.Mensaje;
import com.example.rabbitmq.domain.port.out.GuardarMensajeOutputPort;
import org.springframework.stereotype.Component;

@Component
public class GuardarMensajePostgresAdapter implements GuardarMensajeOutputPort {

    private final MensajeJpaRepository repository;

    public GuardarMensajePostgresAdapter(MensajeJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mensaje guardar(Mensaje mensaje) {
        MensajeEntity entidad = new MensajeEntity(mensaje.getContenido());
        MensajeEntity guardado = repository.save(entidad);
        return new Mensaje(guardado.getId(), guardado.getContenido());
    }
}
