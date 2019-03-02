var google;
var name = document.getElementById("name").value;
var address = document.getElementById("street").value;
/*var mail = document.getElementById("mail").value;
var phone = document.getElementById("phone").value;
var site = document.getElementById("website").value;*/

document.getElementById("newProv").addEventListener("click", createProv());
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

	});


}

var tags = new SlimSelect({
  select: '#tags_prov' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()
  }
});

var provTags = tags.selected();

var data = {
	name: document.getElementById("name").value,
	address:  document.getElementById("street").value,
	lng: coord.lat(), 
	lat: coord.lng(),
	withdrawn: 0,
	tags: tags.selected()
	
};

function createProv() {
		fetch('/observatory/api/products', {
    method: 'POST',
    body: JSON.stringify(data)
  })
  .then(function(response) {
    if(response.ok) return response.json();
    throw new Error("HTTP error, status = " + response.status);
  })
  .then(function(product) {
    console.log('all good ');
  })
  .catch(function (error) {
    console.log('Request failure: ', error);
  });
	
}


