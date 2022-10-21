package org.acme.resource;

import org.acme.dto.PostDto;
import org.acme.service.PostService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject
    private PostService postService;

    @Inject
    Validator validator;

    @GET
    public Response getAll(){
        return Response.ok(postService.getAll()).build();
    }

    @POST
    @Path("/save")
    public Response save(@Valid PostDto postDto) {
        Set<ConstraintViolation<PostDto>> validate = validator.validate(postDto);

        if(!validate.isEmpty()){
            validate.stream().map(ConstraintViolation::getMessage);
        }

        return Response.ok(postService.createPost(postDto)).build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam(value = "id") Long id) {
        return Response.ok(postService.getPostById(id)).build();
    }

    @PATCH
    @Path("/{id}/update")
    public Response update(@PathParam(value = "id") Long id, PostDto postDto) {
        return Response.ok(postService.updatePost(postDto, id)).build();
    }

    @DELETE
    @Path("/{id}/delete")
    public Response delete(@PathParam(value = "id") Long id) {
        return Response.ok(postService.deletePost(id)).build();
    }
}