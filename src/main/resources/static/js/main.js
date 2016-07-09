/* global L */

var mymap = L.map('mapid').setView([45.504629, -73.55686], 13);

L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(mymap);


L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(mymap);

var markers = new Array();

var foodTruckIcon = L.icon({
        iconUrl: 'foodtruck.png',
        iconSize:     [40, 50],
        iconAnchor:   [20, 25],
        popupAnchor:  [0, -10]
});

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
            markerDelete();
            for (var iter = 0; iter < json.length; iter++) {
                var marker = L.marker([json[iter].geometry.coordinates[1], json[iter].geometry.coordinates[0]],
                        {icon: foodTruckIcon})
                        .on('click', addVelo)
                        .addTo(mymap)
                        .bindPopup('Nom: ' + json[iter].properties.Camion +
                                '<br> Lieu: ' + json[iter].properties.Lieu +
                                '<br> Date: ' + json[iter].properties.Date +
                                '<br> Heure de début: ' + json[iter].properties.Heure_debut +
                                '<br> Heure de fin: ' + json[iter].properties.Heure_fin)
                        .openPopup();
                markers.push(marker);
            }
        }
    };
    xhttp.open("GET", "/horaires-camions?du=" + start + "&au=" + end, true);
    xhttp.send();
});

function addVelo(e){
    mymap.setView(e.latlng, 16);
    
  //  xhttp.open("GET", "/horaires-camions?du=" + start + "&au=" + end, true);
  // xhttp.send();
}

function markerDelete() {
    for (i = 0; i < markers.length; i++) {
        mymap.removeLayer(markers[i]);
    }
}
