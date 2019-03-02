var map;
var infoWindow;
var google;
var tags;
var shops;
var category = new SlimSelect({
  select: '#cat_multiple' ,

});

var tags_search = new SlimSelect({
  select: '#tags_multiple' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()

    // Optional - Return a valid data object. See methods/setData for list of valid options
    return {
      text: value,
      value: value.toLowerCase()
    }
  }
});

var provider = new SlimSelect({
  select: '#provider' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()

    // Optional - Return a valid data object. See methods/setData for list of valid options
    return {
      text: value,
      value: value.toLowerCase()
    }
  }
});


tags = tags_multiple.selected(); // Will return a string or an array of string values
shops = provider.selected();

var marker, geoLat, geoLng;

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow.setContent(browserHasGeolocation ?
		'Error: The Geolocation service failed.' :
		'Error: Your browser doesn\'t support geolocation.');
	infoWindow.open(map);
}

function initMap() {
	map = new google.maps.Map(document.getElementById('map_search'), {
		center: {
			lat: 37.987954,
			lng: 23.731890
		},
		zoom: 10
	});
	infoWindow = new google.maps.InfoWindow;
	

	function placeMarker(location) {
		if (marker) {
			marker.setPosition(location);
		} else {
			marker = new google.maps.Marker({
				position: location,
				map: map
			});
		}
	}
	var coord;

	google.maps.event.addListener(map, 'click', function (event) {
		coord = event.latLng;
		geoLat = coord.lat;
		geoLng = coord.lng;
		placeMarker(coord);

	});


	// Try HTML5 geolocation.
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function (position) {
			var pos = {
				lat: position.coords.latitude,
				lng: position.coords.longitude
			};

			placeMarker(pos);
			map.setCenter(pos);
			map.setZoom(15);
		}, function () {
			handleLocationError(true, infoWindow, map.getCenter());
		});
	} else {
		// Browser doesn't support Geolocation
		handleLocationError(false, infoWindow, map.getCenter());
	}

}


