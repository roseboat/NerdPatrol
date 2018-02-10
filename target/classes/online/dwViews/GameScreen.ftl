<html>

<head>
<!-- Web page title -->
<title>Top Trumps - Game</title>


<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=VT323" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css"
	integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body onload="initalize()">
	<!-- Call the initalize method when the page loads -->

	<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
	padding: 0;
}
/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 100%
}

body {
	background-image:
		url("http://123hdwallpaperpic.com/download/20150729/large-magellanic-cloud-galaxies-space-stars-2560x1600.jpg");
	font-family: VT323;
	font-size: 20px;
}
#mainBody {
	background: rgba(255, 255, 255, 0.8);
	padding: 20px;
}

.btn {
	cursor: pointer;
	font-size: 25px;
	padding: 10px 10px;
	box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}

h1 {
	font-weight: 700;
	font-size: 45px;
}

h3 {
	font-weight: 700;
	font-size: 30px;
}

/* Set black background color, white text and some padding */
footer {
	width: 100%;
	background-color: #555;
	bottom: 0;
	color: white;
	padding: 10px;
	font-size: 20px;
}

.card-img-top {
	width: 100%;
	height: 5vw;
	object-fit: cover;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

#statusBar {

	padding: 5px 0;
	text-align: center;
	display: none;
}

/* Formats all the buttons in the card section */

#cardSection button {
	cursor: pointer;
	font-size: 14px;
	font-family: 'Josefin Sans', sans-serif;
	font-weight: 0;
}

#cardSection {
	display: none;
	font-family: VT323;
	font-size: 15px;
}

.card-header {
	font-weight: 400;
	font-size: 20px;
}

.card-subtitle{
	font-size: 18px;
	padding: 10px;
}

b {
	font-weight: bold;
	color: #a980e7;
}

#drawCard {
	margin: auto;
	display: none;
}

#computerSelect {
	margin: auto;
	display: none;
}

#winBar {
	/* background-color: lightgreen; */
	padding: 5px 0;
	text-align: center;
	margin: auto;
	display: none;
}
	
#playAgain {
	display: none;
}
</style>
</head>

<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/toptrumps/" style="font-size: 28px">Top Trumps</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/toptrumps/" style="font-size: 25px;">Home</a></li>
				<li class="active"><a href="/toptrumps/game/" style="font-size: 25px;">Game</a></li>
				<li><a href="/toptrumps/stats/" style="font-size: 25px;">Statistics</a></li>
			</ul>
		</div>
	</nav>

	<div class="row">
		<div class="col-lg-12" style="padding: 15px;"></div>
	</div>

	<div class="row content">
		<div class="col-lg-2"></div>

		<div class="container-fluid text-center">
			<div class="col-lg-8 text-center" id="mainBody">

				<div id="setPlayers">
					<h1>Top Trumps!</h1>

					<h3>
						<strong>Choose the amount of players you'd like to play
							against:</strong>
					</h3>

					<br> <select id="playerCount" style="font-size: 20px;">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select><br>
					<br>
					<button class="btn btn-default" onclick="setPlayers();"
						style="padding: 5px;">Start Game!</button>
					<br>
					<br>
				</div>


				<div id="statusBar">
					<h1>
						Round Number: <strong><label id='roundNumber'></label></strong>
					</h1>
					<h3>
						Active Player: <b><label id='activePlayer'></label></b>
					</h3>
					
					<button class="btn btn-default"
					onclick="drawCardFunction()"
					id='drawCard'>Draw Cards</button><br>


					<p>
						Active Player: <strong><label id='activePlayer'></label></strong>
					</p>
					<p>
						Category Selected: <strong><label id='printCategory'></label></strong>
					</p>
					<p>
						Round Winner: <strong><label id='roundWinner'></label></strong>
					</p>
					<p>
						Cards to be Won: <strong><label id='cardPile'></label></strong>
					</p>


				</div>


				</div>


				<div id="winBar">
					<h1>
						Winner: <strong><label id='endGame'></label></strong>!!!
					</h1>
					<img
					src="http://blog.adsy.me/wp-content/uploads/2016/11/happy-open-hands-trump-transparent.png" style="width:325px;">
				</div>
				<br>
