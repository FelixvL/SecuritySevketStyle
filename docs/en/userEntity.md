# Instructions For Creating User Entity Class

Create user entity class by following steps:

1. ( _Optional_ ) Preferably create or use existing **domain** package 
1. Create **User** class.
1. Annotate class with **@Entity** annotation.
    ```
    @Entity
    public class User { 
    }
    ```
    
1. Add **id** field as Long and then add Id annotation and GeneratedValue annotation with AUTO generation strategy.
    ```
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    ```
    GenerationType.AUTO strategy used because database will going to handle value for id field. 

1. Add **username**, **password**, **role** fields as String and annotate field definition with **@NotBlank** annotation.
    - [@NotBlank](https://docs.jboss.org/hibernate/validator/4.1/api/org/hibernate/validator/constraints/NotBlank.html)
    : Validate that the annotated string is not null or empty. The difference to NotEmpty is that trailing 
    whitespaces are getting ignored.
    ```
    @NotBlank
    private String name, username, password;
    ```

1. Create empty constructor and a constructor with username, password and role parameters.
    ```
    public User(){}
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    ```
    
1. Create Getters and Setters for all of the fiels

---
##### _Used imports:_
```
import javax.persistence.*;
```
or
```
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
```

##### Created File: [User.java](../../src/main/java/com/security/presentatie/domain/User.java)