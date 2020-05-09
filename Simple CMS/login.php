<?php
    include("header.php")
?>



    <img  src="img/bee.png"  class="logo">
    
    <form action="php/user.php" method="post" id="logForm">
       
        
        <div class="form-group">
            <label>Email</label>
            <input type="text" class="form-control" placeholder="Enter email" name="mail" id="mail">
            <span style="color:red;" id="notMail"></span>
        </div>
        
        <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" placeholder="Enter password" name="pass" id="password">
            <span style="color:red;" id="notPass"></span>
        </div>
        
       
        <div class="form-group">
            <input type="submit" class="btn btn-outline-primary" value="Log In" name="login">
        </div>

        <div class="form-group">
            Don't have account? <a href="signup.php">Sign Up</a>
        </div>
    </form>


    <script src="js/jquery-3.4.1.min.js">
    </script>
    <script src="js/valid.js"></script>
    <script>
        $("#login-btn").hide();

    </script>
</body>
</html>

