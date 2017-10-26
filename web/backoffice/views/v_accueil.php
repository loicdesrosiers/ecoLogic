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
<form action="index.php?page=membre" class="form-inline" method="POST">
  <label class="sr-only" for="username"><?= FORM_USERNAME ?></label>
  <div class="input-group mb-2 mr-sm-2 mb-sm-0">
    <div class="input-group-addon">@</div>
    <input type="text" class="form-control" name="username" placeholder="Nom d'utilisateur">
  </div>

  <label class="sr-only" for="password"><?= FORM_PASSWORD ?></label>
  <input type="password" class="form-control mb-2 mr-sm-2 mb-sm-0" name="password" placeholder="Password">

  <button type="submit" class="btn btn-primary"><?= FORM_CONNEXION ?></button>
</form>
<!--  Fin de la page -->

<!--  Pied de page -->
<?php
require_once(PATH_VIEWS.'footer.php');
