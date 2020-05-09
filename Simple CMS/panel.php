<?php require_once "./php/admin.php"?>


<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>CRUD</title>

    <style>
      form {
        width: 50%;
      }
    </style>
  </head>
  <body>

<?php if(isset($_SESSION['message'])) {?>
  <div class="alert alert-<?php echo $_SESSION['message_type']?>">
      <?php
          echo $_SESSION['message'];
          unset($_SESSION['message']);
      ?>
  </div>
<?php }?>

  <div class="container">

    
<h2>Your Products</h2>
<hr style="border: 1px solid gray">

<table class="table table-striped">
  <thead>
    <tr>
      <th>#</th>
      <th>Name</th>
      <th>Description</th>
      <th>Price</th>
      <th>Image</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <?php

      $result = $conn->query("select * from products") or die($conn->error);

      while($row = $result->fetch_assoc()){
        echo "<tr>";
        echo "<td>".$row['id']."</td>";
        echo "<td>".$row['name']."</td>";
        echo "<td>".$row['description']."</td>";
        echo "<td>".$row['price']."</td>";
        echo "<td> <img src='php/".$row['img_path']. "'width='100' height='100'></td>";
        echo "<td>
                <a href='panel.php?edit=" .$row['id']. 
                "'class='btn btn-warning'>Edit</a>
  
                <a href='panel.php?delete=" .$row['id']. 
            "'class='btn btn-danger'>Delete</a>
          </td>";
        echo "</tr>";
      }
    ?>
  </tbody>
</table>



<h2>Add new product</h2>
<hr style="border: 1px solid gray">
    <form action="php/admin.php" enctype="multipart/form-data" method="post" class="m-auto">
            <input type="hidden" value="<?php echo $id;?>" name="id">

            <div class="form-group">
                <label>Name</label>
                <input type="text"  class="form-control" placeholder="Enter name" name="name" value="<?php echo $name;?>">
            </div>
            
            <div class="form-group">
                <label>Description</label>
                <input type="text" class="form-control" placeholder="Enter description" name="description" value="<?php echo $description;?>">
            </div>
            
            <div class="form-group">
                <label>Price</label>
                <input type="text" class="form-control" placeholder="Enter price" name="price" value="<?php echo $price;?>">
            </div>

            <?php if($update == true):?>
            
            <div class="form-group">
                <input type="submit" class="btn btn-warning" value="Update" name="update">
            </div>    
            <?php else: ?>
              <div class="form-group">
                <label>Upload product photo</label>
                <input name="image" type="file" class="form-control-file">
              </div>  
              <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Add" name="add">
              </div>  
            <?php endif;?>
        </form>
    </div>




    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>