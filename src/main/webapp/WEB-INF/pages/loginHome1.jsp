<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<link rel="shortcut icon" href="img/logo (1).png">
		<!-- <title>Vijnana Vihara Nutakki</title> -->
		<title>GRETNALTES</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen" />
		<link href="css/animate.css" rel="stylesheet" media="screen" />
		<link href="css/main.css" rel="stylesheet" media="screen" />
		<link rel="stylesheet" type="text/css" href="css/datepicker.css" />
		<link href="css/barIndicator.css" rel="stylesheet" />
<!-- 		<link href="fonts/font-awesome.min.css" rel="stylesheet" /> -->

		<!-- HTML5 shiv and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->

<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
<style type="text/css">
.blog {
    margin-bottom: 20px;
    background: #eee;
    position: relative;
}
.blog-header {
    position: relative;
    padding: 2px 15px;
    border: none !important;
    border-bottom: 0;
}
span.has-error, #already_exist, .subjects_error
{
  font-weight:normal;
  color:red;
  margin:0px;
  display: block !important;
  position: absolute;
}
.lf
{
	margin-top: 8em;
}
@media (max-width:660px)
{
	.lf{
		margin-top: 1em;
	}
	#scrollUp{
		display: none !important; 
	}
}
  </style>
<script>
    history.forward();
</script>
	</head>
<body style="background: #8eb6c1;">

			<!-- Main Container starts -->
			<div class="main-container">

				<!-- Container fluid Starts -->
				<div class="container-fluid">

					<!-- Spacer starts -->
					<div class="spacer">
						<!-- Row Starts -->
						<div class="row" style="">
							<div class="col-lg-4 col-md-4 hidden-sm hidden-xs"></div>
							
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 fadeInDown animated">
								<div class="blog lf">
									<div class="blog-header" align="center" style="padding-top: 10px;background: #005C77;">
										<!-- <img src="img/VVN.png" align="middle" style="width: 65px;height: 65px;" alt="Vijnana Vihara Nutakki">
										&nbsp; &nbsp; &nbsp; <b style="font-size: 25px;color: white !important">VVN</b> -->
										<img src="img/logo.png" align="middle" style="height: ;" alt="GRETNALTES" class="img-responsive">
										&nbsp; &nbsp; &nbsp; <!-- <b style="font-size: 25px;color: white !important">GRETNALTES</b> -->
									</div>
									<div class="blog-body">

										<form id="login-form" action="LoginHome1" method="post" class="form-horizontal" role="form">
											<div class="form-group">
											    <label for="inputEmail3" class="col-lg-3 col-md-3 col-sm-3 col-xs-12 control-label">Role: </label>
											    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
										      		<select name="rolId" id="rolId" class="form-control" tabindex="1" required>
														<option value="">-- Choose Role --</option>
														<option value="1">Administrator</option>
														<option value="2">Faculty</option>
														<option value="3">Parent</option>
													</select>
												</div>
											</div>
											<div class="form-group">
											    <label for="inputEmail3" class="col-lg-3 col-md-3 col-sm-3 col-xs-12 control-label">Username: </label>
											    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
													<input type="text" tabindex="2" class="form-control" placeholder="Enter Username" autocomplete="off" name="name" id="name" required/>
												</div>
											</div>
											<div class="form-group">
											    <label for="inputEmail3" class="col-lg-3 col-md-3 col-sm-3 col-xs-12 control-label">Password: </label>
											    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
											    	<input type="password" tabindex="3" class="form-control" placeholder="Enter Password" name="password" id="password" required/>
											    </div>
											 </div>
											 <div class="form-group">
											 	<div class="col-sm-8 col-sm-offset-3">
											  		<input type="submit" class="btn btn-success" tabindex="4" value="Login"/>
											  		<button type="button" id="cancel" tabindex="5" class="btn btn-danger">Reset</button>
											  	</div>
											 </div>
											 <div class="form-group">
												<div class="col-sm-8 col-sm-offset-3">
												<%
													String message = null;
													message=(String)session.getAttribute("message");
											        if(message!=null)
											        {
														out.println("<span class='animated fadeOut' style='animation-iteration-count:1;animation-duration:8s;color: red;'>"+message+"</span>");
														session.setAttribute("message", null);
													}
										        %>
												</div>
											</div>
										</form>											
									</div>
								</div>
							</div>
							
							<div class="col-lg-4 col-md-4 hidden-sm hidden-xs"></div>
						</div>
						<!-- Row Ends -->
					</div>
					<!-- Spacer ends -->

				</div>
				<!-- Container fluid ends -->

			</div>
			<!-- Main Container ends -->

			<!-- Footer starts -->
			<!-- <footer class="navbar-fixed-bottom">Copyright Akshara Bharathi Vidyalayam, Mangalagiri.</footer> -->
			<!-- Footer ends -->

<script type="text/javascript">
  $("#login-form").validate({
	errorElement: 'span',
    errorClass: 'has-error',
	rules:
    {
		rolId:{required: true},
		name:{required: true},
		password:{required: true},
	},
	messages:
   	{
		rolId:{required: 'Select Role'},
		name:{required: 'Username'},
		password:{required: 'Password'},
	}	
  });
  $('#cancel').click(function () {
    $("#login-form").validate().resetForm();
    $("#login-form").removeClass("has-error");
    $("#rolId").val('');
    $("#name").val('');
    $("#password").val('');
    $("#login-form").addClass("form-horizontal");
  });
  
  $(function () {
	    $('input[placeholder]').blur();
	    $('#rol').on('change',function () {
	        var ph = $(this).val();
	        if(ph == 'Administrator')
	        {
	        	$("#uname").attr("placeholder", "Enter Username").blur();
	            $("#upass").attr("placeholder", "Enter Password").blur();
	        }
	        else if(ph == 'Faculty') 
	        {
	            $("#uname").attr("placeholder", "Enter Registered Faculty Name as Username").blur();
	            $("#upass").attr("placeholder", "Enter Registered Mobile Number as Password").blur();
	        }
	        else if(ph == 'Parent') 
	        {
	            $("#uname").attr("placeholder", "Enter Registered Father Name as Username").blur();
	            $("#upass").attr("placeholder", "Enter Registered Mobile Number as Password").blur();
	        }
	    });
	});

</script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/scrollup/jquery.scrollUp.js"></script>
    	<script src="js/flot/jquery.flot.tooltip.min.js"></script>
</body>
</html>