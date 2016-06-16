var start = document.getElementById("startDate");
var end = document.getElementById("endDate");
var btn = document.getElementById("btn-valid");
btn.addEventListener("click", function() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
                if (xhttp.readyState === 4 && xhttp.status === 200) {
                        var json = JSON.parse(xhttp.responseText);
                        alert("Voici le nomber de camion"+ json.itemb.length );
                }
        };
        xhttp.open("GET", "http://localhost:8080/horaires-camions?du="+start+"&au="+end, true);
        xhttp.send();
});

