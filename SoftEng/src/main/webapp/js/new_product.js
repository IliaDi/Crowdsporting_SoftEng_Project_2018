document.getElementById("newProduct").addEventListener("click", validateProdForm);
var tags_product = new SlimSelect({
  select: '#tags_prod' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()
  }
});

var category_prod = new SlimSelect({
  select: '#cat' ,


});

function createProd() { 
	var name = document.getElementById("name").value;
	var description = document.getElementById("description").value;
	var tags = tags_product.selected();
	var category = category_prod.selected();


	var alltags = "";
	  tags.forEach(function(tag){
	    alltags = alltags + "&tags=" + tag;
	  });
	  
	fetch('/observatory/api/products', {
	    method: 'POST',

	    body: "name=" +name +"&description=" + description +"&category="+category + alltags
	  })
	  .then(function(response) {
	    if(response.status==200) return response.json();
	    throw new Error("HTTP error, status = " + response.status);
	  })
	  .then(function(product) {
	    console.log('all good ');
		alert('Επιτυχής καταχώρηση προϊόντος');
	  })
	  .catch(function (error) {
	   	alert(error) ;
	  });

}

function validateProdForm() {	
			var nameentry=document.getElementById("name").value;
			var descrentry=document.getElementById("description").value;
			var catentry=document.getElementById("cat").value;
			if(nameentry=="" || descrentry=="" || catentry=="") {
				alert("Please fill all required fields!");
				return false; }
			else { 
				createProd();
				return true;
			}
}