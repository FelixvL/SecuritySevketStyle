package com.security.presentatie.api;

import com.security.presentatie.domain.User;
import com.security.presentatie.persistence.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@CrossOrigin("*")
@Path("/user")
@Controller
public class UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity getUserByUsername(@PathParam("username") @NotBlank String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GET
    @Path("/exist/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity isUserExist(@PathParam("username") @NotBlank String username) {
        return ResponseEntity.ok(userService.isUserExists(username));
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity addUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @DELETE
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity deleteUser(@PathParam("username") @NotBlank String username) {
        return ResponseEntity.ok(userService.deleteUser(username));
    }

/*  This method can be used for creating an endpoint for sending registration form data
    We did't used this endpoint in this project

    @POST
    @Path("/add-user-form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity addUserByFormInput(@RequestBody @FormParam(value = "username") String username,
                                             @FormParam(value = "password") String password,
                                             @FormParam(value = "role") String role) {
        return ResponseEntity.ok(userService.addUser(new User(username, password, role)));
    }
*/

}
