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
	var address = document.getElementById("description").value;
	var tags = tags_product.selected();


	var alltags = "";
	  tags.forEach(function(tag){
	    alltags = alltags + "&tags=" + tag;
	  });

}

function validateProdForm() {	
			var nameentry=document.getElementById("name").value;
			var descrentry=document.getElementById("description").value;
			var catentry=document.getElementById("cat").value;
			if(nameentry=="" || descrentry=="" || catentry==null) {
				alert("Please fill all required fields!");
				return false; }
			else { 
				createProd();
				return true;
			}
}