<<<<<<< HEAD

				<button class="btn btn-default"
					onclick="sendCardArray();cardsLeft();cardPile();roundNumber()"
					id='drawCard'>Draw Card</button>

				&nbsp;
				<button class="btn btn-default" onclick="computerSelect()"
					id='computerSelect'>COMPUTER CHOICE</button>
				<br>

=======
				
				<div id="playAgain">
				<form>
    				<input TYPE="button" VALUE="Play Again"
        			onclick="window.location.href='http://localhost:7777/toptrumps/game/'"> 
				</form>
				</div>
			
>>>>>>> bd5dcf985677e6ebe3737f6fb8688a444aebd675
				<div class="row text-center" id='cardSection'>

					<div class="col-sm-1"></div>

					<div class="col-lg-2">
						<div class="card" id="card1">
							<div class="card-header">Human</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top"
								src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Idris.jpg"
								alt="Card image cap">
							<div class="card-body">


								<button onclick="selectCategory(1)"
									class="btn btn-default btn-block" id="humanCat1">
									<span class="badge"></span>
								</button>
								<button onclick="selectCategory(2)"
									class="btn btn-default btn-block" id="humanCat2">
									<span class="badge"></span>
								</button>
								<button onclick="selectCategory(3)"
									class="btn btn-default btn-block" id="humanCat3">
									<span class="badge"></span>
								</button>
								<button onclick="selectCategory(4)"
									class="btn btn-default btn-block" id="humanCat4">
									<span class="badge"></span>
								</button>
								<button onclick="selectCategory(5)"
									class="btn btn-default btn-block" id="humanCat5">
									<span class="badge"></span>
								</button>
							</div>
							<div class="card-footer text-muted"></div>
						</div>
					</div>

					<div class="col-lg-2">
						<div class="card" id="card2">
							<div class="card-header">Computer 1</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top" src="..." alt="Card image cap">
							<div class="card-body">


								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
							</div>
							<div class="card-footer text-muted"></div>
						</div>
					</div>

					<div class="col-lg-2">
						<div class="card" id="card3">
							<div class="card-header">Computer 2</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top" src="..." alt="Card image cap">
							<div class="card-body">


								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
							</div>
							<div class="card-footer text-muted"></div>
						</div>
					</div>

					<div class="col-lg-2">
						<div class="card" id="card4">
							<div class="card-header">Computer 3</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top" src="..." alt="Card image cap">
							<div class="card-body">

								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
							</div>
							<div class="card-footer text-muted"></div>
						</div>
					</div>

					<div class="col-lg-2">
						<div class="card" id="card5">
							<div class="card-header">Computer 4</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top" src="..." alt="Card image cap">
							<div class="card-body">

								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
								<button class="btn btn-default btn-block" disabled>
									<span class="badge"></span>
								</button>
							</div>
							<div class="card-footer text-muted"></div>
						</div>
					</div>
				</div>


				<div class="col-sm-1"></div>
			</div>

			<div class="col-lg-2"></div>
		</div>
	</div>


	<div class="row content">
		<div class="col-lg-12" style="padding: 10px;"></div>
	</div>


	<footer class="container-fluid text-center">
		<p>Created by the Nerd Patrol</p>
	</footer>
</body>

