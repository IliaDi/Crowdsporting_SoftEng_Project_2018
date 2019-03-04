document.getElementById("sear").addEventListener("click", showProd);
document.getElementById("smh").addEventListener("click", editProd);
document.getElementById("newProduct").addEventListener("click", putProd);
var myid;
/*var tags_prod = new SlimSelect({
  select: '#tags_prod' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()
  }
});*/

var category_prod = new SlimSelect({
  select: '#cat' ,


});

var prod = new SlimSelect({
  select: '#prod' ,
}); 
let bob = document.getElementById('prod');
bob.length = 0;
bob.add(defaultOption);
bob.selectedIndex = 0;

function showProd() { 

	/*var name = document.getElementById("name").value;
	var description = document.getElementById("description").value;
	var tags = tags_product.selected();
	var category = category_prod.selected();*/
	
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
    //var nameofproduct=bob.selected();
  })
  .catch(function(error) {
    console.error('Fetch Error -', error);
  });
    
}
	
//var nameofproduct=bob.selected();

function editProd() {
  //var nameofproduct=bob.selected();
  var nameofproduct=prod.selected();

  var uri = '/observatory/api/productname/' + nameofproduct;

  fetch(uri, {method: 'GET'})
  .then(function(response) {
    if(response.ok) return response.json();
    throw new Error("HTTP error, status = " + response.status);
  })
  .then(function(json) {
    let results = json.products;
    myid = results[0].id;
    document.getElementById('name').value = results[0].name;
  	document.getElementById('description').value = results[0].description;
  	category_prod.set(results[0].category);
  	//tags_prod.set((results[0].tags)[0]);
    
  })
  .catch(function(error) {
    console.error('Fetch Error -', error);
  });
  //alert((results[0].tags)[0]);
}

function putProd() {
  let newName = document.getElementById('name').value;
	let newDescription = document.getElementById('description').value;
	let newCat = category_prod.selected();
	/*let new = tags_prod.selected();
  var alltags = "";
    newTags.forEach(function(tag){
      alltags = alltags + "&tags=" + tag;
    });*/
  //alert(alltags);
  var  tokenHeader = new Headers({
    "X-OBSERVATORY-AUTH": "dc34ee33-486f-483a-b3a9-db552cb73974",
    "Content-Type": "application/json",
              // "Content-Type": "application/x-www-form-urlencoded",
          
  });


  var uri = '/observatory/api/products/' + myid;
  //alert(uri);
  fetch(uri, {
        method: 'PUT',
        headers: tokenHeader,
        body: "name=" +newName +"&description=" + newDescription +"&category=" + newCat //+ alltags
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

}

