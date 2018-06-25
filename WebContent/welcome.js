var xmlhttp

function showHint (str)
{
   if (str.length == 0)
   {
      document.getElementById ("txtHint").innerHTML = "";
      return;
   }
   xmlhttp = GetXmlHttpObject();
   if (xmlhttp == null)
   {
      alert ("Your browser does not support XMLHTTP!");
      return;
   }
	   
   // Backend handler - response software component
   var url = "http://localhost:8080/4640Proj/welcomeMsg";

   url = url + "?StringSoFar=" + str;
   url = url + "&sid=" + Math.random();
   
   // How to configure XMLHttpRequest object?
   // Register the callback function named "stateChanged"
   xmlhttp.onreadystatechange = stateChanged;

   
   
   // How to make an asynchronous request?
   xmlhttp.open("GET", url,true);
   
   
   
   // How to send the request to the server?
   xmlhttp.send();

   
}

function stateChanged()
{
	// The callback function processes the response from the server	
   if (xmlhttp.readyState == 4)
   { 
	  // Update the HTML DOM
      document.getElementById ("txtHint").innerHTML = xmlhttp.responseText;
   }
}

function GetXmlHttpObject()
{
	// How to create an XMLHttpRequest object?
	if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	}
	if(window.ActiveXObject){
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	return null;
}