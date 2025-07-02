package com.example.rabbitmq.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeJpaRepository extends JpaRepository<MensajeEntity, Long> {
}
