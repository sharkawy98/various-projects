<?php
    include("header.php")
?>

    
    <img  src="img/bee.png"  class="logo">
    
    <form action="php/user.php" method="post" id="signForm">
        <div class="form-group">
            <label>Name</label>
            <input type="text"  class="form-control" placeholder="Enter name" name="name" id ="name">
            <span style="color:red;" id="notName"></span>
        </div>
        
        <div class="form-group">
            <label>Phone</label>
            <input type="text" class="form-control" placeholder="Enter phone" name="phone" id="phone">
            <span style="color:red;" id="notPhone"></span>
        </div>
        
        <div class="form-group">
            <label>Email</label>
            <input type="email" class="form-control" placeholder="Enter email" name="mail" id="mail">
            <span style="color:red;" id="notMail"></span>
        </div>
        
        <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" placeholder="Enter password" name="pass" id="password">
            <span style="color:red;" id="notPass"></span>
        </div>    
        
        <div class="form-group">
            <input type="submit" class="btn btn-outline-primary" value="Create account" name="signup">
        </div>        
    </form>


    <script src="js/jquery-3.4.1.min.js">
    </script>
    <script src="js/valid.js"></script>
    <script>
        $("#signup-btn").hide();
    </script>
</body>
</html>