package org.bruno.user.infrastructure.web;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bruno.user.application.UserService;
import org.bruno.user.application.input.UserInput;
import org.bruno.user.application.response.UserResponse;
import org.bruno.user.infrastructure.web.request.UserRequest;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  private final UserService userService;

  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @POST
  public Response save(UserRequest request) {
    UserInput input = request.toInput();
    userService.save(input);
    return Response.status(Response.Status.CREATED).build();
  }

  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") Long id) {
    UserResponse resp = userService.getById(id);
    return Response.ok(resp).build();
  }
}
