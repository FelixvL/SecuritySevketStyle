package com.security.presentatie.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.security.presentatie.domain.Trein;
import com.security.presentatie.persistence.TreinService;
import org.springframework.web.bind.annotation.RequestHeader;

@Path("/trein")
@Component
public class TreinEndpoint {
	
	private final TreinService treinService;

    @Autowired
    public TreinEndpoint(TreinService treinService) {
        this.treinService = treinService;
    }

    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@PreAuthorize("isAuthenticated()")
	public Response listGroep(){
		Iterable <Trein> treinen = treinService.findAll();
		return Response.ok(treinen).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@PreAuthorize("isAuthenticated()")
	public Response postTrein(Trein trein){
		Trein result = treinService.save(trein);
		return Response.accepted(result.getId()).build();	
	}

}
