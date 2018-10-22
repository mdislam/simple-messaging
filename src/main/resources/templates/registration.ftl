<#import "spring.ftl" as spring/>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<link rel="stylesheet" href="css/bootstrap.min.css">
		<script src="js/jquery-3.3.0.slim.min.js"></script>
		<script src="js/popper.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<title>Register</title>
	</head>
	<body class="bg-dark text-white">
		<@spring.bind path="user"/>
		<#if errorMessage??>
			<div class="text-danger text-center">${errorMessage}</div>
		</#if>
		<div class="container">
			<form action="register" method="post" class="form-horizontal">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Username</label>
					<div class="col-md-10">
						<@spring.formInput "user.name"/>
						<@spring.showErrors "--" />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password</label>
					<div class="col-md-10">
						<@spring.formPasswordInput "user.password"/>
						<@spring.showErrors "--" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-10">
						<input type="submit" id="sumbit" value="Submit" class="btn btn-primary"  />
					</div>
				</div>
			</form>	
		</div>
	</body>
</html>

