# Instructions For Creating User Endpoint Class

Create user endpoint class by following steps:

1. ( _Optional_ ) Preferably create or use existing **api** package 

1. Create **UserEndpoint** class and annotate class and annotate class with: 
    - [@CrossOrigin](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/CrossOrigin.html) 
    annotation with Wild Card as string parameter.
    - [@Path](https://docs.oracle.com/cd/E19776-01/820-4867/ggqny/index.html) annotation with the parameter of name of the path going to used as parent for all of the endpoints 
    in the UserEndpoint class.
    - **@Controller** annotation.
    ```
    @CrossOrigin("*")
    @Path("/user")
    @Controller
    public class UserEndpoint { }
    ```
    
1. Create private final userService named UserService object for initialization at constructor.
   ```
   private final UserService userService;
   ```

1. Create class constructor with `Userservice userService` parameter and annotate constructor with **@Autowired** annotation. 
Assign the parameter of constructor to to created private final object. 
    ```
    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }
    ```  
   
1. Create public **getAllUsers** method ResponseEntity return type and annotate class with:
    - **@GET** annotation.
    - **@Path** annotation with `"/all"` parameter.
    - **@Produces** annotation with `MediaType.APPLICATION_JSON` parameter.
        ```
        @GET
        @Path("/all")
        @Produces(MediaType.APPLICATION_JSON)
        public ResponseEntity getAllUsers() {}
        ```
    1. Return a **ResponseEntity** object with `ok` status and use result of getAllUsers method call of 
    userService as parameter.
        ```
        return ResponseEntity.ok(userService.getAllUsers());
        ```
           
1. Create public **getUserByUsername** method with ResponseEntity return type, `String username` parameter and annotate 
class and parameter with:

    **Class Annotations:**
    - **@GET** annotation.
    - **@Path** annotation with `"/{username}"` parameter. 
    - **@Produces** annotation with `MediaType.APPLICATION_JSON` parameter.

    **Parameter Annotations:**
    - **@PathParam("username)** annotation used to read `{username}` part of the url and assign the value to 
    username parameter.
    - **@NotBlank** annotation is a combination of checking for a string is not null and trimmed length of string 
    is bigger than 0. _(**ex:** @NotBlank does the same with if(string != null && string.trim() > 0))_
        
        ```
        @GET
        @Path("/{username}")
        @Produces(MediaType.APPLICATION_JSON)
        public ResponseEntity getUserByUsername(@PathParam("username") @NotBlank String username) {
        
        }
        ```
    1. Return a **ResponseEntity** object with `ok` status and use result of getUserByUsername method call of 
    userService with username as parameter.
        ```
        return ResponseEntity.ok(userService.getUserByUsername(username));
        ```   
                
1. Create public **isUserExist** method with ResponseEntity return type, `String username` parameter and annotate 
class and parameter with:

    **Class Annotations:**
    - **@GET** annotation.
    - **@Path** annotation with `"/exist/{username}"` parameter. 
    - **@Produces** annotation with `MediaType.APPLICATION_JSON` parameter.

    **Parameter Annotations:**
    - **@PathParam("username)** annotation used to read `{username}` part of the url and assign the value to 
    username parameter.
    - **@NotBlank** annotation is a combination of checking for a string is not null and trimmed length of string 
    is bigger than 0. _(**ex:** @NotBlank does the same with if(string != null && string.trim() > 0))_
        
        ```
        @GET
        @Path("/exist/{username}")
        @Produces(MediaType.APPLICATION_JSON)
        public ResponseEntity isUserExist(@PathParam("username") @NotBlank String username) {
            
        }
        ```
    1. Return a **ResponseEntity** object with `ok` status and use result of isUserExists method call of 
    userService with username as parameter.
        ```
        return ResponseEntity.ok(userService.isUserExists(username));
        ```
    
1. Create public **addUser** method with ResponseEntity return type, `User user` parameter and annotate 
class and parameter with:

    **Class Annotations:**
    - **@POST** annotation.
    - **@Path** annotation with `"/add"` parameter. 
    - **@Consumes** annotation with `MediaType.APPLICATION_JSON` parameter.
    - **@Produces** annotation with `MediaType.APPLICATION_JSON` parameter.

    **Parameter Annotations:**
    - **@RequestBody** annotation is used for getting user object from request body.
    - **@Valid** annotation is used for validating requested User object is a valid User object.
        
        ```
        @POST
        @Path("/add")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public ResponseEntity addUser(@RequestBody @Valid User user) {
            
        }
        ```
    1. Return a **ResponseEntity** object with `ok` status and use result of addUser method call of 
    userService with user object as parameter.
        ```
        return ResponseEntity.ok(userService.addUser(user));
        ``` 
           
1. Create public **deleteUser** method with ResponseEntity return type, `String username` parameter and annotate 
class and parameter with:

    **Class Annotations:**
    - **@DELETE** annotation.
    - **@Path** annotation with `"/{username}"` parameter. 
    - **@Produces** annotation with `MediaType.APPLICATION_JSON` parameter.

    **Parameter Annotations:**
    - **@PathParam("username")** annotation.
    - **@NotBlank** annotation.
        
        ```
        @DELETE
        @Path("/{username}")
        @Produces(MediaType.APPLICATION_JSON)
        public ResponseEntity deleteUser(@PathParam("username") @NotBlank String username) {
            return ResponseEntity.ok(userService.deleteUser(username));
        }
        ```
    1. Return a **ResponseEntity** object with `ok` status and use result of deleteUser method call of 
    userService with username as parameter.
        ```
        return ResponseEntity.ok(userService.deleteUser(username));
        ```
    
---
##### _Used imports:_
```
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
```

##### Created File: [UserEndpoint.java](../../src/main/java/com/security/presentatie/api/UserEndpoint.java)