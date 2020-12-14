package org.rga.service;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class OrchestratorService {

    @Inject
    TwilioService twilioService;

    public Uni<JsonObject> getUserDataByDocumentum(WebClient client) {
        return client.get("/v3/a8d9cf86-5910-4511-ab17-68f33f436b3e")
                .send()
                .onItem().transform(resp -> {
            if (resp.statusCode() == 200) {
                // Comprobamos si la respuesta recibida el usuario existe o no existe
                JsonObject jsonObject = resp.bodyAsJsonObject();

                if ( jsonObject != null && jsonObject.getBoolean("response")){
                    // Existe usuario y llamamos a Twilio
                    log.info("Llamamos a TWILIO Y NO SE NECESITA GRABAR UN USUARIO");
                } else {
                    log.info("Llamamos a guardar usuario y LUEGO LLAMAMOS A TWILIO");
                }
                return resp.bodyAsJsonObject();
            } else {
                return new JsonObject()
                        .put("code", resp.statusCode())
                        .put("message", resp.bodyAsString());
            }
        });
    }
}
