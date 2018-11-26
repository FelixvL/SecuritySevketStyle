# Instructions For Creating User Not Found Custom Exception Class

Create user not found exception class by following steps:

1. ( _Optional_ ) Preferably create or use existing **exception** package 

1. Create public **UserNotFoundException** class that extends **RuntimeException**.

1. Add **@ResponseStatus** annotaton with **HttpStatus.NO_CONTENT** to class definition.
    ```
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public class UserNotFoundException extends RuntimeException { }
    ```
    - [@ResponseStatus](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html): 
    The status code is applied to the HTTP response when the handler method is invoked and overrides status 
    information set by other means.
    - [HttpStatus](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) :Hypertext Transfer Protocol (HTTP) 
    response status codes are issued by a server in response to a client's request made to the server.
        - [HttpStatus.NO_CONTENT](): The server successfully processed the request and is not returning any content. 
        Code of no content http status is **204**.

1. Create public UserNotFoundException method with String msg parameter.
    1. Add call of super from the parent with msg parameter to body of the method.
    ```
    public UserNotFoundException(String msg) {
            super(msg);
        }
    ```
    
---
##### _Used imports:_
```
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
```

##### Created File: [UserNotFoundException.java](../../src/main/java/com/security/presentatie/exception/UserNotFoundException.java)