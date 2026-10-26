# Biblioteca de videojuegos

API REST en **Java 21** y **Spring Boot 3.2.5** para registrar videojuegos (fisicos y digitales), clientes y prestamos. Persistencia con **JPA + H2** en memoria.

## Arquitectura y jerarquia de clases

``` text
ContenidoDigital (abstracta)
 ├── JuegoFisico     calcularPrecioRenta = (precioBase * dias) + 2.00
 └── JuegoDigital    calcularPrecioRenta = precioBase * dias
                     (si tamanoGB > 50, descuento del 10%)

Cliente              maximo 3 juegos alquilados a la vez
```

Capas del proyecto:

- **Modelo:** encapsulamiento (atributos privados), herencia, polimorfismo (`calcularPrecioRenta`) y abstraccion (`ContenidoDigital`).
- **Repositorios:** acceso a H2 con Spring Data JPA.
- **Servicios (`@Service`):** reglas de negocio (disponibilidad, limite de 3 prestamos, calculo de renta).
- **Controladores (`@RestController`):** endpoints HTTP.

## Como ejecutar el proyecto

## 1. Clonar el repositorio

git clone [https://github.com/santiagot2000/bibliotecajuegos.git](https://github.com/santiagot2000/bibliotecajuegos.git)

## 2. Entrar al directorio

cd bibliotecajuegos

## 3. Ejecutar la aplicación en la terminal

./mvnw spring-boot:run

## 4. Ejecutar la aplicación en windows

mvnw.cmd spring-boot:run

La API queda en `http://localhost:8080`

## Consola H2

URL: `http://localhost:8080/h2-console`
JDBC URL: `jdbc:h2:mem:bibliotecadb`

Nota: La base de datos es en memoria, por lo que los datos se reinician al detener la aplicación.

## Ejemplos de peticiones HTTP

### Registrar juego fisico — `POST /api/juegos/fisico` Body (JSON)

{
  "titulo": "The Legend of Zelda",
  "anioLanzamiento": 2017,
  "precioBase": 5.0,
  "pesoGramos": 150
}

### Registrar juego digital — `POST /api/juegos/digital` Body (JSON)

{
  "titulo": "Cyberpunk 2077",
  "anioLanzamiento": 2020,
  "precioBase": 4.0,
  "tamanoGB": 70
}

### Listar juegos disponibles — `GET /api/juegos`

Sin cuerpo. Devuelve solo titulos con `disponible = true`.

### Registrar cliente — `POST /api/clientes` Body (JSON)

```bash
{
  "nombre": "Ana Perez",
  "email": "ana@mail.com"
}
```

### Alquilar — `POST /api/prestamos/alquilar` Body (JSON)

{
  "clienteId": 1,
  "juegoId": 1,
  "dias": 3
}

## Validaciones

1. El juego debe existir y estar disponible.
2. El cliente no puede tener mas de 3 juegos alquilados.
Respuesta de éxito (200 OK):

## Respuesta de exito (ejemplo)

{
  "mensaje": "Prestamo realizado con exito.",
  "cliente": "Ana Perez",
  "juego": "The Legend of Zelda",
  "dias": 3,
  "costoTotal": 17.0
}

## Pruebas Rápidas con cURL

## Registrar Juego Físico

```bash
curl -X POST http://localhost:8080/api/juegos/fisico -H "Content-Type: application/json" -d "{\"titulo\":\"Zelda\",\"anioLanzamiento\":2017,\"precioBase\":5.0,\"pesoGramos\":150}"
```

## Registrar Juego Digital

```bash
curl -X POST http://localhost:8080/api/juegos/digital -H "Content-Type: application/json" -d "{\"titulo\":\"Cyberpunk 2077\",\"anioLanzamiento\":2020,\"precioBase\":4.0,\"tamanoGB\":70}"
```

## Registrar Cliente

```bash
curl -X POST http://localhost:8080/api/clientes -H "Content-Type: application/json" -d "{\"nombre\":\"Ana Perez\",\"email\":\"ana@mail.com"\"}"
```

## Listar Disponibles

```bash
curl http://localhost:8080/api/juegos
```

## Alquilar Juego

```bash
curl -X POST http://localhost:8080/api/prestamos/alquilar -H "Content-Type: application/json" -d "{\"clienteId\":1,\"juegoId\":1,\"dias\":3}"
```
