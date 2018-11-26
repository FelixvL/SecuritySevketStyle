# Instructions For Creating Register Html File

Create register.html file by following steps:

1. Create **register.html** file with using basic definitions `<html>, <head>, <body>` in the file at 
src/main/resources/templates` folder.

## Head
1. Insert the codes below to the head part.
    ```
    <script>
        function addUser() {
            alert("add user called");
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var role = document.getElementById("role").value;
            if (username && password && role){
                var data = '{"username":"'+username+'", "password":"'+password+'", "role":"'+role+'"}';
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    alert(this.readyState + " - " + this.status);
                    if (this.readyState === 4 && this.status === 200) {
                        if(JSON.parse(this.responseText).body.username) {
                            var user = JSON.parse(this.responseText).body;
                            document.getElementById("result").innerText = user.username + " added successfully"
                        } else {
                            document.getElementById("result").innerText = "User not added";
                        }
                    }
                };
                xhttp.open("POST", "http://localhost:8082/api/user/add");
                xhttp.setRequestHeader("Content-type", "application/json");
                xhttp.send(data);
            } else {
                document.getElementById("header").innerText = "Please fill all the fields";
            }
        }
    </script>
    ```
    **addUser Method:**
    1. Reads the values for `username, password` and `role` from the input fields at the file body.
        ```
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var role = document.getElementById("role").value;
        ``` 
    1. Checks for all of the vales are assigned.
        ```
        if (username && password && role)
        ```
        **If username, password and role variables are assigned:** 
        1. Creates a JSON formatted string data for sending with request.
            ```
            var data = '{"username":"'+username+'", "password":"'+password+'", "role":"'+role+'"}';
            ```
        1. Sets request request type and request url.
            ```
            xhttp.open("POST", "http://localhost:8082/api/user/add");
            ```
        1. Sets request request header for content type of JSON data.
            ```
            xhttp.setRequestHeader("Content-type", "application/json");
            ```
        1. Sends request with JSON data as request body.
            ```
            xhttp.send(data);
            ```
        1. Checks for the response of the request _(http status codes)_.
            ```
            if (this.readyState === 4 && this.status === 200)
            ```
            **If Response is successful:**
            - Checks username field of the User object that return inside response body is not null.
                ```
                if(JSON.parse(this.responseText).body.username)
                ```
                **If username is not null:**
                - Changes the value of `header` named `<h2>` object in the body with a success message.
                    ```
                    document.getElementById("result").innerText = JSON.parse(this.responseText).body.username + " added successfully"
                    ```
                **If username is null: (Else)**
                - Changes the value of `header` named `<h2>` object in the body with a fail message.
                    ```
                    document.getElementById("result").innerText = "User not added";
                    ```
                
        **If one of the username, password and role variables are not assigned: (Else)** 
        1. Changes the value of `header` named `<h2>` object in the body with a warning message.
            ```
            document.getElementById("header").innerText = "Please fill all the fields";
            ```    
    
## Body

1. Insert the codes below to the body part.
    ```
    <h2 id="header">Add User</h2>
    <div id="result"></div>
    User:     <input id="username" type='text' name='username'><br/>
    Password: <input id="password" type='password' name='password' /> <br/>
    Role:     <input id="role" type='text' name='role' /> <br/>
    <button name="addButton" onclick="addUser()">Add User</button>
    <button name="mainPage" onclick="location.href='/'">Main Page</button>
    <hr/>
    ```
    - `<input id="username" type='text' name='username'/>` creates input field for username.
    - `<input id="password" type='password' name='password'/>`creates input field for password.
    - `<input id="role" type='text' name='role'/>` creates input field for role.
    - `<button name="addButton" onclick="addUser()">` adds button for adding user. On click it calls addUser JavaScript function 
    from the head part of the page.
    - `<button name="mainPage" onclick="location.href='/'">` adds button for going back to main page. 

---

##### Created File: [register.html](../../../src/main/resources/templates/register.html)
