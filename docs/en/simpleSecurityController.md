# Instructions For Creating Simple Security Controller Class

Create simple security controller by following steps:

1. ( _Optional_ ) Preferably create or use existing **config** package 

1. Create **SimpleSecurityController** class and annotate class with **@Controller** annotation.
    ```
    @Controller
    public class SimpleSecurityController {}
    ```
1. Create inMemoryUserDetailsManager named private final InMemoryUserDetailsManager object.
    ```
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    ```
1. Create class constructor with inMemoryUserDetailsManager parameter and 
annotate constructor with @Autowired annotation. Assign inMemoryUserDetailsManager parameter of constructor to 
inMemoryUserDetailsManager object. 
    ```
    @Autowired
    public SimpleSecurityController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }
    ```
1. Create public userExists method with boolean return type and String username parameter. Call the userExists method 
of inMemoryUserDetailsManager object with username parameter and return the result.  
    ```
    public boolean userExists(String username) {
            return inMemoryUserDetailsManager.userExists(username);
        }
    ```
1. Create public add method without a return type and String defined username, password and role parameters.
    1. Call the createUser method of inMemoryUserDetailsManager object with User object parameter from Spring Security. 
    **P.S.:**_org.springframework.security.core.userdetails.User_
    1. Call the withUsername method of User object with username parameter.
    1. Call the password method of User object with password parameter.
    1. Call the roles method of User object with role parameter.
    1. Call the build method.
    1. _(Optinal)_ add a console output to be able to follow when user added to inMemoryUserDetailsManager.
    ```
    public void add(String username, String password, String role) {
        inMemoryUserDetailsManager
                .createUser(
                        User.withUsername(username)
                                .password(password)
                                .roles(role)
                                .build());
        System.out.println("Add user is working for - " + username + "- password : " + password);
    }
    ```
1. Create public remove method without a return type and String defined username parameter.
    1. Call the deleteUser method of inMemoryUserDetailsManager object with username parameter. 
    1. _(Optinal)_ add a console output to be able to follow when user deleted to inMemoryUserDetailsManager.
    ```
    public void remove(String username) {
        inMemoryUserDetailsManager
                .deleteUser(username);
        System.out.println("Deleted user is working for - " + username);
    }
    ``
    
---
##### _Used imports:_
```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
```

##### Created File: [SimpleSecurityController.java](../../src/main/java/com/security/presentatie/config/SimpleSecurityController.java)