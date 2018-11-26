# Instructions For Creating User Service Class

Create user service class by following steps:

1. ( _Optional_ ) Preferably create or use existing **persistence** package 

1. Create **UserService** class and annotate class with @Controller and @Transactional annotations.
    ```
    @Controller
    @Transactional
    public class UserService { }
    ```
    
1. Create private final objects for initialization at constructor.
    1. userRepository named UserRepository object.
    1. securityController named SimpleSecurityController object.
    1. encoder named PasswordEncoder object.
       ```
       private final UserRepository userRepository;
       private final SimpleSecurityController securityController;
       private final PasswordEncoder encoder;
       ```

1. Create class constructor with userRepository, securityController and encoder parameters and 
annotate constructor with @Autowired annotation. Assign the parameters of constructor to 
to created private final objects. 
    ```
    @Autowired
    public UserService(UserRepository userRepository, SimpleSecurityController securityController, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.securityController = securityController;
        this.encoder = encoder;
    }
    ```  
    
1. Create public **getAllUsers** method with Iterable<User> return type and String username parameter.
    1. Return result of calling findAll method of userRepository object.
    ```
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
    ```
  
1. Create public **getUserByUsername** method with User return type and String username parameter.
    **P.S.:**_Use the User class created by [Create User Entity Class](/docs/en/userEntity.md) _steps for return type 
    and for the next step that already created by._ 
    ```
        public User getUserByUsername(String username) { }
    ```
    1. Create a User type result object and assign the response of calling  findByUsername method of userRepository 
    object with username parameter to the object.
        ```  
        User result = userRepository.findByUsername(username);
        ```
    1. Create an if else statement for result object is not null.
        1. Return result if result object is not null.
        1. Throw new UserNotFoundException with a message parameter.
        **P.S.:**_Use the custom UserNotFound exception created by [Create User Not Found Custom Exception Class](/docs/en/userNotFoundException.md) 
        _steps_.
        ```
        if (result != null) {
            return result;
        } else {
            throw new UserNotFoundException("[username : " + username + "] - User not found");
        }
        ```
    
1. Create public **addUser** method with User return type and User user parameter.
    ```
    public User addUser(User user) { }
    ```
    1. Create a try catch block for catching **UserNotFoundException** of Spring Security. 
        ```
        try {
        
        } catch (UserNotFoundException e) {
        
        }
        ```
        **Try statement:**
        1. Call getUserByUsername method with parameter of calling getUsername method of user object 
        and then return new User object.
            ```
            getUserByUsername(user.getUsername());
            return new User();
            ```
        **Catch Statement:**
        1. Call encode method of encoder with parameter of calling getPassword method of the user object.
        1. Call setPassword method of user object with parameter of result from the encode method of encoder call.
        1. Call save method of userRepository object with parameter of result from the setPassword method of user call.
        1. Create a savedUser named User object and assign result from the save method of userRepository call.
            ```
            User savedUser = userRepository.save(user.setPassword(encoder.encode(user.getPassword())));
            ```
        1. Add an if statement to check savedUser is not null.
            - Call add method of securityController object with 3 parameters:
                - Call getUsername method of savedUser.
                - Call getPassword method of savedUser.
                - Call getRole method of savedUser.
            ```
            if (savedUser != null) {
                securityController.add(savedUser.getUsername(), savedUser.getPassword(), savedUser.getRole());
            }
            ```
        1. Return savedUser object.
            ```
                return savedUser;
            ```
    **P.S.:** Save method of userRepository is used for saving user to our database and add method of securityController
    is used for adding the saved user to inMemoryUserDetailsManager.
    
1. Create public **delete** method with String return type and String username parameter.
    ```
    public String deleteUser(String username) { }
    ```
    1. Add an if else statement to check the user is existing by calling isUserExists method with username parameter.
        ```
        if (isUserExists(username)) {

        } else {

        }
        ```
        **If Statement:**
        1. Call findByUsername method of userRepository with parameter of username.
        1. Call getId method of result of calling findByUsername method of userRepository.
        1. Call deleteById method of userRepository with parameter of result of the calling getId method of result of 
        calling findByUsername method 
            ```
            userRepository.deleteById(userRepository.findByUsername(username).getId());
            ```
        1. Call remove method of securityController with username parameter.
            ```
            userRepository.deleteById(userRepository.findByUsername(username).getId());
            ```
        1. Return a String as success result. Any String objects can be used as success message.
            ```
            return "User deleted [username: " + username + "]";
            ``` 
        
        **Else Statement:**
        1. Return a String as fail result. Any String objects can be used as failure message.
            ```
            return "Delete user request can not proceed because of non existing user [username: " + username + "]";
            ``` 
    **P.S.:** deleteById method of userRepository is used for deleting user from our database and remove method of 
    securityController is used for removing the user from inMemoryUserDetailsManager.
        
1. Create public **initUsers** method without return type.
    ```
    public void initUsers() { }
    ```
    1. _(Optional)_ Add a console log to inform that initialization started.
        ```
        System.out.println("<----- User Initialization Started ----->");
        ```
    1. Create a for each loop for every User object in result of getAllUsers method and call add method of 
    securityController with 3 parameters:
        - Call getUsername method of User object in loop.
        - Call getPassword method of User object in loop.
        - Call getRole method of User object in loop.
        ```
        for (User u : getAllUsers()) {
            securityController.add(u.getUsername(), u.getPassword(), u.getRole());
        }
        ``` 
    1. _(Optional)_ Add a console log to inform that initialization ended.
        ```
        System.out.println("<----- User Initialization Finished ----->");
        ```
    **P.S.:** inMemoryUserDetailsManager worrks in the memory of the system that we run this spring boot application. 
    When we restart the application we got just the default admin user that defined in [WebSecurityConfig](../../src/main/java/com/security/presentatie/config/WebSecurityConfig.java).
    But the users saved in database are still available and with usage of this method we will be able to add them to 
    our inMemoryUserDetailsManager when our application starts.
 
1. Create public **isUserExists** method with boolean return type and String username parameter.
    ```
    public boolean isUserExists(String username) { }
    ```
    1. Return result of userExists method call of securityController object with username parameter.
        ```
        return securityController.userExists(username);
        ```

---
##### _Used imports:_
```
import com.security.presentatie.config.SimpleSecurityController;
import com.security.presentatie.domain.User;
import com.security.presentatie.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
```

##### Created File: [UserService.java](../../src/main/java/com/security/presentatie/persistence/UserService.java)