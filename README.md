# Biblioteca de videojuegos

API REST en **Java 21** y **Spring Boot 3.2.5** para registrar videojuegos (fisicos y digitales), clientes y prestamos. Persistencia con **JPA + H2** en memoria.

## Arquitectura y jerarquia de clases

```
ContenidoDigital (abstracta)
 ├── JuegoFisico     calcularPrecioRenta = (precioBase * dias) + 2.00
 └── JuegoDigital    calcularPrecioRenta = precioBase * dias
                     (si tamanoGB > 50, descuento del 10%)

Cliente              maximo 3 juegos alquilados a la vez
```

Capas:

- **Modelo:** encapsulamiento (atributos privados), herencia, polimorfismo (`calcularPrecioRenta`) y abstraccion (`ContenidoDigital`).
- **Repositorios:** acceso a H2 con Spring Data JPA.
- **Servicios (`@Service`):** reglas de negocio (disponibilidad, limite de 3 prestamos, calculo de renta).
- **Controladores (`@RestController`):** endpoints HTTP.

## Como ejecutar

Requisitos: Java 17+ (el proyecto usa 21) y Maven (o el wrapper `mvnw`).

```bash
git clone https://github.com/santiagot2000/bibliotecajuegos.git
cd bibliotecajuegos
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

La API queda en `http://localhost:8080`. Consola H2: `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:bibliotecadb`, usuario `sa`, password `password`).

H2 es en memoria: al apagar la app se pierden los datos.

## Ejemplos de peticiones HTTP

### Registrar juego fisico — `POST /api/juegos/fisico`

```json
{
  "titulo": "The Legend of Zelda",
  "anioLanzamiento": 2017,
  "precioBase": 5.0,
  "pesoGramos": 150
}
```

Costo de renta a 3 dias: `(5 * 3) + 2 = 17`.

### Registrar juego digital — `POST /api/juegos/digital`

```json
{
  "titulo": "Cyberpunk 2077",
  "anioLanzamiento": 2020,
  "precioBase": 4.0,
  "tamanoGB": 70
}
```

Costo de renta a 2 dias: `4 * 2 = 8`, con 10% de descuento por superar 50 GB: `7.2`.

### Listar juegos disponibles — `GET /api/juegos`

Sin cuerpo. Devuelve solo titulos con `disponible = true`.

### Registrar cliente — `POST /api/clientes`

```json
{
  "nombre": "Ana Perez",
  "email": "ana@mail.com"
}
```

### Alquilar — `POST /api/prestamos/alquilar`

```json
{
  "clienteId": 1,
  "juegoId": 1,
  "dias": 3
}
```

Validaciones:

1. El juego debe existir y estar disponible.
2. El cliente no puede tener mas de 3 juegos alquilados.

Respuesta de exito (ejemplo):

```json
{
  "mensaje": "Prestamo realizado con exito.",
  "cliente": "Ana Perez",
  "juego": "The Legend of Zelda",
  "dias": 3,
  "costoTotal": 17.0
}
```

cURL de referencia:

```bash
curl -X POST http://localhost:8080/api/juegos/fisico -H "Content-Type: application/json" -d "{\"titulo\":\"Zelda\",\"anioLanzamiento\":2017,\"precioBase\":5.0,\"pesoGramos\":150}"
curl -X POST http://localhost:8080/api/juegos/digital -H "Content-Type: application/json" -d "{\"titulo\":\"Cyberpunk 2077\",\"anioLanzamiento\":2020,\"precioBase\":4.0,\"tamanoGB\":70}"
curl -X POST http://localhost:8080/api/clientes -H "Content-Type: application/json" -d "{\"nombre\":\"Ana Perez\",\"email\":\"ana@mail.com\"}"
curl http://localhost:8080/api/juegos
curl -X POST http://localhost:8080/api/prestamos/alquilar -H "Content-Type: application/json" -d "{\"clienteId\":1,\"juegoId\":1,\"dias\":3}"
```
