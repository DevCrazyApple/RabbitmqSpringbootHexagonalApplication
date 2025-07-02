# Proyecto: RabbitMQ + Spring Boot + PostgreSQL (Arquitectura Hexagonal)

## 🧱 Arquitectura

Esta aplicación implementa una arquitectura **hexagonal** (Ports & Adapters) con integración a:

- **RabbitMQ**: mensajería asíncrona  
- **PostgreSQL**: persistencia con Spring Data JPA  
- **Spring Boot**: motor principal de la aplicación  
- **Gradle**: herramienta de build  
- **Docker + Docker Compose**: para orquestación local

---

## 📦 Estructura de paquetes
```
/RabbitmqSpringbootHexagonalApplication
├── docker-compose.yml
├── Dockerfile
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── rabbitmq
│       │               ├── application
│       │               │   └── ServicioMensajeria.java
│       │               ├── configuration
│       │               │   └── RabbitConfig.java
│       │               ├── domain
│       │               │   ├── model
│       │               │   │   └── Mensaje.java
│       │               │   └── port
│       │               │       ├── in
│       │               │       │   └── ServicioMensajeriaInputPort.java
│       │               │       └── out
│       │               │           ├── EnviarMensajeOutputPort.java
│       │               │           └── GuardarMensajeOutputPort.java
│       │               ├── infrastructure
│       │               │   ├── in
│       │               │   │   ├── controller
│       │               │   │   │   └── MensajeController.java
│       │               │   │   └── rabbit
│       │               │   │       └── MensajeRabbitListener.java
│       │               │   ├── out
│       │               │   │   └── rabbit
│       │               │   │       └── EnviarMensajeRabbitAdapter.java
│       │               │   └── persistence
│       │               │       ├── GuardarMensajePostgresAdapter.java
│       │               │       ├── MensajeEntity.java
│       │               │       └── MensajeJpaRepository.java
│       │               ├── RabbitmqSpringbootHexagonalApplication.java
│       │               └── ServletInitializer.java
│       └── resources
│           ├── application.properties
│           ├── static
│           └── templates
```

---

## 🔄 Flujo de la aplicación

1. **POST /mensajes** — Recibe un mensaje por HTTP y lo envía a RabbitMQ.  
2. **RabbitMQ Listener** — Escucha en `mensaje.queue` y procesa el mensaje.  
3. **Persistencia** — Guarda el mensaje en PostgreSQL usando JPA.

---

## ⚙️ Componentes clave

### 📬 RabbitMQ

- Cola: `mensaje.queue`  
- Exchange: `mensaje.exchange`  
- Routing Key: `mensaje.routing.key`  
- Configuración en `RabbitMQConfig.java`  
- Productor: `MensajeEventPublisher`  
- Consumidor: `MensajeRabbitListener`

### 🗃 PostgreSQL

- Entidad: `Mensaje.java`  
- Repositorio: `MensajeRepository.java`  
- Persistencia implementada en adaptador salida `PersistenciaMensajeAdapter`

---

## 🚀 Levantar la app con Docker

```bash
docker-compose up --build

Esto inicia:

    PostgreSQL (puerto 5432)

    RabbitMQ (puertos 5672 y 15672)

    Spring Boot App (puerto 8080)

🔎 Endpoints disponibles

    POST http://localhost:8080/mensajes -> Manda un mensaje y el rabbit lo escucha, procesa y almacena
        {
          "contenido": "Hola mundo"
        }
    
    GET http://localhost:8080/mensajes -> Lista todos los mensajes procesados y almacenados
        [
          {
            "id": 1,
            "contenido": "Hola mundo"
          }
        ]

📝 Notas

    Asegúrate de que RabbitMQ y PostgreSQL estén arriba antes de que arranque la app.

    Puedes acceder a la UI de RabbitMQ en: http://localhost:15672
    Usuario: guest, Contraseña: guest
