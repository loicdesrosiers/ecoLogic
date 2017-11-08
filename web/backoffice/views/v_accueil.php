<?php
/*
  Bug detectés :
  - /
*/
//  En tete
?>
<?php require_once(PATH_VIEWS.'header.php');?>
<div class="container">
<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Debut de la page -->
<form action="index.php?page=tab" class="form-signin" method="POST">
   <h2 class="form-signin-heading"><?= FORM_CONNECT ?></h2>
  <label class="sr-only" for="username"><?= FORM_USERNAME ?></label>
    <input type="text" class="form-control" name="username" placeholder="Nom d'utilisateur">
  <label class="sr-only" for="password"><?= FORM_PASSWORD ?></label>
  <input type="password" id="inputPassword" class="form-control mb-2 mr-sm-2 mb-sm-0" name="password" placeholder="Password">

  <button type="submit" class="btn btn-lg btn-primary btn-block"><?= FORM_CONNEXION ?></button>
</form>
<!--  Fin de la page -->
</div>
<!--  Pied de page -->
<?php
require_once(PATH_VIEWS.'footer.php');
