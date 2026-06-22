# Prices

Solución a una prueba técnica de gestión de precios. El sistema expone un endpoint para consultar la tarifa aplicable de un producto (marca, producto, fecha) aplicando reglas de prioridad y solapamiento de rangos de fechas.

## Stack

- Java 26
- Spring Boot 4.1.0 (WebFlux sobre Netty)
- Spring Data JPA + Hibernate
- H2 (base de datos en memoria)
- Springdoc OpenAPI 3.0.3 (Swagger UI)
- Maven (multi-módulo)
- Docker (JRE 26, build con Maven 3.9)
- JUnit 5

## Arquitectura

El proyecto sigue una arquitectura hexagonal (puertos y adaptadores) y DDD dividida en cuatro módulos Maven con dependencias estrictas hacia adentro:

```
presentation  →  application  →  domain
       ↘            ↗
        infrastructure
```

- **domain**: núcleo del negocio. Modelo `Price` (Aggregate Root), value objects (`BrandId`, `ProductId`, `PriceList`, `Money`, `Currency`, `Priority`), excepciones de dominio y repositorios definidos como puertos. No depende de nada externo.
- **application**: casos de uso y orquestación. Implementa los servicios que resuelven la consulta de precio (con solapamiento de rangos y prioridad) y expone los puertos hacia el exterior.
- **infrastructure**: adaptadores técnicos. Implementa los repositorios del dominio contra JPA/H2, expone el modelo de persistencia.
- **presentation**: capa de entrada. Controladores HTTP reactivos, DTOs, validación, mapeo a respuestas y captura global de excepciones con `ControllerAdvice`.

## Patrones de diseño

- **Arquitectura hexagonal**: separación entre dominio puro y adaptadores (web, persistencia).
- **Domain-Driven Design**: Aggregate Root (`Price`), Value Objects inmutables con validación en construcción.
- **Repository**: abstracción de acceso a datos como puerto en dominio, implementación en infraestructura.
- **Factory Method / Object Mother**: `MotherCreator` y `*Mother` para generación de datos de prueba.
- **DTO / Mapeo entre capas**: la presentación no expone el modelo de dominio directamente; transforma a DTOs de respuesta.
- **Builder implícito de Spring**: configuración y ensamblado de beans por el contenedor.
- **Strategy por composición de prioridades**: la selección de tarifa vigente se resuelve por prioridad y rango de fechas.

## Cómo levantar el proyecto

### Requisitos

- JDK 26
- Maven 3.9+ (o usar `./mvnw`)

### En local con Maven

```bash
./mvnw clean install
./mvnw -pl presentation spring-boot:run
```

La aplicación queda disponible en `http://localhost:8081`.

Endpoints útiles:

- `POST http://localhost:8081/price/find` — consulta de tarifa
- `http://localhost:8081/swagger-ui.html` — documentación OpenAPI
- `http://localhost:8081/h2-console` — consola de H2 (JDBC URL: `jdbc:h2:mem:price-service`, user `sa`, password `sa`)

### Tests con Maven

```bash
./mvnw test
```

## Docker y Makefile

El repositorio incluye dos Dockerfiles y un `Makefile` para simplificar build, ejecución y tests.

- `Dockerfile` — multi-stage. Construye el módulo `presentation` con Maven y empaqueta el JAR final sobre una imagen `eclipse-temurin:26-jre`. Expone el puerto 8081.
- `Dockerfile.test` — imagen con Maven que ejecuta `mvn verify` sobre `presentation` y sus dependencias.
- `Makefile` — punto de entrada único con los targets habituales.

### Targets disponibles

```bash
make help    # Listado de targets
make build   # Construir imagen de la app
make run     # Construir y arrancar contenedor (puerto 8081)
make logs    # Ver logs del contenedor en marcha
make stop    # Parar y eliminar el contenedor
make test    # Construir imagen de test y ejecutar la suite
make clean   # Eliminar imágenes y contenedor
```

Flujo típico:

```bash
make build run
# Probar en http://localhost:8081
make logs
make stop
make test
```
