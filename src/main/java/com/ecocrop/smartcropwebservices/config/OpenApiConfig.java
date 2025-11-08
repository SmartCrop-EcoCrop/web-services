package com.ecocrop.smartcropwebservices.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

// Esta anotación define la información general de la API
@OpenAPIDefinition(
        info = @Info(
                title = "SmartCrop API",
                version = "v1.0",
                description = "API para el monitoreo y gestión de cultivos agrícolas mediante dispositivos IoT."
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Servidor Local de Desarrollo"),
                @Server(url = "https://smartcrop-backend-production.up.railway.app", description = "Servidor Desplegado")

        }
)
public class OpenApiConfig {
    // Clase vacía, solo contiene la anotación de metadatos
}