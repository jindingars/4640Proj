<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="stylesNew.css" type="text/css" rel="stylesheet"/>
<title>Home Page</title>
</head>
<body>

    <header>
    <h1>UVa Second Hand Market</h1>
    <p class='slogan'><span>Free website for selling!</span></p>
    </header>

    <script type="text/javascript">
      function setFocus() {
      // body...
      //document.mainform.desc.focus(); //access an object 
      document.getElementById('desc').focus();
     }
    </script>


    <form action="search.jsp" name="search" method="get">
      <div class= "searchBox">
        <input type="text" id = "taskdesc" name="content" />
        <font color="red"><i><span id="task-msg" ></span></font></i>
      </div>
      <div class="searchBtn">
      <input type="submit" id="add" value="search" class = "btn btn-submit" onclick="validateData()">
      </div>
    </form>


    <script type="text/javascript">
      function validateData(){
        if(document.getElementById('taskdesc').value == ""){
          document.getElementById("task-msg").innerHTML = "Please enter Key Word";
        }
        else
          document.getElementById("task-msg").innerHTML = "";
      }
    </script>

    <div id="base" class="">

    <nav>
    <div id="locations" >
      <div id="locations_div" ></div>
        <div id="locations_text">
          <p><span>Locations</span></p>
        </div>
      </div>

      <style type="text/css">
      h5.stacking {
        position: absolute;
        background: lightblue;
        opacity: 0.6;
        z-index: 10;
      }
      </style>
      

	
	  <div id = "login">
	  	<p><a href="login.jsp">Login</a></br><% String username = (String)session.getAttribute("username"); %>
		<% if (username == null){%>
		Welcome Guest   
		<%}  else{ %>
		Welcome <%=username %> 
		<%} %>
	  </div>
	  
	  <div id="logout">
	  	<p><a href="logout">Logout</a>
	  </div>
	  
      <div id="onGrounds" class="linkText">
        <p><a class="link" href = "http://localhost/on.php"><span>On Grounds</span></a></p>
      </div>

     
      <div id="theCorner" class="linkText">
        <p><a class="link" href = "http://localhost/corner.php"><span>The Corner</span></a></p>
      </div>

     
      <div id="northGrounds" class="linkText">
        <p><a class="link" href = "http://localhost/north.php"><span>North Grounds</span></a></p>
      </div>

      <div id="types" class="ax_default button">
        <div id="types_div" class=""></div>
        <div id="types_text" class="text ">
          <p><span>Types</span></p>
        </div>
      </div>

      <div id="books" >   
        <p><a class="link" href = "http://localhost/books.php"><span>Books</span></a></p> 
      </div>

      <div id="furnitures" >
        <p><a class="link" href = "http://localhost/Furnitures.php"><span>Furnitures</span></a></p>
      </div>

      <div id="clothes">
        <p><a class="link" href = "http://localhost/clothes.php"><span>Clothes</span></a></p>
      </div>


      <div id="kitchenTools">
        <p><a class="link" href = "http://localhost/kitchen.php"><span>Kitchen tools</span></a></p>
      </div>

      <div id="electricities">
        <p><a class="link" href = "http://localhost/elec.php"><span>Electricites</span></a></p>
      </div>

      <div id="freeSection">
        <p><a class="link" href = "http://localhost/free.php"><span>Others</span></a></p>
      </div>


      </nav>

      <script type="text/javascript">
        window.onload = function(){  
          var btn = document.getElementById("imgLeft");  
   
          btn.addEventListener("mouseover",function(){ document.getElementById("description").innerHTML = "It's on sale!";}); 
          btn.addEventListener("mouseout",function(){ document.getElementById("description").innerHTML = "";});  
          
        }  
        
      </script>

      <section>
      <div >
      <img id="imgLeft" class="img" src="clo.png"/>     
      <img id="imgRight" class="img"  src="gitar.jpg"/>
      <font color="red"><i><span id="description" ></span></font></i>
      </div>
      </section>
      

      <div id="u20" class="ax_default link_button">
        <div id="u20_div" class=""></div>
      </div>

 
      <style >
        .postn{
          position: fixed;
          background-color: lightblue;
          left: 850px;
          width: 250px;
          top: 200px;
          transition: width 2s,height 2s, background-color 2s, transform 2s;
        }
        .postn:hover {
          background-color: lightgray;
          width: 200px;
          height: 100px;

        }

        .welcome {
          position: absolute;
          top: 520px;
          left: 300px;
          width: 350px;
          font-size: 40;
          transform: rotate(-20deg);
          font-size: 150%;
          color: purple;
        }

      </style>
       
      <div class= "postn">
      <% if (username != null){%>
      <p><a href = 'post.html'>Post new items</a></p>
      <%}else{%>
    	  <p><a href="javascript:loginFirst()">Post new items</a></p>
      <%} %>
      </div>

      <div class="welcome">
         <p onclick="changetext(this)">Welcome to our website, click me:)</p>
      </div>

      <script>
        function changetext(id){
          id.innerHTML="Have a great day!";
        }
        
        function loginFirst(){
        		alert("Please login first:)");
        		window.location.assign("login.jsp");
        }
      </script>
   
      <div>
      <p><a href="register.html" class= "register_div">Register for our website</a></p>
      </div>
      
 
</body>
</html>