<?php 

$conn = new mysqli("localhost", "root", "", "cms") or die($conn->error);


if(isset($_POST['signup'])) {

    $name= $_POST['name'];
    $mail= $_POST['mail'];
    $phone= $_POST['phone'];
    $pass= $_POST['pass'];

    $result= $conn->query("SELECT * FROM `users` WHERE email='$mail'") or die($conn->error);
    if($result->num_rows != 0)
        echo "<script> alert('This email is already Taken!');  window.location.href='../signup.php';</script>"; 


    $sql_insert="INSERT into `users` VALUES('$name','$phone','$mail','$pass');";
    $conn->query($sql_insert) or die($conn->error);

    echo "<script> alert('Succesful signup');  window.location.href='../index.php';</script>"; 
}



if(isset($_POST['login'])) {
    $mail= $_POST['mail'];
    $pass= $_POST['pass'];

    $result= $conn->query("SELECT * FROM `users` WHERE email='$mail' AND `password`='$pass'") or die($conn->error);

    if($result->num_rows != 1) {
        echo "<script> alert('Invalid email or password');  window.location.href='../login.php';</script>"; 
    }

    $row = $result->fetch_assoc();
    $_SESSION["uname"] = $row["name"];

    echo "<script> alert('Successful login');  window.location.href='../index.php';</script>"; 
}
?>
