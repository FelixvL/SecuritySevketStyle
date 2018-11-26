# Instructions For Creating MVC Config Class

Create mvc ig class by following steps:

1. ( _Optional_ ) Preferably create or use existing **config** package 

1. Create public **MvcConfig** class that implements **WebMvcConfigurer** and annotate class definition with 
**@Configuration** annotation.
    ```
    @Configuration
    public class MvcConfig implements WebMvcConfigurer {
    }
    ```

1. Create public **addViewControllers** method without return type and `ViewControllerRegistry registry` parameter.
    ``` 
    public void addViewControllers(ViewControllerRegistry registry) {
    }
    ```

    1. Add view controllers and set view names:
        - Add `/login` path and set view name as `login`
        - Add `/trein` path and set view name as `trein`
        - Add `/users` path and set view name as `users`
        - Add `/register` path and set view name as `register`
        ```
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/trein").setViewName("trein");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/register").setViewName("register");
        ```
        _**P.S.:** by using view controllers we can use dynamic templates and and access pages with url path that added._
        
        _**Ex:** Able to use `http://www.example.com/users` to access `users.html` page at `http://www.example.com` domain._

---
##### _Used imports:_
```
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
```

##### Created File: 
[MvcConfig.java](../../src/main/java/com/security/presentatie/config/MvcConfig.java)