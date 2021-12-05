package de.dueto.backend.swagger;

import io.swagger.annotations.*;

@SwaggerDefinition(
        info = @Info(
                description = "DueTo-Backend API",
                version = "V12.0.12",
                title = "DueTo-Backend API",
                contact = @Contact(
                        name = "DueTo-Team",
                        email = "sedueto@gmail.com",
                        url = "https://www.sedueto.wordpress.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
)
public interface ApiDocumentationConfig {

}