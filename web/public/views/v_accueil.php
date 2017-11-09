<?php
/*
  Bug detectés :
  - /
*/
//  En tete
?>
<?php require_once(PATH_VIEWS.'header.php');?>

<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Debut de la page -->
<div id="index-banner" class="parallax-container">
  <div class="section no-pad-bot">
    <div class="container">
      <br><br>
      <h1 class="header center teal-text text-lighten-2"><?= TITRE ?></h1>
      <div class="row center">
        <h5 class="header col s12 light"><?= SLOGAN ?></h5>
      </div>

    </div>
  </div>
  <div class="parallax"><img src="<?= PATH_IMAGES ?>.background1.jpg" alt="Unsplashed background img 1"></div>
</div>


<div class="container">
  <div class="section">



   <div class="col s12 m4">
        <div class="icon-block">
          <h2 class="center brown-text"><img src="<?= PATH_IMAGES ?>play_store.png" alt="Comming soon" width="20%"></h2>
          <h5 class="center"><?= PUB_AND ?></h5>

          
        </div>
      </div>

<?php /*
      <div class="col s12 m4">
        <div class="icon-block">
          <h5 class="center"><?= PUB_AND ?></h5>

          <p class="light"></p>
        </div>
      </div>
    </div>*/?>

  </div>
</div>


<!--div class="parallax-container valign-wrapper">
  <div class="section no-pad-bot">
    <div class="container">
      <div class="row center">
        <h5 class="header col s12 light">A modern responsive front-end framework based on Material Design</h5>
      </div>
    </div>
  </div>
  <div class="parallax"><img src="<?= PATH_IMAGES ?>.background2.jpg" alt="Unsplashed background img 2"></div>
</div>

<div class="container">
  <div class="section">

    <div class="row">
      <div class="col s12 center">
        <h3><i class="mdi-content-send brown-text"></i></h3>
        <h4>Contact Us</h4>
        <p class="left-align light">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque id nunc nec volutpat. Etiam pellentesque tristique arcu, non consequat magna fermentum ac. Cras ut ultricies eros. Maecenas eros justo, ullamcorper a sapien id, viverra ultrices eros. Morbi sem neque, posuere et pretium eget, bibendum sollicitudin lacus. Aliquam eleifend sollicitudin diam, eu mattis nisl maximus sed. Nulla imperdiet semper molestie. Morbi massa odio, condimentum sed ipsum ac, gravida ultrices erat. Nullam eget dignissim mauris, non tristique erat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;</p>
      </div>
    </div>

  </div>
</div>


<div class="parallax-container valign-wrapper">
  <div class="section no-pad-bot">
    <div class="container">
      <div class="row center">
        <h5 class="header col s12 light">A modern responsive front-end framework based on Material Design</h5>
      </div>
    </div>
  </div>
  <div class="parallax"><img src="<?= PATH_IMAGES ?>.background3.jpg" alt="Unsplashed background img 3"></div>
</div> --!>

<!--  Fin de la page -->

<!--  Pied de page -->
<?php
require_once(PATH_VIEWS.'footer.php');
