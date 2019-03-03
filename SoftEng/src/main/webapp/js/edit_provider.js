var google;
var coord;
var marker;
var clicked = false;
/*var mail = document.getElementById("mail").value;
var phone = document.getElementById("phone").value;
var site = document.getElementById("website").value;*/
document.getElementById("sear").addEventListener("click", showProv);
document.getElementById("smh").addEventListener("click", editProv);
document.getElementById("newProv").addEventListener("click", putProv);
function initMap() {
	map = new google.maps.Map(document.getElementById('map_prov'), {
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


}


var provider = new SlimSelect({
  select: '#prov' ,

});



/*var data = {
	name: document.getElementById("name").value,
	address:  document.getElementById("street").value,
	lng: coord.lat(), 
	lat: coord.lng(),
	withdrawn: 0,
	tags: tags.selected()
	
};*/


function showProv() { 
	
	var bob = providers.selected();
	
	
}

function editProv() {
	
	

}

function putProv() {
	var name = document.getElementById("name").value;
	var address = document.getElementById("street").value;

	
}
