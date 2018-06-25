<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
	<form name="login" action= "login" method="post" onsubmit="return validate(this)">
 		
 		Username:
 		<input type="text" name="name" id ="name">
 		<font color="red"><i><span id="name-msg"></span></i></font></br>

        Password: 
        <input type="password" name="pwd" id="pwd" />
        <font color="red"><i><span id="pwd-msg"></span></i></font></br>
        
        <input type="submit" name="submit" value="Login">
        <div>
        <a href ="register.html" >No account? Create one!</a>
        </div>
	</form>
	
	<div class="back">
      <p><a href="home.jsp" >Back to home page</a></p>
    </div>
	
	<script type="text/javascript">
		function validate(f){
			rightcount = 0
			if(f.name.value == '' && f.pwd.value == ''){
				document.getElementById("name-msg").innerHTML = 'Please enter a valid name!';
				document.getElementById("pwd-msg").innerHTML = 'Please enter a valid password!';
				return false;
				
			}

			if(f.name.value == ''){
				document.getElementById("name-msg").innerHTML = 'Please enter a valid name!';
				return false;
			}
			else{
				document.getElementById("name-msg").innerHTML = '';
				rightcount ++;
				
			}
			if(f.pwd.value == ''){
				document.getElementById("pwd-msg").innerHTML = 'Please enter a valid password!';
				return false;
			}
			else{
				document.getElementById("pwd-msg").innerHTML = '';
				rightcount ++;
				
			}
			
			if(rightcount == 2)
				return true;	
		}
	</script>
</body>
</html>