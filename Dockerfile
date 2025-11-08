#Usa una imagen base con Java (JDK) para ejecutar la aplicación
FROM eclipse-temurin:25-jre-jammy

#Crea un volumen para que la aplicación escriba logs o archivos temporales
VOLUME /tmp

#Expone el puerto donde Spring Boot está corriendo (8081)
EXPOSE 8081

#Copia el archivo JAR compilado (asumiendo que usas Maven 'package')
# Nota: Necesitas compilar tu proyecto primero para generar este archivo JAR.
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

#Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]