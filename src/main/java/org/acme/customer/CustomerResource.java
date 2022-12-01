package org.acme.customer;

import java.net.URI;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;


@Path("/customers")
public class CustomerResource {

    private static final Logger LOG = Logger.getLogger(CustomerResource.class);

    @Inject
    CustomerService customerService;

    @POST
    public Response add(CreateCustomerRequest customerRequest){
        var uuid = customerService.add(customerRequest);
        LOG.info("Create customer with ID: "+ uuid);
        return Response.created(URI.create("/customers/" + uuid)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDetailView retrive(UUID id){
        return customerService.findById(id);
    }
}
