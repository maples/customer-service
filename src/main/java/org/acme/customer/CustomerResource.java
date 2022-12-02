package org.acme.customer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.UUID;


@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @POST
    public Response add(CreateCustomerRequest customerRequest){
        var uuid = customerService.add(customerRequest);
        return Response.created(URI.create("/customers/" + uuid)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrive(UUID id){
        try {
            return Response.ok(customerService.findById(id)).build();
        } catch (CustomerNotFoundException e) {
            return Response.noContent().build();
        }
    }
}
