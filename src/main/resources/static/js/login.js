function login() {
    
    var l = document.getElementById("login")
    var p = document.getElementById("password")
    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/api/v1/auth/login", true)
    request.setRequestHeader('Content-Type', 'application/json');
    request.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                document.cookie = "Authorization=" + JSON.parse(this.responseText).token
                document.cookie = "userId=" + JSON.parse(this.responseText).id
                location.replace("http://localhost:8080/profile");
            }
            if (this.status == 401) {
                alert("Неверный пароль!");
            }
            if (this.status == 404) {
                alert("Такого пользователя еще нет!");
            }
            if (this.status == 500) {
                alert(JSON.parse(this.responseText).message)
            }
        }
    }
    var body = {
        login: l.value,
        password: p.value
    }
    request.send(JSON.stringify(body));
}