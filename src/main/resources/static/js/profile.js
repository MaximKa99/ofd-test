function getUser() {
    var request = new XMLHttpRequest();

    var id;

    var cookies = document.cookie.split(";");
    for(var i=0; i<cookies.length; i++){
 
        var parts = cookies[i].split("=")
        if (parts[0] == " userId") {
            id = parts[1]
        }
    }
    
    request.open("GET", "http://localhost:8080/api/v1/user/" + id, true)
    request.setRequestHeader('Content-Type', 'application/json');
    request.onreadystatechange = function () {
        if (this.status == 200) {
            var balance = JSON.parse(this.responseText).balance
            var login = JSON.parse(this.responseText).login
            document.getElementById("balance").innerHTML = balance
            document.getElementById("login").innerHTML = login
        }
        if (this.status == 500) {
            alert(JSON.parse(this.responseText).message)
        }
    }
    request.send();
}

getUser()