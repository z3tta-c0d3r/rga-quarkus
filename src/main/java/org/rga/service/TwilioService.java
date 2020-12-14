package org.rga.service;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class TwilioService {
    @Inject
    Vertx vertx;

    private WebClient client;

    @PostConstruct
    void initialize() {
        this.client = WebClient.create(vertx,
                new WebClientOptions().setDefaultHost("run.mocky.io").setDefaultPort(443).
                        setSsl(true).setTrustAll(true));
    }

    public Uni<JsonObject> sendTwilio() {
        return client.get("/v3/a8d9cf86-5910-4511-ab17-68f33f436b3e").send().onItem().transform(resp -> {
            if (resp.statusCode() == 200) {
                // Comprobamos si la respuesta recibida el usuario existe o no existe
                JsonObject jsonObject = resp.bodyAsJsonObject();

                if ( jsonObject != null && jsonObject.getBoolean("response")){
                    // Existe usuario y llamamos a Twilio
                    log.info("SI SE ENVIO CORRECTAMENTE SE ACTUALIZA LA BBDD EL CAMPO PROCESADO " +
                            "Y ACTUALIZAMOS CRM");
                } else {
                    log.info("NO SE ENVIO LA LLAMADA Y LO DEJAMOS SIN PROCESAR Y DEVOLVEMOS EL ERROR");
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