<script type="text/javascript">
    // Method that is called on page load
    function initalize() {
      // --------------------------------------------------------------------------
      // You can call other methods you want to run when the page first loads here
      // --------------------------------------------------------------------------


    }

    // -----------------------------------------
    // Add your other Javascript methods Here
    // -----------------------------------------
    // This is a reusable method for creating a CORS request. Do not edit this.

    function createCORSRequest(method, url) {
      var xhr = new XMLHttpRequest();
      if ("withCredentials" in xhr) {
        // Check if the XMLHttpRequest object has a "withCredentials" property.
        // "withCredentials" only exists on XMLHTTPRequest2 objects.
        xhr.open(method, url, true);
      } else if (typeof XDomainRequest != "undefined") {
        // Otherwise, check if XDomainRequest.
        // XDomainRequest only exists in IE, and is IE's way of making CORS requests.
        xhr = new XDomainRequest();
        xhr.open(method, url);
      } else {
        // Otherwise, CORS is not supported by the browser.
        xhr = null;
      }
      return xhr;
    }

	function disableHumanButtons() {
		document.getElementById('humanCat1').disabled = true;
		document.getElementById('humanCat2').disabled = true;
		document.getElementById('humanCat3').disabled = true;
		document.getElementById('humanCat4').disabled = true;
		document.getElementById('humanCat5').disabled = true;
	}

	function enableHumanButtons() {
		document.getElementById('humanCat1').disabled = false;
		document.getElementById('humanCat2').disabled = false;
		document.getElementById('humanCat3').disabled = false;
		document.getElementById('humanCat4').disabled = false;
		document.getElementById('humanCat5').disabled = false;
	}

    function hideComputerButton() {
    	document.getElementById("computerSelect").style.display = "none";
    }

    function hideCards() {
   	    var opp1 = document.getElementById("card2");
   	    if (opp1.style.display === "none") {} else {
   	      opp1.style.display = "none";
   	   	}
   		var opp2 = document.getElementById("card3");
   	    if (opp2.style.display === "none") {} else {
   	    opp2.style.display = "none";
   	    }
   	    var opp3 = document.getElementById("card4");
   	    if (opp3.style.display === "none") {} else {
   	    opp3.style.display = "none";
   	    }
   	    var opp4 = document.getElementById("card5");
   	    if (opp4.style.display === "none") {} else {
   	    opp4.style.display = "none";
   	    }
   	 }

	function humanFunctionOrder() {
		enableHumanButtons();
		hideCards();
	}

  	function revealBar() {
    	document.getElementById("statusBar").style.display = "block";
  	}

  	function revealDrawCardButton() {
    	document.getElementById("drawCard").style.display = "block";
  	}

   	function revealComputerSelectButton() {
		document.getElementById("computerSelect").style.display = "block";
  	}

  	function revealCards() {
    	document.getElementById("card2").style.display = "block";
    	document.getElementById("card3").style.display = "block";
    	document.getElementById("card4").style.display = "block";
    	document.getElementById("card5").style.display = "block";
  	}

   	function revealWinBar() {
		document.getElementById("winBar").style.display = "block";
  	}
  	
  	function revealPlayAgain() {
		document.getElementById("playAgain").style.display = "block";
  	}

  	function revealcardSection() {
  		document.getElementById("cardSection").style.display = "block";
    }

	function hideSelection() {
	    var x = document.getElementById("setPlayers");
	    if (x.style.display === "none") {} else {
	      x.style.display = "none";
	    }
	}

	function hideStatusBar() {
	    var x = document.getElementById("statusBar");
	    if (x.style.display === "none") {} else {
	      x.style.display = "none";
	    }
    }


	function buildCards() {
        var playerNum = $('#playerCount').val();

        if (playerNum == 1) {
          $("#card3").remove();
          $("#card4").remove();
          $("#card5").remove();
        } else if (playerNum == 2) {
          $("#card4").remove();
          $("#card5").remove();
        } else if (playerNum == 3) {
          $("#card5").remove();
        }
    }
<<<<<<< HEAD
=======
  }
	
	function drawCardFunction() {
		
		sendCardArray();
		cardsLeft();
		
		roundNumber();
	}
>>>>>>> bd5dcf985677e6ebe3737f6fb8688a444aebd675

  </script>

<!-- Here are examples of how to call REST API Methods -->
<script type="text/javascript">


  function setPlayers() {
    var number = document.getElementById('playerCount').value;
    var xhr = createCORSRequest('GET',
      "http://localhost:7777/toptrumps/setPlayers?Number=" + number); // Request type and URL+parameters
    if (!xhr) {
      alert("CORS not supported");
    }
    xhr.onload = function(e){
        if (number < 1 || number > 4) {
          alert("Player number out of bounds");
        } else {
          buildCards();
<<<<<<< HEAD

=======
>>>>>>> bd5dcf985677e6ebe3737f6fb8688a444aebd675
          hideSelection();
          drawCardFunction();
          revealBar();
          revealDrawCardButton();
        }
    }

    xhr.send();
  }

