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

  </div>
</div>



<!--  Fin de la page -->

<!--  Pied de page -->
<?php
require_once(PATH_VIEWS.'footer.php');
