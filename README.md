# seguros-producto-gestionarproducto

![N|Solid](https://www.tarjetacencosud.cl//TarjetaMasWEB/img/favicon/ms-icon-144x144.png)
**seguros-producto-gestionarproducto**: es un microservicio que permite la gestión de productos financieros y tradicionales

## Funcionalidades

- Gestión de canales de venta
- Gestión de destino de ventas
- Gestión de Producto
- Gestión de tipo de periodo
- Gestión de tipo de recargo
- Gestión de tipo de traspaso
- Gestión de tipo de seguro
- Gestión de tipo de descuento
- Gestión de modo de traspaso
- Gestión de tarifa por
- Gestión de tipo de promoción
- Gestión de tipo de ajuste
- Gestión de tipo de tarifa
- Gestión de conceptos
- Gestión de homologación indentificador



## Tecnologías

Se utilizaron varias librerias de código abierto para funcionar correctamente, entre las más importantes están:

- [org.springframework.boot:spring-boot-starter-web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [org.springframework.cloud:spring-cloud-starter-config](https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-config)
- [org.springframework.boot:spring-boot-starter-data-jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [org.springframework.boot:spring-boot-starter-security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
- [org.springframework.kafka:spring-kafka:2.2.7.RELEASE](https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka/2.2.7.RELEASE)

## Installation

**seguros-producto-gestionarproducto**  requiere de [Java 1.8+](https://www.java.com)  para inciar.

Iniciar el servidor 

```sh
gradlew bootRun
```

#### Proceso para compilación

```sh
gradlew build
```

## Docker

Para implementar en un contenedor Docker.

El microservicio hace uso de un plugin de docker para la generación de la imagen ejecutando la siguiente tarea:


```sh
./gradlew buildDocker -PdockerTag=${tag} -Penv=${env} -PkafkaTruststoreJks=truststore-kafka-${env}.jks
```

Esto creará la imagen de **seguros-producto-gestionarproducto** con las dependencias necesarias.
Asegúrese de cambiar ${tag} con el tag correspondiente y ${env} con el ambiente correspondiente

Una vez hecho esto, ejecute la imagen de Docker y asigne el puerto a lo que desee en tu host. 

En este ejemplo, simplemente asignamos el puerto 8000 del host al puerto 8080 del Docker (o cualquier puerto expuesto en el Dockerfile):

```sh
docker run -d -p 8000:8080 operacionesregistry.azurecr.io/pana/dev/seguros-producto-gestionarproducto:1.1.1
```

Verifique la implementación navegando a la dirección de su servidor en su navegador preferido.

```sh
127.0.0.1:8000
```

## License

Copyright © Cencosud Scotiabank
