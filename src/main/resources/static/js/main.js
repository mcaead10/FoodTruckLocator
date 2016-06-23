document.getElementById("btn-valid").addEventListener("click", function (e) {
    e.preventDefault();
    var start = document.getElementById("startDate").value;
    var end = document.getElementById("endDate").value;

    if (!start || !end)
    {
        alert("Format de date incorect");
        return;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var json = JSON.parse(xhttp.responseText);
            console.log("Voici le nomber de camion: " + json.length);
        }

    };
    xhttp.open("GET", "/horaires-camions?du=" + start + "&au=" + end, true);
    xhttp.send();
});
