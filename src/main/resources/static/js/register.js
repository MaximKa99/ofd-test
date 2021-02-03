function register() {
    
    var l = document.getElementById("login")
    var p = document.getElementById("password")
    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/api/v1/auth/register", true)
    request.setRequestHeader('Content-Type', 'application/json');
    request.onreadystatechange = function () {
        let printError = (msg) => {
            var error = document.getElementById("error")
            error.innerHTML = msg
        }
        if (this.readyState == 4) {
            if (this.status == 200) {
                document.cookie = "Authorization=" + JSON.parse(this.responseText).token
                document.cookie = "userId=" + JSON.parse(this.responseText).id
                location.replace("http://localhost:8080/profile");
            }
            if (this.status == 500) {
                printError(JSON.parse(this.responseText).message)
            }
            if (this.status == 400) {
                printError("Такой пользователь уже зарегистрирован!");
            }
        }
    }
    var body = {
        login: l.value,
        password: p.value
    }
    request.send(JSON.stringify(body));
}