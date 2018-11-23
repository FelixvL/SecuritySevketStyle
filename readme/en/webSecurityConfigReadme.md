# Instructions For Creating WebSecurityConfig Class

Create WebSecurityConfig class for setting the security configuration by following steps:

1. ( _Optional_ ) Preferably create or use existing **config** package 
1. Create **WebSecurityConfig** class
1. Extend **WebSecurityConfigurerAdapter** in class definition and annotate class with **@Configuration**, 
**@EnableWebSecurity** and **@EnableGlobalMethodSecurity** with prePostEnabled parameter true assigned.
    ``` 
    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    ```
    - [@Configuration](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html)
    : Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to 
    generate bean definitions and service requests for those beans at runtime.
    - [@EnableWebSecurity](https://docs.spring.io/spring-security/site/docs/4.2.5.RELEASE/apidocs/org/springframework/security/config/annotation/web/configuration/EnableWebSecurity.html) 
    Add this annotation to an @Configuration class to have the Spring Security configuration defined in any 
    WebSecurityConfigurer or more likely by extending the WebSecurityConfigurerAdapter base class and overriding 
    individual methods.
    - [@EnableGlobalMethodSecurity](https://docs.spring.io/spring-security/site/docs/4.2.6.RELEASE/apidocs/org/springframework/security/config/annotation/method/configuration/EnableGlobalMethodSecurity.html) 
    Enables Spring Security global method security.
        - **prePostEnabled:** Determines if Spring Security's pre post annotations should be enabled.

1. Write a public **encoder** method with PasswordEncoder return type and annotate method with **@Bean** annotation. Add 
return new BCryptPasswordEncoder to method.
    ```
    @Bean
        public PasswordEncoder encoder() {
            return new BCryptPasswordEncoder();
        }
    ```
    - [@Bean](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Bean.html)
    : Indicates that a method produces a bean to be managed by the Spring container.
    - [BCryptPasswordEncoder](https://docs.spring.io/spring-security/site/docs/4.2.7.RELEASE/apidocs/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html)
    : Implementation of PasswordEncoder that uses the BCrypt strong hashing function.

1. Write a public **inMemoryUserDetailsManager** method with **InMemoryUserDetailsManager** return type. There are 2 different ways to write this method.
   1. _Without_ a user definition:
       ```
       @Bean
       public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
           return new InMemoryUserDetailsManager(new Properties());
       }
       ```
   1. _With_ a default user definition:
       ```
       @Bean
       public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
           final Properties users = new Properties();
           users.put("username",encoder().encode("password") + ",ROLE_ADMIN,enabled"); //adding for default jedimaster account
           return new InMemoryUserDetailsManager(users);
       }
       ```
       Multiple users can added by repeating adding users to object.
       - **username**: Username for the user.
       - **password**: Password for the user. Used encoder method to instantiate password encoder. We have to encrypt
       our password by this way. We will fully automate password encoding and decoding except of this manual user adding.
       For more information about encrypted encoders read [DelegatingPasswordEncoder](https://docs.spring.io/spring-security/site/docs/4.2.7.RELEASE/apidocs/org/springframework/security/crypto/password/DelegatingPasswordEncoder.html).
       - **role**: Role for the user. "ROLE_" is a prefix. _Ex: "ROLE_ANOTHER" used to give "ANOTHER" role to a user_
       - **isEnabled**: Value used for defining user account **enabled** or **disabled**. We use all accounts as enabled 
       in this project because we won't going to use a verification at user creation.
       _Ex: On e-mail verified sign up systems we create user account as disabled until user completes verification._
   
1. Write a public **authProvider** method with **[DaoAuthenticationProvider](https://docs.spring.io/spring-security/site/docs/4.2.8.RELEASE/apidocs/org/springframework/security/authentication/dao/DaoAuthenticationProvider.html)** 
return type and annotate with @Bean annotation:
    1. Create **authProvider** named new DaoAuthenticationProvider object.
    1. Set user details service of created authProvider object by using created inMemoryUserDetailsManager method.
    1. Set password encoder of authProvider by using encoder method.
    1. Return authProvider object.
    ```
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(inMemoryUserDetailsManager());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    ```
    This method is used to assign the inMemoryUserDetailsManager as user details service.
    
1. Write an overriding protected **configure** method with **AuthenticationManagerBuilder** parameter and no return type:
   ```
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(inMemoryUserDetailsManager());
   }
   ```
   - **@Override** annotation is used in Spring to annotate that following method is an overriding method.
   - This method is used to assign the inMemoryUserDetailsManager as user details service.
   


1. Write an overriding protected **configure** method with **HttpSecurity** parameter and no return type:
    ```
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/register", "/api/user/add").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
                .defaultSuccessUrl("/trein", true)
            .and()
            .httpBasic()
            .and()
            .logout()
                .logoutUrl("/logout").permitAll()
                .clearAuthentication(true)
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
    ```
    - **csrf** ([Cross-Site Request Forgery](https://www.owasp.org/index.php/Cross-Site_Request_Forgery_(CSRF))) is an protection system but in this presentation we will not going to use it.
    - **antMatchers** are used to writing specific rules for defined **antPatterns**. "/", "/register" and ""/api/user/add""
    used as antPatterns in this presentation. antMatchers tries to math http request url mapping with antPatterns. Most 
    of the time users needs to be able to get to main page, register page and add user endpoint without logging in.
        
        If they match then applies the following rule we defined. 
        ``` 
        permitAll, denyAll, authenticated, hasAuthority, hasAnyAuthority, hasRole and hasAnyRole 
        ```
        are most used basic rules. 
    - **anyRequest** is used to defining rules for any requests that is not matching with the ant matchers. 
      Following role will be applied to anyRequest.
    - **formLogin** is used to define the url path of login page and settings for login. 
    - **logout** is used to define the url path of logout page and settings for logut. 
    

---
##### _Used imports:_
```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Properties;
```

##### Created File: [WebSecurityConfig.java](../../src/main/java/com/security/presentatie/config/WebSecurityConfig.java)