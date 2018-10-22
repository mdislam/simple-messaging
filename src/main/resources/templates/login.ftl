<#import "spring.ftl" as spring/>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<link rel="stylesheet" href="css/bootstrap.min.css">
		<script src="js/jquery-3.3.0.slim.min.js"></script>
		<script src="js/popper.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<title>Log in</title>
	</head>
	<body class="bg-dark text-white">
		<#if errorMessage??>
			<div class="text-danger text-center">${errorMessage}</div>
		</#if>

		<#if accountCreated?? && accountCreated>
			<div class="text-success text-center">Your account was created successfully!</div>
		</#if>
        <div class="container">
            <form method="POST" class="form-horizontal" role="form" action="/authenticate">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>        
        	<div class="form-group">
                    <label for="username" class="col-sm-2 control-label">Username</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="username" name="username" placeholder="Username"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Password</label>
                    <div class="col-md-10">
                        <input type="password" id="password" class="form-control" name="password" placeholder="Password"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <input type="submit" id="submitlogin" class="btn btn-primary" value="Log in"/>
                    </div>
                </div>
                    <div class="col-md-10">
                        <a id="register" href="/register">Don't have an account yet?<span></span></a>
                    </div>
                </div>
            </form>
        </div>

    </body>
</html>
