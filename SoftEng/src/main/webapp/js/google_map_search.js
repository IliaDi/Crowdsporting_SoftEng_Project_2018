var map, infoWindow;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map_search'), { 
          center: {lat: 37.987954, lng: 23.731890},
          zoom: 10
        });
        infoWindow = new google.maps.InfoWindow;
		var marker , my_lat, my_lng;

function placeMarker(location) {
  if ( marker ) {
    marker.setPosition(location);
  } else {
    marker = new google.maps.Marker({
      position: location,
      map: map
    });
  }
}
var coord;

google.maps.event.addListener(map, 'click', function(event) {
	coord = event.latLng;
	my_lat = coord.lat;
	my_lng = coord.lng;
  placeMarker(coord);
  
});


        // Try HTML5 geolocation.
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };

            placeMarker(pos);
            map.setCenter(pos);
			map.setZoom(15);
          }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
          });
        } else {
          // Browser doesn't support Geolocation
          handleLocationError(false, infoWindow, map.getCenter());
        }
		
      }

      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                              'Error: The Geolocation service failed.' :
                              'Error: Your browser doesn\'t support geolocation.');
        infoWindow.open(map);
      }