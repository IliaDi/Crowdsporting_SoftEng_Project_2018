var google;
var coord;
var marker;
var clicked = false;
var myid;
var loo;
var laa;
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
		loo=event.latLng.lng();
		laa=event.latLng.lat();
		placeMarker(coord);
		clicked = true;
	});


}


var provider = new SlimSelect({
  select: '#prov' ,

});

let bob = document.getElementById('prov');
bob.length = 0;
bob.add(defaultOption);
bob.selectedIndex = 0;

/*var data = {
	name: document.getElementById("name").value,
	address:  document.getElementById("street").value,
	lng: coord.lat(), 
	lat: coord.lng(),
	withdrawn: 0,
	tags: tags.selected()
	
};*/


function showProv() { 
	
	fetch('/observatory/api/shops', {method: 'GET'})
  .then(function(response) {
    if(response.ok) return response.json();
    throw new Error("HTTP error, status = " + response.status);
  })
  .then(function(json) {
    let results = json.shops;
    let option;
      
    for (let i = 0; i < results.length; i++) {
        option = document.createElement('option');
        option.text = results[i].name;
        option.value = results[i].name;
        bob.add(option);
    } 
    //var nameofshop=bob.selected();
  })
  .catch(function(error) {
    console.error('Fetch Error -', error);
  });
	
	
}

function editProv() {
	
  var nameofshop=provider.selected();

  var uri = '/observatory/api/shopsname?name=' + nameofshop;
  //alert(uri);
  fetch(uri, {method: 'GET'})
  .then(function(response) {
    if(response.ok) return response.json();
    throw new Error("HTTP error, status = " + response.status);
  })
  .then(function(json) {
    let results = json.shops;
    myid = results[0].id;
    document.getElementById('name').value = results[0].name;
  	document.getElementById('street').value = results[0].address; 
  	loo = results[0].lng;
  	laa = results[0].lat;
  	var mar = new google.maps.Marker({
                    position: new google.maps.LatLng( laa,loo),
                    map: map,
                    title: 'Hello World!'
                });

  })
  .catch(function(error) {
    console.error('Fetch Error -', error);
  });
}

function putProv() {
	//alert(loo+" "+laa)
	let newName = document.getElementById('name').value;
	let newAddress = document.getElementById('street').value;
	//let newlaa = laa;
	//let newloo = loo;

  var  tokenHeader = new Headers({
    "X-OBSERVATORY-AUTH": "dc34ee33-486f-483a-b3a9-db552cb73974",
    "Content-Type": "application/json",
    
  });
  var uri = '/observatory/api/shops/' + myid;
  //alert(uri);
  fetch(uri, {
        method: 'PUT',
        headers: tokenHeader ,
        body: "name=" +newName +"&address=" + newAddress +"&lng=" + loo + "&lat=" + laa
      })
      .then(function(response) {
        if(response.status==200) return response.json();
        throw new Error("HTTP error, status = " + response.status);
      })
      .then(function(shop) {
        console.log('all good ');
      })
      .catch(function (error) {
        alert(error) ;
      });
	
}
