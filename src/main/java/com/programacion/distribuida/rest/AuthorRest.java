package com.programacion.distribuida.rest;

import com.programacion.distribuida.db.Authors;
import com.programacion.distribuida.servicios.AuthorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRest {

    @GET
    @Path("/{id}")
    @Operation(summary = "Find author by id",
            description = "This can only be used when the author for the search exists.")
    public Authors findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @Inject AuthorRepository repository;

    @GET
    @Operation(summary = "Find all authors",
            description = "This is used when you want to list all authors.")
    public List<Authors> findAll() {
        return repository
                .findAll()
                .list();
    }

    @POST
    @Operation(summary = "Create author",
            description = "This is used to add a new author.")
    public void insert(Authors obj) {
        repository.persist(obj);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update author",
            description = "This is used to modify an existing author.")
    public void update(Authors obj, @PathParam("id") Long id) {

        var author = repository.findById(id);

        author.setFirtName(obj.getFirtName());
        author.setLastName(obj.getLastName());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete author",
            description = "This is used to delete an existing author.")
    public void delete( @PathParam("id") Long id ) {
        repository.deleteById(id);
    }
}
