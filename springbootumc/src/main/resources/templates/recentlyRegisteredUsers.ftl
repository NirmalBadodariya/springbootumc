<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <link href="https://fonts.googleapis.com/css?family=Quicksand:400,600,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.html5.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.print.min.js"></script>
    <#--  <link rel="stylesheet" href="./css/adminHome.css">  -->
    <link rel="stylesheet" href = "/css/adminHome.css">
    <title>Website Menu #10</title>
  </head>
  <body>

  
    <div class="site-mobile-menu site-navbar-target">
        <div class="site-mobile-menu-header">
          <div class="site-mobile-menu-close mt-3">
            <span class="icon-close2 js-menu-toggle"></span>
          </div>
        </div>
        <div class="site-mobile-menu-body"></div>
      </div>



      <header class="site-navbar site-navbar-target bg-white" role="banner">

        <div class="container">
          <div class="row align-items-center position-relative">

            <div class="col-lg-4">
              <nav class="site-navigation text-right ml-auto " role="navigation">
                <ul class="site-menu main-menu js-clone-nav ml-auto d-none d-lg-block">
                  <li class="active"><a href="adminHome1.jsp" class="nav-link">Home</a></li>
                  <li><a href="EditDetails">Profile</a></li>
                  <li><button id="showUserDetails" class="btn btn-info">UserList</button></li>
                </ul>
              </nav>
            </div>
            <div class="col-lg-4 text-center">
              <div class="site-logo">
                <a href="adminHome1.jsp">AdminHome</a>
              </div>
                

              
            

          </div>
        </div>
          
      </header>
    
 <div id="tablediv">
<table cellspacing="0" id="showData"> 
    <thead> 
    <tr>
        <th style="text-align:start; width:33.3% !important;">ID</th> 
        <th style="text-align:start; width:33.3% !important;">Name</th> 
        <th style="text-align:start; width:33.3% !important;">Email</th> 
        
            
           </tr>    
    </thead>
    <tbody>
        
    </tbody> 
</table>
</div>
  
<script type="text/javascript">
$(document).ready(function() {
 $("#tablediv").hide();
     $("#showUserDetails").click(function(event){
           $.post('getRecentUsers',function(data) {
            if(data!=null){
                $("#showData").find("tr:gt(0)").remove();
                
                $("#showData").DataTable({
                    data : data,
                    columns: [
                        { data: 'id'},
                        { data: 'firstName'},
                        { data: 'email'}
                    ],
                    dom: 'Bfrtip',
                    buttons: [
                        'copy', 'csv', 'excel', 'pdf', 'print'
                    ]

                });
            }
            else{
                alert("Data is coming null");
            }
            });

            $("#tablediv").show();          
  });      
            
});
 
 
            
</script>

    <#--  <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.sticky.js"></script>
    <script src="js/main.js"></script>  -->
  </body>
</html>