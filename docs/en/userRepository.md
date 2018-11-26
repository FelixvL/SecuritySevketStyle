# Instructions For Creating User CRUD Repository Interface

Create user repository interface by following steps:

1. ( _Optional_ ) Preferably create or use existing **persistence** package 

1. Create **UserRepository** interface.

1. Extend **CrudRepository<User, Long>** in interface definition.
    ``` 
    public interface UserRepository extends CrudRepository<User, Long>
    ```

1. Add a query method for finding users by username.
    ```
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    ```
    GenerationType.AUTO strategy used because database will going to handle value for id field. 

1. Add **username**, **password**, **role** fields as String.
    ```
    User findByUsername(String username);
    ```

---
##### _Used imports:_
```
import com.security.presentatie.domain.User;
import org.springframework.data.repository.CrudRepository;
```

##### Created File: [UserRepository.java](../../src/main/java/com/security/presentatie/persistence/UserRepository.java)