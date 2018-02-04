<html>

  <head>
    <!-- Web page title -->
    <title>Top Trumps - Game</title>


    <!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <body onload="initalize()">
      <!-- Call the initalize method when the page loads -->

      <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        
        .navbar {
          margin-bottom: 0;
          border-radius: 0;
        }
        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        
        .row.content {
          height: 100%
        }
        /* Set gray background color and 100% height */
        
        .sidenav {
          padding-top: 20px;
          background-color: #f1f1f1;
          height: 100%;
        }
        /* Set black background color, white text and some padding */
        
        footer {
          background-color: #555;
          color: white;
          padding: 15px;
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
          background-color: lightblue;
          padding: 5px 0;
          text-align: center;
          display: none;
        }
        
        .card-img-top {
          width: 100%;
          height: 5vw;
          object-fit: cover;
        }

      </style>

  </head>

  <body>

    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="/toptrumps/">Top Trumps</a>
        </div>
        <ul class="nav navbar-nav">
          <li><a href="/toptrumps/">Home</a></li>
          <li class="active"><a href="/game/">Game</a></li>
          <li><a href="/toptrumps/stats/">Statistics</a></li>
        </ul>
      </div>
    </nav>

    <div class="container-fluid text-center">
      <div class="row content">
        <div class="col-sm-2 sidenav"></div>

        <div class="container-fluid text-center">
          <div class="col-lg-8 text-center">
            <h1>Top Trumps!</h1>


            <div id="myDIV">
              <p>Choose the amount of players you'd like to play against:</p>

              <select id="input1" style="font-size:17px;">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
              </select>
              <button class="btn btn-default" onclick="chooseNumberPlayers();" width="25">Submit</button>
            </div>
            <br>

            <div id="statusBar">
              <p> Active Player is
                <label id='activePlayer'></label>
                <p> The Category selected is:
                <strong><label id='printCategory'></label></strong>
                <p> The winner of this round is
                <label id='roundWinner'></label> 
 				<p>
            </div>


           	<br> <div>Cards to be Won: <label id='pile'></label> </div>
            <hr>
            <h3>Let's Play!</h3>
            <br>
            <button onclick="sendCardArray();">Draw Card</button>
            <div id="playerTurn"></div>
            <br>
            <br>

            <div class="row text-center">

              <div class="col-sm-1"></div>

              <div class="col-lg-2">
                <div class="card" id="card1" style="display">
                  <h4 class="card-header">Human Player</h4>
                  <img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Idris.jpg" alt="Card image cap">
                  <div class="card-body">
                    <h5 class="card-title">Avenger</h5>


                    <button onclick="selectCategory(1)" class="btn btn-default btn-block">Cat1<span class="badge">7</span></button>

                    <button onclick="selectCategory(2)" class="btn btn-default btn-block">Cat2<span class="badge">4</span></button>

                    <button onclick="selectCategory(3)" class="btn btn-default btn-block">Cat3<span class="badge">9</span></button>

                    <button onclick="selectCategory(4)" class="btn btn-default btn-block">Cat4<span class="badge">3</span></button>

                    <button onclick="selectCategory(5)" class="btn btn-default btn-block">Cat5<span class="badge">2</span></button>
                  </div>
                </div>
              </div>

              <div class="col-lg-2">
                <div class="card" id="card2">
                  <h4 class="card-header">Computer Player</h4>
                  <img class="card-img-top" src="..." alt="Card image cap">
                  <div class="card-body">
                    <h5 class="card-title">Avenger</h5>

                    <button onclick="selectCategory(1)" class="btn btn-default btn-block" disabled><span class="badge">7</span></button>

                    <button onclick="selectCategory(2)" class="btn btn-default btn-block" disabled>Cat2<span class="badge">4</span></button>

                    <button onclick="selectCategory(3)" class="btn btn-default btn-block" disabled>Cat3<span class="badge">9</span></button>

                    <button onclick="selectCategory(4)" class="btn btn-default btn-block" disabled>Cat4<span class="badge">3</span></button>

                    <button onclick="selectCategory(5)" class="btn btn-default btn-block" disabled>Cat5<span class="badge">2</span></button>
                  </div>
                </div>
              </div>

              <div class="col-lg-2">
                <div class="card" id="card3">
                  <h4 class="card-header">Computer Player</h4>
                  <img class="card-img-top" src="..." alt="Card image cap">
                  <div class="card-body">
                    <h5 class="card-title">Avenger</h5>


                    <button onclick="selectCategory(1)" class="btn btn-default btn-block" disabled><span class="badge">7</span></button>

                    <button onclick="selectCategory(2)" class="btn btn-default btn-block" disabled>Cat2<span class="badge">4</span></button>

                    <button onclick="selectCategory(3)" class="btn btn-default btn-block" disabled>Cat3<span class="badge">9</span></button>

                    <button onclick="selectCategory(4)" class="btn btn-default btn-block" disabled>Cat4<span class="badge">3</span></button>

                    <button onclick="selectCategory(5)" class="btn btn-default btn-block" disabled>Cat5<span class="badge">2</span></button>
                  </div>
                </div>
              </div>

              <div class="col-lg-2">
                <div class="card" id="card4">
                  <h4 class="card-header">Computer Player</h4>
                  <img class="card-img-top" src="..." alt="Card image cap">
                  <div class="card-body">
                    <h5 class="card-title">Avenger</h5>


                    <button onclick="selectCategory(1)" class="btn btn-default btn-block" disabled><span class="badge">7</span></button>

                    <button onclick="selectCategory(2)" class="btn btn-default btn-block" disabled>Cat2<span class="badge">4</span></button>

                    <button onclick="selectCategory(3)" class="btn btn-default btn-block" disabled>Cat3<span class="badge">9</span></button>

                    <button onclick="selectCategory(4)" class="btn btn-default btn-block" disabled>Cat4<span class="badge">3</span></button>

                    <button onclick="selectCategory(5)" class="btn btn-default btn-block" disabled>Cat5<span class="badge">2</span></button>
                  </div>
                </div>
              </div>

              <div class="col-lg-2">
                <div class="card" id="card5">
                  <h4 class="card-header">Computer Player</h4>
                  <img class="card-img-top" src="..." alt="Card image cap">
                  <div class="card-body">
                    <h5 class="card-title">Avenger</h5>


                    <button onclick="selectCategory(1)" class="btn btn-default btn-block" disabled><span class="badge">7</span></button>

                    <button onclick="selectCategory(2)" class="btn btn-default btn-block" disabled>C2345at2<span class="badge">4</span></button>

                    <button onclick="selectCategory(3)" class="btn btn-default btn-block" disabled>C23454at3<span class="badge">9</span></button>

                    <button onclick="selectCategory(4)" class="btn btn-default btn-block" disabled>Caagret4<span class="badge">3</span></button>

                    <button onclick="selectCategory(5)" class="btn btn-default btn-block" disabled>Caasfgt5<span class="badge">2</span></button>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-sm-1"></div>
          </div>


          <div class="col-sm-2 sidenav"></div>
        </div>
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
      // For example, lets call our sample methods
      //helloJSONList();
      //helloWord("Student");

      //setCategories();
      //cardTest();

      //cardPile();

    }

    function buildCards() {

      var playerNum = $('#input1').val();

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

    function hideSelection() {
      var x = document.getElementById("myDIV");
      if (x.style.display === "none") {} else {
        x.style.display = "none";
      }
    }

    function revealBar() {

      document.getElementById("statusBar").style.display = "block";
    }

  </script>

  <!-- Here are examples of how to call REST API Methods -->
  <script type="text/javascript">
  
    function selectCategory(x) {
      var number = x
      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/selectCategory?Number=" + number); // Request type and URL+parameters
      if (!xhr) {
        alert("CORS not supported");
      }
      
      xhr.onload = function(e) {
      
      var responseText = xhr.response; // the text of the response
      responseText = responseText.replace(/^"(.*)"$/, '$1');
      document.getElementById('roundWinner').innerHTML = responseText;
      }
      
      xhr.send();
      
      document.getElementById('printCategory').innerHTML = cardExample.categories[x-1];
      
      
    }


    function activePlayer() {

      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/activePlayer");
      if (!xhr) {
        alert("tester");
      }
      xhr.onload = function(e) {

        var responseText = xhr.response; // the text of the response
        responseText = responseText.replace(/^"(.*)"$/, '$1');
        document.getElementById('activePlayer').innerHTML = responseText;
      }
      xhr.send();
    }
    



    function chooseNumberPlayers() {
      var number = document.getElementById('input1').value;
      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/setPlayers?Number=" + number); // Request type and URL+parameters
      if (!xhr) {
        alert("CORS not supported");
      }

      if (number < 1 || number > 4) {
        alert("Player number out of bounds");
      } else {


        buildCards();
        hideSelection();
        activePlayer();
        revealBar();



        xhr.send();

      }


      xhr.send();
    }

    // This calls the helloJSONList REST method from TopTrumpsRESTAPI
    function helloJSONList() {
      // First create a CORS request, this is the message we are going to send (a get request in this case)
      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
      // Message is not sent yet, but we can check that the browser supports CORS
      if (!xhr) {
        alert("CORS not supported");
      }
      // CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
      // to do when the response arrives 
      xhr.onload = function(e) {
        var responseText = xhr.response; // the text of the response
        //alert(responseText); // lets produce an alert
      };
      // We have done everything we need to prepare the CORS request, so send it
      xhr.send();
    }
    // This calls the helloJSONList REST method from TopTrumpsRESTAPI
    function helloWord(word) {
      // First create a CORS request, this is the message we are going to send (a get request in this case)
      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/helloWord?Word=" + word); // Request type and URL+parameters
      // Message is not sent yet, but we can check that the browser supports CORS
      if (!xhr) {
        alert("CORS not supported");
      }
      // CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
      // to do when the response arrives 
      xhr.onload = function(e) {
        var responseText = xhr.response; // the text of the response
        //alert(responseText); // lets produce an alert
      };
      // We have done everything we need to prepare the CORS request, so send it
      xhr.send();
    }

    function cardTest() {
      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/cardTest");
      if (!xhr) {
        alert("Fucked it");
      }
      xhr.onload = function(e) {
        var responseText = xhr.response; // the text of the response
        var rT = JSON.parse(responseText);

        $(".panel-heading").append("Deez Nuts");
        $("#Cat1").append("Hello");
        for (i = 0; i < rT.number_OF_CATEGORIES; i++) {
          var catName = "#Cat" + (i + 1);
          $(catName).html(rT.categories[i] + "<span class=\"badge\">" + rT.cardValues[i] + "</span>");
        }

        alert(rT.name);
      }
      xhr.send();
    }
	
	var cardExample = undefined;
	
    function sendCardArray() {
      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/sendCardArray");
      if (!xhr) {
        alert("Fucked it");
      }
      xhr.onload = function(e) {
        var responseText = xhr.response; // the text of the response
        var list = JSON.parse(responseText);
		
		cardExample = list[0];
		
        for (i = 0; i < 5; i++) {
          var cardTitle = "#card" + (i + 1);
          $(cardTitle).find(".card-img-top").attr("src", "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + list[i].name + ".jpg");
          $(cardTitle).find(".card-title").text(list[i].name);
          $(cardTitle).find(".btn").each(function(j) {
            $(this).html(list[i].categories[j] + "  " + "<span class=\"badge\">" + list[i].cardValues[j] + "</span>");
          });
        }
      
      }
      xhr.send();
    }

    function cardPile() {

      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/cardPile");
      if (!xhr) {
        alert("dickfarts");
      }
      xhr.onload = function(e) {
        var responseText = xhr.response; // the text of the response
        document.getElementById('pile').innerHTML = responseText;
      }
      xhr.send();
    }

    function setCategories() {

      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/setCategories");
      if (!xhr) {
        alert("tester");
      }
      xhr.onload = function(e) {

        var responseText = xhr.response; // the text of the response
        responseText = responseText.replace(/^"(.*)"$/, '$1');
        document.getElementById('Cat1').innerHTML = responseText;
      }
      xhr.send();
    }

    function whosTurn() {
      var xhr = createCORSRequest('GET',
        "http://localhost:7777/toptrumps/whosTurn");
      if (!xhr) {
        alert("No players!");
      }
      xhr.onload = function(e) {
        var responseText = xhr.response;
        document.getElementById('playerTurn').innerHTML = "It is " + responseText + "'s turn!";
      }
      xhr.send;
    }
    
    function printWinner() {
	
		var xhr = createCORSRequest('GET',
				"http://localhost:7777/toptrumps/printWinner");
		if (!xhr) {
			alert("tester");
			}
		xhr.onload = function(e) {
		
		var responseText = xhr.response; // the text of the response
			responseText = responseText.replace(/^"(.*)"$/, '$1');
			document.getElementById('roundWinner').innerHTML = responseText;
	}
	xhr.send();
	}

	function cardPile() {
		
		var xhr = createCORSRequest('GET',
				"http://localhost:7777/toptrumps/cardPile");
		if (!xhr) {
			alert("dickfarts");
			}
		xhr.onload = function(e) {
		var responseText = xhr.response; // the text of the response
			document.getElementById('pile').innerHTML = responseText;
	}
	xhr.send();
	}

  </script>


  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


  </body>

</html>
