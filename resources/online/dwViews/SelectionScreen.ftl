<html>
<head>
<!-- Web page title -->
<title>Top Trumps - Home</title>

<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=VT323" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


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
	font-size: 25px;
}

h1 {
	font-weight: 700;
	font-size: 45px;
}

.btn {
	cursor: pointer;
	font-size: 25px;
	padding: 10px 10px;
	box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
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

#mainBody {
	background: rgba(255, 255, 255, 0.8);
	padding: 10px;
}
</style>
</head>
<body>

	<!-- Call the initalize method when the page loads -->
<body onload="initalize()">

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/toptrumps/" style="font-size: 28px">Top Trumps</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/toptrumps/">Home</a></li>
				<li><a href="/toptrumps/game/">Game</a></li>
				<li><a href="/toptrumps/stats/">Statistics</a></li>
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
	
				<h1>Top Trumps Home!</h1>
				<img
					src="http://time-static-shared.s3-website-us-east-1.amazonaws.com/interactives/presidential_reading_level/img/trump.png" style="width:275px;">

				<p>Choose whether you want to play a game or view statistics</p>

				<button class="btn btn-default" onclick="playGame();" >Play
					a Game</button>
				&nbsp;
				<button class="btn btn-default" onclick="viewStatistics();">View
					Game Statistics</button>
					<br><br>
			</div>
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
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
				
			}
	
			
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
		
		</script>

<!-- Here are examples of how to call REST API Methods -->
<script type="text/javascript">
		
			function playGame(){
        		window.location='http://localhost:7777/toptrumps/game';
    		}
    		
    		function viewStatistics(){
    			window.location='http://localhost:7777/toptrumps/stats';
    		}

		</script>
</body>
</html>