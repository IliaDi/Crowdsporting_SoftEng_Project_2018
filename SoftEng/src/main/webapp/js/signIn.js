document.getElementById('logIn').addEventListener("click", signMeIn);
function signMeIn() {
	let username = document.getElementById('uname').value;
	let pass = document.getElementById('psw').value;
	fetch('/observatory/api/token', {
	    method: 'POST',
	    headers: {
            "Content-Type": "application/json",
            // "Content-Type": "application/x-www-form-urlencoded",
        },
	    body: "username=" +username +"&password=" + pass
	  })
	  .then(function(response) {
	    if(response.status==200) return response.json();
	    throw new Error("HTTP error, status = " + response.status);
	  })
	  .then(function(product) {
	    let tok = product[0].token
		alert('Åðéôõ÷Þò åßóïäïò');
	  })
	  .catch(function (error) {
	   	alert(error) ;
	  });

}