<?php
  include("header.php");
?>

     <!--Carousel Wrapper-->
  <div id="carousel-example-1z" class="carousel slide carousel-fade pt-0" data-ride="carousel">

<!--Indicators-->
<ol class="carousel-indicators">
  <li data-target="#carousel-example-1z" data-slide-to="0" class="active"></li>
  <li data-target="#carousel-example-1z" data-slide-to="1"></li>
  <li data-target="#carousel-example-1z" data-slide-to="2"></li>
</ol>
<!--/.Indicators-->

<!--Slides-->
<div class="carousel-inner" role="listbox">

  <!--First slide-->
  <div class="carousel-item active">
    <div class="view" style="background-image: url('img/cover2.jpg'); background-repeat: no-repeat; background-size: cover;">

      <!-- Mask & flexbox options-->
      <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

        <!-- Content -->
        <div class="text-center white-text mx-5 wow fadeIn">


          <a target="_blank" href="" class="btn btn-outline-warning btn-lg">
            Shop Now
            <i class="fas fa-cart-plus ml-2"></i>
          </a>
        </div>
        <!-- Content -->

      </div>
      <!-- Mask & flexbox options-->

    </div>
  </div>
  <!--/First slide-->

  <!--Second slide-->
  <div class="carousel-item">
    <div class="view" style="background-image: url('img/cover1.jpg'); background-repeat: no-repeat; background-size: cover;">

      <!-- Mask & flexbox options-->
      <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

        <!-- Content -->
        <div class="text-center white-text mx-5 wow fadeIn">


          <a target="_blank" href="" class="btn btn-outline-warning btn-lg">
            Shop Now
            <i class="fas fa-cart-plus ml-2"></i>
          </a>
        </div>
        <!-- Content -->

      </div>
      <!-- Mask & flexbox options-->

    </div>
  </div>
  <!--/Second slide-->

  <!--Third slide-->
  <div class="carousel-item">
    <div class="view" style="background-image: url('img/cover3.jpg'); background-repeat: no-repeat; background-size: cover;">

      <!-- Mask & flexbox options-->
      <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

        <!-- Content -->
        <div class="text-center white-text mx-5 wow fadeIn">


          <a target="_blank" href="" class="btn btn-outline-warning btn-lg">
            Shop Now
            <i class="fas fa-cart-plus ml-2"></i>
          </a>
        </div>
        <!-- Content -->

      </div>
      <!-- Mask & flexbox options-->

    </div>
  </div>
  <!--/Third slide-->

</div>
<!--/.Slides-->

<!--Controls-->
<a class="carousel-control-prev" href="#carousel-example-1z" role="button" data-slide="prev">
  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
  <span class="sr-only">Previous</span>
</a>
<a class="carousel-control-next" href="#carousel-example-1z" role="button" data-slide="next">
  <span class="carousel-control-next-icon" aria-hidden="true"></span>
  <span class="sr-only">Next</span>
</a>
<!--/.Controls-->

</div>
<!--/.Carousel Wrapper-->

  <!--Main layout-->
  <main>
  <div class="container">
      <!-- Jumbotron Header -->
      <header class="jumbotron my-4">
      <h1 class="display-3">BeeShop</h1>
      <p class="lead">Number one choice for your children clothes</p>      
    </header>

    <!-- Page Features -->
    <div class="row text-center">
    <?php
     
      $conn = new mysqli("localhost", "root", "", "cms") or die($conn->error);
      $result = $conn->query("select * from products") or die($conn->error);

      while($row = $result->fetch_assoc()){
        echo "<div class='col-lg-3 col-md-6 mb-4'>";
        echo "<div class='card h-100'>";
        echo "<img class='card-img-top' src='php/".$row['img_path'] . "'>";
        echo "<div class='card-body'>";
        echo "<h4 class='card-title'>".$row['name']." - ".$row['price'] . " $</h4>";
        echo "<p class='card-text'>".$row['description']."</p>";
        echo "</div>";
        echo "<div class='card-footer'>";
        echo "<a href='#' class='btn btn-warning'>Buy Now</a>";
        echo "</div></div></div>";
      }
    ?>      
    </div>
    <!-- /.row -->

  </div>
  </main>
  <!--Main layout-->

<?php
include ("footer.php");
?>
