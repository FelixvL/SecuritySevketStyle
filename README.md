# Basic Spring Security Presentation

Please first follow and complete the steps in [GSWPresentatie](https://github.com/FelixvL/GSWPresentatie). 

In this presentation we assume that you have already followed and finished **GSWPresentatie** successfully.

#### We will going to use :
- Spring Basic Security


## How to secure our Spring Application
1. Add spring-boot-starter-security and spring-boot-starter-thymeleaf dependencies to pom.xml
    ```
    <dependencies>
        ..... Other dependencies
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
    <dependencies>
    ```
1. [Create WebSecurityConfig Class](readme/en/webSecurityConfigReadme.md)
1. [Create Simple Security Controller Class](/readme/en/simpleSecurityController.md)
1. [Create User Not Found Custom Exception Class](/readme/en/userNotFoundException.md)
1. [Create User Entity Class](readme/en/userEntity.md)
1. [Create User Repository Interface](readme/en/userRepository.md)
1. [Create User Service Class](/readme/en/userService.md)
1. [Create User Endpoint Class](/readme/en/userEndpoint.md)
1. [Update Jersey Config Class](/readme/en/jerseyConfig.md)
1. [Update Spring Boot Application Class](/readme/en/applicationClass.md)
1. [Create index.html File](/readme/en/frontend/indexHtml.md)
1. [Create register.html File](/readme/en/frontend/registerHtml.md)
1. [Create users.html File](/readme/en/frontend/usersHtml.md)
1. [Create trein.html File](/readme/en/frontend/treinHtml.md)
1. [Create Mvc Config Class](/readme/en/MvcConfig.md)
