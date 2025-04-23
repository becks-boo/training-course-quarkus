package io.stein;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.UUID;

@Path("/customers")
public class CustomersResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String[] getCustomers() {
        return new String[]{"Hello World!"};
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        customer.setUuid(UUID.randomUUID());

        return Response
                .created(uriInfo
                        .getAbsolutePathBuilder()
                        .path(customer.getUuid().toString())
                        .build())
                .entity(customer)
                .build();
    }


}
