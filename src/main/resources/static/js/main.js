var start = document.getElementById("startDate");
var end = document.getElementById("endDate");
var btn = document.getElementById("btn-valid");
btn.addEventListener("click", function(e) {
  e.preventDefault();
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
                if (xhttp.readyState === 4 && xhttp.status === 200) {
                        var json = JSON.parse(xhttp.responseText);
                        alert("Voici le nomber de camion" + json.length );
                }
        };
        console.log( "/horaires-camions?du="+start.value+"&au="+end.value);
        xhttp.open("GET", "/horaires-camions?du="+start.value+"&au="+end.value, true);
        xhttp.send();
});