<<<<<<< HEAD
  function computerSelect(){
       var xhr = createCORSRequest('GET',
      "http://localhost:7777/toptrumps/computerSelect");
    if (!xhr) {
      alert("CORS not supported");
    }

     xhr.onload = function(e) {
  var responseText = xhr.response;
    responseText = responseText.replace(/^"(.*)"$/, '$1');
    document.getElementById('printCategory').innerHTML = responseText;
      processRound();

    }

    xhr.send();
    revealCards();
  }

  function activePlayer() {
	var xhr = createCORSRequest('GET',
				"http://localhost:7777/toptrumps/activePlayer");
	if (!xhr) {
		alert("tester");
	}
	xhr.onload = function(e) {
=======
	function activePlayer() {
		cardPile();
		var xhr = createCORSRequest('GET',
				"http://localhost:7777/toptrumps/activePlayer");
		if (!xhr) {
			alert("No active Player error");
		}
		xhr.onload = function(e) {
			
		disableDrawButton();
>>>>>>> bd5dcf985677e6ebe3737f6fb8688a444aebd675
		var responseText = xhr.response; // the text of the response
		//responseText = responseText.replace(/^"(.*)"$/, '$1');
		document.getElementById('activePlayer').innerHTML = responseText;
<<<<<<< HEAD

			if (responseText != "Human Player")	{
=======
	
			if (responseText != "Human Player")	{		
>>>>>>> bd5dcf985677e6ebe3737f6fb8688a444aebd675
				setTimeout("computerSelect()", 2000);
				disableHumanButtons();
				revealCards();
				}
			else{
				humanFunctionOrder();
				}
		}
		xhr.send();
	}



<<<<<<< HEAD


	 function sendCardArray() {

	      var xhr = createCORSRequest('GET',
	        "http://localhost:7777/toptrumps/sendCardArray");
	      if (!xhr) {
	        alert("No cards found");
	      }
	      xhr.onload = function(e) {
	          activePlayer();
	        var responseText = xhr.response; // the text of the response
	        var list = JSON.parse(responseText);

			cardExample = list[0];

	        for (i = 0; i < 5; i++) {
	          var cardTitle = "#card" + (i + 1);
	          $(cardTitle).find(".card-img-top").attr("src", "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + list[i].name + ".jpg");
	          $(cardTitle).find("#card-title").text(list[i].name);
	          $(cardTitle).find(".btn").each(function(j) {
	            $(this).html(list[i].categories[j] + "  " + "<span class=\"badge\">" + list[i].cardValues[j] + "</span>");
	          });
	        }
	      }
	      cardPile();
	      revealcardSection();
	      document.getElementById('printCategory').innerHTML = "";
	      document.getElementById('roundWinner').innerHTML = "";

	      xhr.send();
	    }

=======
       xhr.onload = function(e) {
	var responseText = xhr.response; 
      responseText = responseText.replace(/^"(.*)"$/, '$1');
      document.getElementById('printCategory').innerHTML = responseText;
      
		processRound();
		
      }

      xhr.send();
	}

>>>>>>> bd5dcf985677e6ebe3737f6fb8688a444aebd675
    function selectCategory(x) {
      var number = x
      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/selectCategory?Number=" + number); // Request type and URL+parameters
      if (!xhr) {
        alert("CORS not supported");
      }

      xhr.onload = function(e) {
<<<<<<< HEAD

		processRound();

      }
      xhr.send();

      document.getElementById('printCategory').innerHTML = cardExample.categories[x-1];
      revealCards();

=======
      
    	  var responseText = xhr.response; 
          responseText = responseText.replace(/^"(.*)"$/, '$1');
          document.getElementById('printCategory').innerHTML = responseText;
    	  
    	  processRound();
    	  disableHumanButtons();
      }
      xhr.send();

>>>>>>> bd5dcf985677e6ebe3737f6fb8688a444aebd675
    }

	function processRound(){

	var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/processRound");
      if (!xhr) {
        alert("CORS not supported");
      }

      xhr.onload = function(e) {
	var responseText = xhr.response; // the text of the response
      responseText = responseText.replace(/^"(.*)"$/, '$1');
      document.getElementById('roundWinner').innerHTML = responseText;
<<<<<<< HEAD

=======
      enableDrawButton();
      cardPile(); //this one reduces it at end of round
>>>>>>> bd5dcf985677e6ebe3737f6fb8688a444aebd675
  if (responseText== "EndGame"){
  		hideStatusBar();
  		endGame();
		revealWinBar();
		revealPlayAgain();
		alert("Game stats saved");
  }
}
 xhr.send();


      revealCards();



}

	 function sendCardArray() {

	      var xhr = createCORSRequest('GET',
	        "http://localhost:7777/toptrumps/sendCardArray");
	      if (!xhr) {
	        alert("No cards found");
	      }
	      xhr.onload = function(e) {
	          activePlayer();
	        var responseText = xhr.response; // the text of the response
	        var list = JSON.parse(responseText);

			cardExample = list[0];

<<<<<<< HEAD
        for (i = 0; i < 5; i++) {
          var cardTitle = "#card" + (i + 1);
          $(cardTitle).find(".card-img-top").attr("src", "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + list[i].name + ".jpg");
          $(cardTitle).find("#card-title").text(list[i].name);
          $(cardTitle).find(".btn").each(function(j) {
            $(this).html(list[i].categories[j] + "  " + "<span class=\"badge\">" + list[i].cardValues[j] + "</span>");
          });
        }
      }

      cardPile();
      revealcardSection();
      document.getElementById('printCategory').innerHTML = "";
      document.getElementById('roundWinner').innerHTML = "";

      xhr.send();
    }
=======
	        for (i = 0; i < 5; i++) {
	          var cardTitle = "#card" + (i + 1);
	          $(cardTitle).find(".card-img-top").attr("src", "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + list[i].name + ".jpg");
	          $(cardTitle).find("#card-title").text(list[i].name);
	          $(cardTitle).find(".btn").each(function(j) {
	            $(this).html(list[i].categories[j] + "  " + "<span class=\"badge\">" + list[i].cardValues[j] + "</span>");
	          });
	        }
	      }
	      revealcardSection();
	      document.getElementById('printCategory').innerHTML = "";
	      document.getElementById('roundWinner').innerHTML = "";
	    
	      xhr.send();
	    }
>>>>>>> bd5dcf985677e6ebe3737f6fb8688a444aebd675

	function cardsLeft() {
    var xhr = createCORSRequest('GET',
      "http://localhost:7777/toptrumps/cardsLeft");
    if (!xhr) {
      alert("Fucked it");
    }
    xhr.onload = function(e) {
      var responseText = xhr.response; // the text of the response
      var list = JSON.parse(responseText);

		cardExample = list[0];

      for (i = 0; i < 5; i++) {
        var cardTitle = "#card" + (i + 1);

        if (list[i] ==0){
        $("#card"+ (i+1)).remove();
        }
        else{

        $(cardTitle).find(".card-footer").text(list[i]+" cards remaining...");
    	}

      }
    }
    xhr.send();
  }

    function endGame() {

		var xhr = createCORSRequest('GET',
				"http://localhost:7777/toptrumps/endGame");
		if (!xhr) {
			alert("Error");
			}
		xhr.onload = function(e) {

		var responseText = xhr.response; // the text of the response
			responseText = responseText.replace(/^"(.*)"$/, '$1');
			document.getElementById('endGame').innerHTML = responseText;
			hideCards();
	}

	xhr.send();
	}

	function cardPile() {

		var xhr = createCORSRequest('GET',
				"http://localhost:7777/toptrumps/cardPile");
		if (!xhr) {
			alert("No cards found");
			}
		xhr.onload = function(e) {
		var responseText = xhr.response; // the text of the response
			document.getElementById('cardPile').innerHTML = responseText;
	};
	xhr.send();
	}

   function roundNumber() {
   		var xhr = createCORSRequest('GET',
				"http://localhost:7777/toptrumps/roundNumber");
		if (!xhr) {
			alert("No Round Number");
			}
		xhr.onload = function(e) {
		var responseText = xhr.response; // the text of the response
			document.getElementById('roundNumber').innerHTML = responseText;
	}
	xhr.send();
   }

  </script>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>


</body>
</html>
