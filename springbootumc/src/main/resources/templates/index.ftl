
<html lang="en">
  <head>
  	<title>Login 01</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script type = "text/javascript"src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"> </script>
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" type="text/css">
	
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
         
    <link rel="stylesheet" type="text/css" href = "/css/login.css">

	</head>
	<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section"></h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
		      	<div class="icon d-flex align-items-center justify-content-center">
		      		<span class="fa fa-user-o"></span>
		      	</div>
		      	<h3 class="text-center mb-4">Sign In</h3>
				  <div>
					<h4 style="color:red"><#if noUser??>${noUser}</#if></h4>
				</div>
			<form action="/login" class="login-form" method="post">
		      		<div class="form-group">
		      			<input type="email" class="form-control rounded-left" id="email" placeholder="email" required name="email">
		      		</div>
		      		
	            <div class="form-group d-flex">
	              <input type="password" class="form-control rounded-left" placeholder="Password" required name="password">
	            </div>
	            <div class="form-group">
	            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Login</button>
	            </div>
				
				<div class="form-group d-md-flex">
	            	<div class="w-50">
	            		<div class="w-50 text-md-left">
							<a href="register">New User?</a>
						</div>
								</div>
								<div class="w-50 text-md-right">
									<a href="forgotpass">Forgot Password?</a>
								</div>
	            </div>
	          </form>
	        </div>
				</div>
			</div>
		</div>
	</section>


	</body>
</html>

