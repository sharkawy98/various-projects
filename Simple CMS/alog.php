<?php session_start() ?>

<form action="" method="post">
<input type="text" placeholder="username" name="name" autocomplete="off">
<input type="password" placeholder="password" name="pass">
<input type="submit" name="admin">
</form>

<?php

if(isset($_POST["admin"])) {
    
    if ($_POST["name"] != "admin" && $_POST["pass"] != "admin")
        echo "<script>window.location.href='index.php';</script>";
        
       
    echo "<script>window.location.href='panel.php';</script>";
}
?>

