<#import "spring.ftl" as spring/>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<link rel="stylesheet" href="css/bootstrap.min.css">
		<script>
			let csrfHeader = "${_csrf.headerName}";
			let csrfToken = "${_csrf.token}";
		</script>

		<script src="js/jquery-3.3.0.slim.min.js"></script>
		<script src="js/popper.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/sockjs.min.js"></script>
		<script src="js/stomp.min.js"></script>
		<script src="js/dbchat.js"></script>
		<title>Chat</title>
	</head>
	<body onload="showOldMessages(); connect();" onunload="disconnect()" class="bg-dark text-white">
	<nav class="navbar">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<a class="nav-link" href="#" onclick="document.getElementById('logout').submit()">Log out</a>
			</li>
		</ul>
	</nav>

	<div style="margin: 1em">
		<div id="chat-area"></div>
		<input class="form-control" id="messagebox" onkeypress="onPressChatHandler(event); " placeholder="Your message"></input>
	</div>

	<form style="visibility: hidden" id ="logout" method="post" action="/logout">
		<input type="submit" value="Logout"/>
		<input name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>		
	</body>
</html>

