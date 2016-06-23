/* global L */

var mymap = L.map('mapid').setView([45.504629,-73.55686], 13);

L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(mymap);
