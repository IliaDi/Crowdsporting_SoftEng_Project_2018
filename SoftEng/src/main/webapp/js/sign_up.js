document.getElementById("signUp").addEventListener("click", validateNewAcc);
var  tokenHeader = new Headers({
    "X-OBSERVATORY-AUTH": "dc34ee33-486f-483a-b3a9-db552cb73974",
    "Content-Type": "application/json",
    
  });
function createAcc() {
	var fname = document.getElementById("fullname").value;
	var pword = document.getElementById("psw").value;
	var confirmpword = document.getElementById("rpsw").value;
	var email = document.getElementById("email").value;
	fetch('/observatory/api/signup', {
    method: 'POST',
	headers: tokenHeader,
    body: "fullname=" +fname +"&password=" + pword + "&email=" + email
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

function validateNewAcc() {	
			var fnameentry=document.getElementById("fullname").value;
			var passwentry=document.getElementById("psw").value;
			var rpasswentry=document.getElementById("rpsw").value;
			var emailentry=document.getElementById("email").value;
			if(fnameentry=="" || passwentry=="" || emailentry=="") {
				alert("Please fill all required fields!");
				return false;}
			else if(passwentry != rpasswentry) {
				alert("Password and its confirmation must match.");
				return false;
			}
			else { 
				createAcc();
				return true;
			}
}