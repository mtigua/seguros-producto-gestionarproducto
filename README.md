# seguros-producto-gestionarproducto

![N|Solid](https://www.tarjetacencosud.cl//TarjetaMasWEB/img/favicon/ms-icon-144x144.png)
**seguros-producto-gestionarproducto**: es un microservicio que permite la gesti�n de productos financieros y tradicionales

## Funcionalidades

- Gesti�n de canales de venta
- Gesti�n de destino de ventas
- Gesti�n de Producto
- Gesti�n de tipo de periodo
- Gesti�n de tipo de recargo
- Gesti�n de tipo de traspaso
- Gesti�n de tipo de seguro
- Gesti�n de tipo de descuento
- Gesti�n de modo de traspaso
- Gesti�n de tarifa por
- Gesti�n de tipo de promoci�n
- Gesti�n de tipo de ajuste
- Gesti�n de tipo de tarifa
- Gesti�n de conceptos
- Gesti�n de homologaci�n indentificador
- Gesti�n de Registro de cobertura
- Gesti�n de Recargo por asegurado
- Gesti�n de Upgrade


## Tecnolog�as

Se utilizaron varias librerias de c�digo abierto para funcionar correctamente, entre las m�s importantes est�n:

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

#### Proceso para compilaci�n

```sh
gradlew build
```
#### Documentaci�n api
https://api-proxy-dev.adrfsc.cl/gestionarProducto/swagger-ui.html

## Docker

Para implementar en un contenedor Docker.

El microservicio hace uso de un plugin de docker para la generaci�n de la imagen ejecutando la siguiente tarea:


```sh
./gradlew buildDocker -PdockerTag=${tag} -Penv=${env} -PkafkaTruststoreJks=truststore-kafka-${env}.jks
```

Esto crear� la imagen de **seguros-producto-gestionarproducto** con las dependencias necesarias.
Aseg�rese de cambiar ${tag} con el tag correspondiente y ${env} con el ambiente correspondiente

Una vez hecho esto, ejecute la imagen de Docker y asigne el puerto a lo que desee en tu host. 

En este ejemplo, simplemente asignamos el puerto 8000 del host al puerto 8080 del Docker (o cualquier puerto expuesto en el Dockerfile):

```sh
docker run -d -p 8000:8080 operacionesregistry.azurecr.io/pana/dev/seguros-producto-gestionarproducto:1.1.1
```

Verifique la implementaci�n navegando a la direcci�n de su servidor en su navegador preferido.

```sh
127.0.0.1:8000
```

## License

Copyright � Cencosud Scotiabank
