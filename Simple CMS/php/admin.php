<?php 

session_start();

    $id = 0;
    $name = "";
    $description = "";
    $price = "";
    $update = false;

    $conn = new mysqli("localhost", "root", "", "cms") or die($conn->error);

    if (isset($_POST['add'])) {

    $name = $_POST["name"];
    $description = $_POST["description"];
    $price = $_POST["price"];

    $filetmp = $_FILES['image']['tmp_name']; //temp file created by php
    $filename = $_FILES['image']['name']; //the original uploded file
    $filepath = "data/".$filename; // the path we created to save the cvs

    $conn->query("INSERT INTO `products`(`name`, `description`, `price`, `img_path`) VALUES 
            ('$name','$description',$price,'$filepath');") or die($conn->error);
    
    move_uploaded_file($filetmp, $filepath); //move temp file to our folder

    $_SESSION['message'] = "Record added successfuly !";
    $_SESSION['message_type'] = "primary";

    header("location: ../panel.php");
} 


if (isset($_GET['delete'])) {
    $id = $_GET['delete'];
    $conn->query("delete from products where id = '$id';") or die($conn->error);

    $_SESSION['message'] = "Record deleted successfuly !";
    $_SESSION['message_type'] = "danger";
}

if (isset($_GET['edit'])) {
    $id = $_GET['edit'];
    $result = $conn->query("select * from products where id = $id;") or die($conn->error);

    if ($result->num_rows != 0) {
        $row = $result->fetch_assoc();
        $id = $row['id'];
        $name = $row['name'];
        $description = $row['description'];
        $price = $row['price'];
        $update = true;
    }
}


if (isset($_POST['update'])) {
    $id = $_POST["id"];
    $name = $_POST["name"];
    $description = $_POST["description"];
    $price = $_POST["price"];

    
    $conn->query("UPDATE `products` SET `name`='$name',`description`='$description',`price`=$price WHERE id = $id;") or die($conn->error);
    
    move_uploaded_file($filetmp, $filepath); //move temp file to our folder

    $_SESSION['message'] = "Record updated successfuly !";
    $_SESSION['message_type'] = "warning";

    header("location: ../panel.php");
} 

?>