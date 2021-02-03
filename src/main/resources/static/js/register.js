function register() {
    
    var l = document.getElementById("login")
    var p = document.getElementById("password")
    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/api/v1/auth/register", true)
    request.setRequestHeader('Content-Type', 'application/json');
    request.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
            // document.cookie = "token="+ JSON.parse(this.responseText).token
            // console.log(document.cookie)
                console.log(this.responseText)
            }
            if (this.status == 500) {
                console.log(JSON.parse(this.responseText))
            }
            if (this.status == 400) {
                console.log(JSON.parse(this.responseText))
            }
        }
    }
    var body = {
        login: l.value,
        password: p.value
    }
    request.send(JSON.stringify(body));
}