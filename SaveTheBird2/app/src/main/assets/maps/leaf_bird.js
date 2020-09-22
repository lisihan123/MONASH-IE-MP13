// See post: http://asmaloney.com/2015/06/code/clustering-markers-on-leaflet-maps

var map = L.map('map').setView([-37.8, 144.96], 8);

L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibHd1dTAwMjEiLCJhIjoiY2tlZmYwcXR4MGsyODMzdXEyeGhlM21taiJ9.V4hkxkJ5mhH0NMCWoldlyw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox/streets-v11',
		tileSize: 512,
		zoomOffset: -1
	}).addTo( map );

var myURL = jQuery( 'script[src$="leaf_bird.js"]' ).attr( 'src' ).replace( 'leaf_bird.js', '' );

var myIcon = L.icon({
  iconUrl: myURL + 'images/pin24.png',
  iconRetinaUrl: myURL + 'images/pin48.png',
  iconSize: [29, 24],
  iconAnchor: [9, 21],
  popupAnchor: [0, -14]
});

var markerClusters = L.markerClusterGroup();

d3.csv("./hooded.csv", function(data) {
    console.log(data);
    data.forEach(function(row){
        
        var popup = '<br/><b>Observation Date:</b> ' + row['OBSERVATION DATE'] +
                    '<br/><b>Observation Count:</b> ' + row['OBSERVATION COUNT'];
                                                           
                                                           
        var m = L.marker( [row.LATITUDE, row.LONGITUDE], {icon: myIcon} )
                  .bindPopup( popup );
//        
//        var m = L.circle([row.LATITUDE, row.LONGITUDE], 500, {
//            color: 'red',
//            fillColor: '#f03',
//            fillOpacity: 1
//        }).addTo(map).bindPopup("I am a circle.");
        markerClusters.addLayer( m );
//        markerClusters.addLayer(m);
    })
});



//for ( var i = 0; i < markers.length; ++i )
//{
//  var popup = markers[i].name +
//              '<br/>' + markers[i].city +
//              '<br/><b>IATA/FAA:</b> ' + markers[i].iata_faa +
//              '<br/><b>ICAO:</b> ' + markers[i].icao +
//              '<br/><b>Altitude:</b> ' + Math.round( markers[i].alt * 0.3048 ) + ' m' +
//              '<br/><b>Timezone:</b> ' + markers[i].tz;
//
//  var m = L.marker( [markers[i].lat, markers[i].lng], {icon: myIcon} )
//                  .bindPopup( popup );
//
//  markerClusters.addLayer( m );
//}

map.addLayer( markerClusters );
