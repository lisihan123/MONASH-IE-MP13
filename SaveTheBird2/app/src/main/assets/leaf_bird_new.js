// See post: http://asmaloney.com/2015/06/code/clustering-markers-on-leaflet-maps

var map = L.map('map').setView([-37.8, 144.96], 8);

mapboxgl.accessToken = "pk.eyJ1IjoibHd1dTAwMjEiLCJhIjoiY2tlZmYwcXR4MGsyODMzdXEyeGhlM21taiJ9.V4hkxkJ5mhH0NMCWoldlyw";


var mapboxmap = new mapboxgl.Map({
    container: 'map', // Container ID
    style: 'mapbox://styles/mapbox/streets-v11', // Map style to use
    center: [-122.25948, 37.87221], // Starting position [lng, lat]
    zoom: 12, // Starting zoom level
  });

var geocoder = new MapboxGeocoder({ // Initialize the geocoder
  accessToken: mapboxgl.accessToken, // Set the access token
  mapboxgl: mapboxgl, // Set the mapbox-gl instance
  marker: false, // Do not use the default marker style
});


var mapboxClient = mapboxSdk({ accessToken: mapboxgl.accessToken });

// Add the geocoder to the map
mapboxmap.addControl(geocoder);

L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibHd1dTAwMjEiLCJhIjoiY2tlZmYwcXR4MGsyODMzdXEyeGhlM21taiJ9.V4hkxkJ5mhH0NMCWoldlyw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox/streets-v11',
		tileSize: 512,
		zoomOffset: -1
	}).addTo( map );

var myURL = jQuery( 'script[src$="leaf_bird_new.js"]' ).attr( 'src' ).replace( 'leaf_bird_new.js', '' );

var myIcon = L.icon({
  iconUrl: myURL + 'images/pin24.png',
  iconRetinaUrl: myURL + 'images/pin48.png',
  iconSize: [58, 48],
  iconAnchor: [9, 21],
  popupAnchor: [0, -14]
});

var markerClusters = L.markerClusterGroup();

//d3.csv("./hooded.csv", function(data) {
////    console.log(data);
//    data.forEach(function(row){
//        
//        var popup = '<b>Observation date:</b> ' + row['OBSERVATION DATE'] +
//                    '<br/><b>Count of Hoodies:</b> ' + row['OBSERVATION COUNT']+
//                    '<br/><b>State:</b> ' + row['STATE'];
//                                                           
//                                                           
//        var m = L.marker( [row.LATITUDE, row.LONGITUDE], {icon: myIcon} )
//                  .bindPopup( popup );
////        
////        var m = L.circle([row.LATITUDE, row.LONGITUDE], 500, {
////            color: 'red',
////            fillColor: '#f03',
////            fillOpacity: 1
////        }).addTo(map).bindPopup("I am a circle.");
//        markerClusters.addLayer( m );
////        markerClusters.addLayer(m);
//    })
//});



$.getJSON('http://13.210.103.77/api/data', function(data) {
        

        var alldata = data['data'];
        alldata.forEach(function(row){
            
            var dateAll = row['observation_date'].split("-");
            var newformat = dateAll[1] +"/" + dateAll[2] + "/" + dateAll[0]
            mapboxClient.geocoding
                  .reverseGeocode({
                    query: [Number(row.longitude), Number(row.latitude)]
                  })
                  .send()
                  .then(response => {
                    // GeoJSON document with geocoding matches
                    var match = response.body;                
                    var popup = '<b>Observation date:</b> ' + newformat +
                    '<br/><b>Count of Hoodies:</b> ' + row['observation_count']+
                    '<br/><b>Location:</b> ' + match['features'][0]['place_name'];
                    var m = L.marker( [row.latitude, row.longitude], {icon: myIcon} )
                        .bindPopup( popup );
                    markerClusters.addLayer( m );
                  });
        })
    
});



map.addLayer( markerClusters );
