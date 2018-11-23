# Instructions For Creating Trein Html File

Create trein.html file by following steps:

1. Create **trein.html** file with using basic definitions `<html>, <head>, <body>` in the file at 
src/main/resources/templates` folder.

## Head
1. Insert the codes below to the head part.
    ```
    <script>
        function logout() {
            localStorage.clear();
            navigate("logout");
        }
        function navigate(url) {
            location.href = url;
        }
        function treinVersturen(){
            var api = document.getElementById("apiUrl").value;
            var merk = document.getElementById("treinMerk").value;
            var trein = '{"merk":"'+merk+'"}';
            postData(api, trein);
        }
        function postData(api, data){
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 202) {
                    document.getElementById("demo").innerHTML = this.responseText;
                }
            };
            xhttp.open("POST", "http://localhost:8082/"+api, true);
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send(data);
        }
        function getData(api){
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("demo").innerHTML = this.responseText;
                }
            };
            xhttp.open("GET", "http://localhost:8082/"+api);
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send();
        }
    </script>
    ```       
    
## Body

1. Insert the codes below to the body part.
    ```
    <div style="text-align: center; width: 100%">
        <button disabled="disabled" onclick="navigate('/trein')" >Trein Menu</button>
        <button onclick="navigate('/users')">User Menu</button>
        <br/>
        <h1 style="color: darkred">TREIN MENU</h1>
    </div>
    <hr/>
    Welkom <b>[[${#httpServletRequest.remoteUser}]]</b>
    <button onclick="logout()">Logout</button>
    <hr>
    RESULT SERVER: <div id=demo></div>
    <hr>
    TREIN:
    api: <input type=text id="apiUrl" value="api/trein" disabled="disabled"/><hr />
    treinMerk:<input type="text" id="treinMerk"/><br />
    <button onclick="treinVersturen()">Verstuur</button>
    <button onclick="getData(document.getElementById('apiUrl').value)">getData 'all treinen'</button>
    ```
---

##### Created File: [trein.html](../../../src/main/resources/templates/trein.html)
