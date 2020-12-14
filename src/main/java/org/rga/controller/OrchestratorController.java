package org.rga.controller;

import io.smallrye.mutiny.Uni;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.rga.client.TwilioClient;
import org.rga.domain.request.UserDomain;
import org.rga.entity.MasUser;
import org.rga.mapper.UserMapper;
import org.rga.service.OrchestratorService;
import org.rga.service.TwilioService;
import org.rga.service.UserService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orchestrator")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class OrchestratorController {
    @Inject
    UserService userService;

    @Inject
    OrchestratorService orchestratorService;

    @Inject
    TwilioService twilioService;

    @Inject
    @RestClient
    TwilioClient twilioClient;

    @Inject
    Vertx vertx;

    private WebClient client;

    @PostConstruct
    void initialize() {
        VertxOptions vxOptions = new VertxOptions().setBlockedThreadCheckInterval(200000000);
        vertx = Vertx.vertx(vxOptions);

        this.client = WebClient.create(vertx,
                new WebClientOptions().setDefaultHost("run.mocky.io").setDefaultPort(443).
                        setSsl(true).setTrustAll(true));
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getUserById(@PathParam("id") int id) {
        return userService.findUserById(id).onItem()
                .transform(user -> user != null ? Response.ok(user) : Response.status(Response.Status.NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @POST
    public Uni<JsonObject> orchestrator(UserDomain userDomain) {

        // Recuperamos los datos del dominio de entrada
        //MasUser userEntity = UserMapper.INSTANCE.UserDomainToUserEntity(userDomain);

        // Realizamos la llamada al gestor documental que realiza la busqueda del usuario
        Uni<JsonObject> uni = orchestratorService.getUserDataByDocumentum(this.client);

        // Comprobamos si existe o no existe el usuario introducido para realizar
        // las siguientes acciones de llamar a twilio
        Uni<JsonObject> uniTwilio =  uni.onItem().transformToUni(resp -> {
            JsonObject jsonObject = resp;
            log.info(jsonObject.getBoolean("response").toString());
            MasUser userEntity = UserMapper.INSTANCE.UserDomainToUserEntity(userDomain);
            if (jsonObject.getBoolean("response")) {
                return twilioClient.postTwilio(userDomain);
            } else {

                userService.add(userEntity);

                return twilioClient.postTwilio(userDomain);
            }
        });

        Uni<JsonObject> uniUpdate = uniTwilio.onItem().transformToUni( data -> {
            JsonObject jsonObject = data;
            log.info(jsonObject.getBoolean("response").toString());
            String name = "kta";
            return Uni.createFrom().item(name)
                    .onItem().transform(n -> jsonObject);
        });

        return uniUpdate;
    }

    @POST
    @Path("/twilio")
    public Uni<JsonObject>  postTwilio(UserDomain userDomain) {

        log.info("SE HA LLAMADO AL MICRO PARA LANZAR AL TWILIO DESDE EL ORQUESTADOR");
        Uni<JsonObject> uniTwilio = twilioClient.postTwilio(userDomain);

        // comprobamos si la respuesta es correcta si es asi actualizamos los valores
        // de los procesados
       return uniTwilio.onItem().transform(resp -> {
                // Comprobamos si la respuesta recibida el usuario existe o no existe
                if (resp != null && resp.getBoolean("response")) {
                    MasUser userEntity = UserMapper.INSTANCE.UserDomainToUserEntity(userDomain);
                    userEntity.setId(42);
                    // Actualizamos la base de datos para que el proceso este actualizado
                    userService.update(userEntity);
                }
            return resp;
        });
    }
}
