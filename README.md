# ClimaAlert

## Enunciado

Nos han encargado el diseño y desarrollo de **Climalert**, un sistema de monitoreo climático y envío automático de alertas.

Climalert funcionará como un servicio autónomo, sin interfaz gráfica, cuya responsabilidad es conectarse periódicamente a un proveedor meteorológico externo, procesar los datos recibidos y notificar por correo electrónico a las entidades correspondientes cuando se detecten condiciones climáticas peligrosas o inusuales.

Para esta primera iteración solamente consideraremos como **alerta** a una temperatura mayor a **35°C** y una humedad superior a **60%**.

El sistema debe desarrollarse utilizando **Spring Boot** y debe cumplir con los siguientes puntos:

---

## Requerimientos

### 1. Integración con proveedor externo de clima

El sistema deberá integrarse vía REST con **WeatherAPI**, utilizando su endpoint:

```txt
/current.json
```

La ubicación consultada será fija, por ejemplo:

```txt
CABA
```

Cada 5 minutos, el sistema deberá obtener los datos climáticos actuales y almacenarlos localmente para registro histórico y análisis posterior.

### 2. Procesamiento de alertas meteorológicas

Cada 1 minuto, el sistema deberá analizar la última información disponible del clima.

Si se detectan condiciones críticas, deberá generarse una alerta.

### 3. Notificación por correo electrónico

Al generarse una alerta, el sistema deberá enviar un correo a los siguientes destinatarios:

```txt
admin@clima.com
emergencias@clima.com
meteorologia@clima.com
```

El correo deberá incluir el detalle completo del clima.

---

## Descripción del proyecto

**ClimaAlert** implementa un servicio autónomo desarrollado con **Spring Boot** que consulta periódicamente el clima actual desde WeatherAPI, guarda un historial local de consultas y analiza si las condiciones climáticas actuales representan una alerta.

La aplicación no posee interfaz gráfica. Su funcionamiento se basa en tareas programadas mediante `@Scheduled`.

---

## Tecnologías utilizadas

* Java 21
* Spring Boot
* Maven
* WeatherAPI
* Lombok
* Java Mail Sender

---

## Configuración

Para utilizar WeatherAPI es necesario configurar una API key.

En el archivo:

```txt
src/main/resources/application.properties
```

se utilizan las siguientes propiedades:

```properties
weatherapi.base-url=https://api.weatherapi.com/v1
weatherapi.key=${WEATHER_API_KEY}
weatherapi.location=Lomas de Zamora, Buenos Aires, Argentina
weatherapi.lang=es
```

La variable de entorno `WEATHER_API_KEY` debe contener la API key real de WeatherAPI.

Además, para el envío de correos, se utilizan las siguientes variables de entorno:

```properties
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
```

Estas variables son necesarias para autenticar la cuenta utilizada por el servicio de notificación por correo electrónico.

---

## Ejecución del proyecto

Desde la raíz del proyecto, donde se encuentra el archivo `pom.xml`, ejecutar:

```powershell
.\mvnw.cmd spring-boot:run
```

Para compilar el proyecto:

```powershell
.\mvnw.cmd clean install
```

---

## Funcionamiento general

El sistema cuenta con dos procesos principales:

### Consulta del clima

Cada cierto intervalo de tiempo, el sistema consulta WeatherAPI para obtener la información climática actual de la ubicación configurada.

Los datos obtenidos se parsean a un modelo propio de dominio y luego se almacenan en un repositorio en memoria.

### Análisis de alertas

Cada cierto intervalo de tiempo, el sistema obtiene el clima más reciente guardado y evalúa si se cumple la condición de alerta:

```java
temperatura > 35 && humedad > 60
```

En caso de cumplirse, se genera un mensaje de alerta con el detalle completo del clima y se envía a los destinatarios configurados.

---

## Estructura del proyecto

```txt
src
└── main
    ├── java
    │   └── ar.edu.utn.frba.dds.climalert
    │       ├── config
    │       ├── dto
    │       ├── exceptions
    │       ├── models
    │       ├── repositories
    │       ├── schedulers
    │       └── services
    └── resources
        └── application.properties
```

---

## Paquetes principales

### `config`

Contiene configuraciones generales de la aplicación.

### `dto`

Contiene los objetos utilizados para mapear la respuesta de WeatherAPI.

### `exceptions`

Contiene excepciones propias del dominio.

### `models`

Contiene las clases propias del dominio de ClimaAlert.

### `repositories`

Contiene la lógica de almacenamiento local. En esta primera iteración, el almacenamiento se realiza en memoria.

### `schedulers`

Contiene las tareas programadas encargadas de consultar el clima y analizar alertas periódicamente.

### `services`

Contiene la lógica principal del sistema, incluyendo la consulta del clima, el análisis de alertas y la notificación por correo electrónico.

---

## Estado actual

El proyecto permite:

* Consultar WeatherAPI mediante REST.
* Mapear la respuesta recibida a DTOs propios.
* Convertir los DTOs a modelos de dominio.
* Guardar los climas consultados en memoria.
* Obtener el clima más reciente.
* Analizar si existe una alerta meteorológica.
* Generar y enviar una notificación cuando se detectan condiciones críticas.

---

## Prueba de funcionamiento

La prueba de funcionamiento se encuentra en el mismo repositorio en formato `.png`.

En dicha prueba se observa el envío de una notificación por correo electrónico. La temperatura mostrada no supera los 35°C, ya que el envío fue forzado con fines demostrativos para validar la funcionalidad de notificación.

---

## Autor

**Simon Rojas**

Proyecto realizado para la materia **Diseño de Sistemas**.
