# Instructions For Creating Index Html File

Create index.html file by following steps:

1. Create **index.html** file with using basic definitions `<html>, <head>, <body>` in the file at 
src/main/resources/static` folder.

1. Insert the codes below to the body part.
    ```
    <div style="text-align: center">
        <h1>Welkom naar Secured GSW Trein Applicatie</h1><br>
        <h3>Je moet eerste inloggen of een niewue account annmaken</h3><br>
        <input onclick="location.href='/login'"  type="button" value="Inloggen"/>
        <input onclick="location.href='/register'" type="button" value="Account Annmaken"/>
    </div>
    ```
    - First `<input>` is used for creating a link for login page.
    - Second `<input>` is used for creating a link for register page.

---

##### Created File: [index.html](../../../src/main/resources/static/index.html)
