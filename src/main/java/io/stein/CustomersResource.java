package io.stein;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/customers")
public class CustomersResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String[] getCustomers() {
        return new String[]{"Hello World!"};
    }
}
