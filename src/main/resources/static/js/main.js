var start = document.getElementById("startDate").value;
var end = document.getElementById("endDate").value;
var btn = document.getElementById("btn-valid");
btn.addEventListener("click", function (e) {
    e.preventDefault();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (!start || !end)
        {
            alert("Format de date incorect");
        } else {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                var json = JSON.parse(xhttp.responseText);
                alert("Voici le nomber de camion" + json.length);
            }
        }
    };
    xhttp.open("GET", "/horaires-camions?du=" + start + "&au=" + end, true);
    xhttp.send();
});

