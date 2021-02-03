var token;

function authentificate() {
    
    var l = document.getElementById("login")
    var p = document.getElementById("password")
    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/api/v1/auth/login", true)
    request.setRequestHeader('Content-Type', 'application/json');
    request.onreadystatechange = function () {
        if (this.status == 200) {
            document.cookie = "Authorization=" + JSON.parse(this.responseText).token
            document.cookie = "userId=" + JSON.parse(this.responseText).id
            location.replace("http://localhost:8080/profile");
        }
        if (this.status == 500) {
            console.log(JSON.parse(this.responseText))
        }
    }
    var body = {
        login: l.value,
        password: p.value
    }
    request.send(JSON.stringify(body));
}