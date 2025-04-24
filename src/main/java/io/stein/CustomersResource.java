package io.stein;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Path("/customers")
public class CustomersResource {

    @Context
    UriInfo uriInfo;

    //TODO replace this
    private final Map<UUID, Customer> customers = new HashMap<>();

    {
        Customer customer = new Customer();
        customer.setUuid(UUID.randomUUID());
        customer.setName("Tom Mayer");
        customer.setBirthdate(LocalDate.now().minusYears(30));
        customer.setState("active");
        customers.put(customer.getUuid(), customer);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String[] getCustomers() {
        return new String[]{"Hello World!"};
    }

    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("uuid") UUID uuid) {
        var customer = customers.get(uuid);

        if (customer == null) {
            return Response.status(404).build();
        } else {
            return Response.ok(customer).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {

        if (customer.getUuid() != null) {
            return Response.status(400).build();
        }

        customer.setUuid(UUID.randomUUID());
        this.customers.put(customer.getUuid(), customer);

        return Response
                .created(uriInfo
                        .getAbsolutePathBuilder()
                        .path(customer.getUuid().toString())
                        .build())
                .entity(customer)
                .build();
    }
}
