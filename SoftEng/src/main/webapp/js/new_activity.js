


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


var category_act = new SlimSelect({
  select: '#cat' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()
  }
});


function createAct(){
  var name = document.getElementById("name").value;
  var price = document.getElementById("price").value;
  var dateFrom = document.getElementById("dateFroma").value;
  var dateTo = document.getElementById("dateToa").value;
  var description = document.getElementById("description").value;
  var category = category_act.selected();
  var tags = tags_activity.selected();
  var i;
  /*var tags_final="";
  for(i=0; i<tags.length; i++){
    tags_final+=("&tags=" +tags_activity[i]);
  }*/
  var alltags = "";
  tags.forEach(function(tag){
    alltags = alltags + "&tags=" + tag;
  });
	fetch('/observatory/api/products', {
    method: 'POST',

    body: "name=" +name +"&category=" + category + alltags
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
