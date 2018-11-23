# Instructions For Updating Jersey Config Class

Update jersey config class by registering UserEndpoint.class

`register(UserEndpoint.class);`

## After Update:
```
@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig(){
		register(TreinEndpoint.class);
		register(UserEndpoint.class);
	}
}
```

---
##### _Used imports:_
```
import javax.ws.rs.ApplicationPath;

import com.security.presentatie.api.TreinEndpoint;
import com.security.presentatie.api.UserEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
```

##### Updated File: [JerseyConfig.java](../../src/main/java/com/security/presentatie/config/JerseyConfig.java)
