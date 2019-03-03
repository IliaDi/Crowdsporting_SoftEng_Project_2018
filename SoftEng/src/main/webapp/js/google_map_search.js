var map,infoWindow, google, marker;
var tags, shops, products , geoLat, geoLng, dateTo, dateFrom;
var coord, geoDist ;
var clicked = false;
/*
var category = new SlimSelect({
  select: '#cat_multiple' ,

});*/
document.getElementById("sear_btn").addEventListener("click", validateForm);

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
		clicked = true;

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

function searchIT() { 
window.location.href="#map"; 
let tags= tags_search.selected(); 
let shopsprovider.selected(); 
let activities.selected(); 
let dateFrom = document.getElementById("dateFrom").value;
let dateTo = document.getElementById("dateTo").value; 
let myLat = coord.lat();
let myLng = coord.lng() ); 


   
}

function validateForm() {	
			var start=document.getElementById("dateFrom").value;
			var end=document.getElementById("dateTo").value;
			var maxdist = document.getElementById("geoDist").value;
			if((start!="" && end=="") ||  (start=="" && end!="")) {
				alert("Please fill both start and finish dates!");
				return false; }
			else if(clicked==true && maxdist =="") {
				alert("Please fill out max distance from position!"); 
				return false; }
			else {
				searchIT();
				return true;
			}
}


var google;

function init() {
    // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
    var myLatlng = new google.maps.LatLng(37.987954, 23.731890);

    
    var mapOptions = {
        zoom: 10,
        center: myLatlng,
        scrollwheel: false,
        styles: [{"featureType":"administrative.land_parcel","elementType":"all","stylers":[{"visibility":"off"}]},{"featureType":"landscape.man_made","elementType":"all","stylers":[{"visibility":"off"}]},{"featureType":"poi","elementType":"labels","stylers":[{"visibility":"off"}]},{"featureType":"road","elementType":"labels","stylers":[{"visibility":"simplified"},{"lightness":20}]},{"featureType":"road.highway","elementType":"geometry","stylers":[{"hue":"#f49935"}]},{"featureType":"road.highway","elementType":"labels","stylers":[{"visibility":"simplified"}]},{"featureType":"road.arterial","elementType":"geometry","stylers":[{"hue":"#fad959"}]},{"featureType":"road.arterial","elementType":"labels","stylers":[{"visibility":"off"}]},{"featureType":"road.local","elementType":"geometry","stylers":[{"visibility":"simplified"}]},{"featureType":"road.local","elementType":"labels","stylers":[{"visibility":"simplified"}]},{"featureType":"transit","elementType":"all","stylers":[{"visibility":"off"}]},{"featureType":"water","elementType":"all","stylers":[{"hue":"#a1cdfc"},{"saturation":30},{"lightness":49}]}]
    };

    var mapElement = document.getElementById('map');

    var map = new google.maps.Map(mapElement, mapOptions);
    
    
}
google.maps.event.addDomListener(window, 'load', init);







