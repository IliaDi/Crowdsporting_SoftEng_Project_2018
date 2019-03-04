document.getElementById("prov_search").addEventListener("click", getshops);
document.getElementById("act_search").addEventListener("click", getproducts);
document.getElementById("newActivity").addEventListener("click", createAct);

var tags_activity = new SlimSelect({
  select: '#tags_act' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()
  }
});


var product = new SlimSelect({
  select: '#name' ,
  showSearch: true,
  searchText: 'Δεν βρέθηκε,δημιουργήστε νέα δραστηριότητα',
  searchPlaceholder: 'Αναζητήστε καταχωρημένες δραστηριότητες',
  searchHighlight: true

});

var shop = new SlimSelect({
  select: '#prov_name' ,
  showSearch: true,
  searchText: 'Δεν βρέθηκε,δημιουργήστε νέο πάροχο',
  searchPlaceholder: 'Αναζητήστε καταχωρημένους παρόχους',
  searchHighlight: true

});


let bob = document.getElementById('name');
let dropdown = document.getElementById('prov_name');
bob.length = 0;
dropdown.length = 0;
bob.add(defaultOption);
dropdown.add(defaultOption);
bob.selectedIndex = 0;
dropdown.selectedIndex = 0;
/*
let dropdown = document.getElementById('prov_name');
dropdown.length = 0;
dropdown.add(defaultOption);
dropdown.selectedIndex = 0;*/


function getproducts(){
  fetch('/observatory/api/products', {method: 'GET'})
  .then(function(response) {
    if(response.ok) return response.json();
    throw new Error("HTTP error, status = " + response.status);
  })
  .then(function(json) {
    let results = json.products;
    let option;
      
    for (let i = 0; i < results.length; i++) {
        option = document.createElement('option');
        option.text = results[i].name;
        option.value = results[i].name;
        bob.add(option);
    } 
  })
  .catch(function(error) {
    console.error('Fetch Error -', error);
  });
}



function getshops(){
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
        dropdown.add(option);
    } 
  })
  .catch(function(error) {
    console.error('Fetch Error -', error);
  });}




function createAct(){
var price = document.getElementById("price").value;
var pid = product.selected();
var sid = shop.selected(); 
var dateFroma = document.getElementById("dateFroma").value;
var dateToa = document.getElementById("dateToa").value;

  fetch('/observatory/api/prices', {
      method: 'POST',
      headers: tokenHeader,
      body: "price=" +price +"&pid=" + pid +"&sid="+sid +"&dateFrom=" + dateFroma +"&dateTo="+dateToa
    })
    .then(function(response) {
      if(response.status==200) return response.json();
      throw new Error("HTTP error, status = " + response.status);
    })
    .then(function(product) {
      console.log('all good ');
    alert('Επιτυχής καταχώρηση τιμής');
    })
    .catch(function (error) {
      alert(error) ;
    });
}