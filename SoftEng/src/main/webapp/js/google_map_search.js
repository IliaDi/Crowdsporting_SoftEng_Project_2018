var map,infoWindow, google, marker;
var tags, shops, products , geoLat, geoLng, dateTo, dateFrom;
var coord, geoDist ;
/*
var category = new SlimSelect({
  select: '#cat_multiple' ,

});*/
document.getElementById("sear_btn").addEventListener("click", searchIT);

var tags_search = new SlimSelect({
  select: '#tags_multiple' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()
  }
});

var provider = new SlimSelect({
  select: '#provider_mult' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()

  }
});

var activities = new SlimSelect({
  select: '#activities_mult' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()

  }
});

var sprice = new SlimSelect({
  select: '#sort_price' ,
  showSearch: false

});

var sdist = new SlimSelect({
  select: '#sort_dist' ,
  showSearch: false

});

var sdate = new SlimSelect({
  select: '#sort_date' ,
  showSearch: false

});

tags = tags_search.selected(); // Will return a string or an array of string values
shops = provider.selected();
products = activities.selected();
dateFrom = document.getElementById("dateFrom").value;
dateTo = document.getElementById("dateTo").value;
geoDist = document.getElementById("geoDist").value;

/*
function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow.setContent(browserHasGeolocation ?
		'Error: The Geolocation service failed.' :
		'Error: Your browser doesn\'t support geolocation.');
	infoWindow.open(map);
}*/

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
	

	google.maps.event.addListener(map, 'click', function (event) {
		coord = event.latLng;
		placeMarker(coord);

	});


	/*// Try HTML5 geolocation.
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
*/
}

function searchIT() { window.location.href="#map"; //εδώ πρέπει να γίνει GET με τις παραπάνω παραμέτρους , μετά το αποτέλεσμα να μορφοποιηθεί στη λίστα , και στο τέλος επειδή κάθε στοιχείο της λίστας έχει και ένα edit κουμπί , να καλείται μια PATCH (γραμμένη σε αυτό το αρχείο) η οποία να παίρνει το id αυτού του list item
alert('tags\n'+tags_search.selected()+'shops\n'+provider.selected()+'products\n'+ activities.selected()+'dateFrom\n'+document.getElementById("dateFrom").value+'DateTo\n'+document.getElementById("dateTo").value + 'lat\n' + coord.lat() +'lng\n' + coord.lng() ); }







