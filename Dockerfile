# Etapa 1: Build con Gradle
FROM gradle:8.14.2-jdk17 AS build
WORKDIR /home/gradle/project

# Copiar archivos del proyecto y asegurar permisos
COPY --chown=gradle:gradle . .
RUN chown -R gradle:gradle /home/gradle

# Copiar el archivo gradle.properties para evitar problemas de compresión
COPY --chown=gradle:gradle gradle.properties ./gradle.properties

# Crear un caché aislado y compilar el WAR con Spring Boot
RUN mkdir -p /home/gradle/gradle-cache && chown gradle:gradle /home/gradle/gradle-cache \
  && gradle clean bootWar \
     --no-daemon \
     --no-parallel \
     --gradle-user-home /home/gradle/gradle-cache \
     --stacktrace \
     --info

# Etapa 2: Imagen con Tomcat
FROM tomcat:10.1-jdk17

# Limpiar aplicaciones por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el .war generado al directorio de despliegue de Tomcat
COPY --from=build /home/gradle/project/build/libs/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
