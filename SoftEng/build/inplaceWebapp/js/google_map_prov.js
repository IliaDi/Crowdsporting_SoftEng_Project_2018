var google;
var coord;
var marker;
var clicked = false;
/*var mail = document.getElementById("mail").value;
var phone = document.getElementById("phone").value;
var site = document.getElementById("website").value;*/

document.getElementById("newProv").addEventListener("click", validateProvForm);
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


var tags_provider = new SlimSelect({
  select: '#tags_prov' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()
  }
});



/*var data = {
	name: document.getElementById("name").value,
	address:  document.getElementById("street").value,
	lng: coord.lat(), 
	lat: coord.lng(),
	withdrawn: 0,
	tags: tags.selected()
	
};*/

function createProv() { 
	//alert("bob");
	var name = document.getElementById("name").value;
	var address = document.getElementById("street").value;
	var tags = tags_provider.selected();


	var alltags = "";
	  tags.forEach(function(tag){
	    alltags = alltags + "&tags=" + tag;
	  });

	fetch('/observatory/api/shops', {
	    method: 'POST',
	    headers: {
            "Content-Type": "application/json",
            // "Content-Type": "application/x-www-form-urlencoded",
        },
	    body: "name=" +name +"&address=" + address +"&lng=" + coord.lng() + "&lat=" + coord.lat() + alltags
	  })
	  .then(function(response) {
	    if(response.status==200) return response.json();
	    throw new Error("HTTP error, status = " + response.status);
	  })
	  .then(function(product) {
	    console.log('all good ');
	  })
	  .catch(function (error) {
	   	alert(error) ;
	  });

	//alert("Hello! I am an alert box!!"+alltags + address); */
}

function validateProvForm() {	
			var nameentry=document.getElementById("name").value;
			var streetentry=document.getElementById("street").value;
			if(nameentry=="" || streetentry=="") {
				alert("Please fill both name and address fields!");
				return false; }
			else if(clicked!=true) {
				alert("Please pin the location on the map!"); 
				return false; }
			else { 
				createProv();
				return true;
			}
}
