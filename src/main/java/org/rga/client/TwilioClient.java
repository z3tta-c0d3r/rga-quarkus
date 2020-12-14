package org.rga.client;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.rga.domain.request.UserDomain;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/twilio")
@RegisterRestClient(configKey = "rest.client")
@Singleton
public interface TwilioClient {

    @POST
    @Path("/send")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<JsonObject> postTwilio(UserDomain userDomain);
}
