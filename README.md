# 🎮 Biblioteca de Juegos - API REST & POO

Proyecto universitario desarrollado en **Java 21** y **Spring Boot 3.2.5** para la gestión y alquiler de videojuegos físicos y digitales, aplicando principios de Programación Orientada a Objetos (POO) y Clean Architecture.

---

## 🏛️ 1. Arquitectura y Jerarquía de Clases

El proyecto sigue el patrón **Controller-Repository-Model** desacoplado mediante DTOs e inmutabilidad (`record`):

* **`Contenido_Digital` (Clase Abstracta):** Modela la entidad base con atributos comunes (`id`, `titulo`, `precioBase` `disponible`) y define el método abstracto `calcularPrecioRenta(int dias)`.
* **`Juego_Fisico` (Subclase):** Hereda de `Contenido_Digital`. Sobrescribe `calcularPrecioRenta` agregando el costo de envío.
* **`Juego_Digital` (Subclase):** Hereda de `Contenido_Digital`. Sobrescribe `calcularPrecioRenta` ajustando el precio según el tamaño de descarga (GB).
* **`Cliente`:** Maneja la lista de juegos prestados e implementa la regla de negocio de **máximo 3 alquileres activos**.
* **`PrestamoRequest` (Java Record):** DTO inmutable usado para transferir los datos de alquiler en las peticiones HTTP.

---

## 🚀 2. Instrucciones para Ejecutar la Aplicación

### Prerrequisitos

* **Java 21** instalado.
* **Maven 3.x** instalado o el wrapper del proyecto (`./mvnw`).

### Pasos

1. **Clonar el repositorio:**
   ```bash
   git clone <URL-DE-TU-REPOSITO-GITHUB>
   cd bibliotecajuegos