
var name = document.getElementById("prov_name").value;
var price = document.getElementById("price").value;
var dateFrom = document.getElementById("dateFroma").value;
var dateTo = document.getElementById("dateToa").value;
var description = document.getElementById("description").value;

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
var tags = tags_activity.selected();

var category_act = new SlimSelect({
  select: '#cat' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()
  }
});

var category = category_act.selected();

function createAct(){
	
}