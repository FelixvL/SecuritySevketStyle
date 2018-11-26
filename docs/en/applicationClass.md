# Instructions For Updating Spring Boot Application Class

Spring boot application class is the class that contains **main** method of the application.
Update spring boot application class by following steps:

1. Create CommandLineRunner type **init** method with `UserService userService` parameter and annotate method with 
**@Bean** annotation.

1. Return a lambda for `(args)` to call initUsers of userService object that created in 
[Create User Service Class](/docs/en/userService.md) steps.

```
@Bean
CommandLineRunner init(UserService userService) {
    return (args) -> userService.initUsers();
}
```

## After Update:

_**Attention:** Class name can be different on different projects/_

```
@SpringBootApplication
public class VeiligheidPresentatieApplicatie {

    public static void main(String[] args) {
        SpringApplication.run(VeiligheidPresentatieApplicatie.class, args);
    }

    @Bean
    CommandLineRunner init(UserService userService) {
        return (args) -> userService.initUsers();
    }
}
```

---
##### _Used imports:_
```
import com.security.presentatie.persistence.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
```

##### Updated File: [VeiligheidPresentatieApplicatie.java](../../src/main/java/com/security/presentatie/VeiligheidPresentatieApplicatie.java)
