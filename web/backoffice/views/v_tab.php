<?php
/*
  Bug detectés :
  - /
*/
//  En tete
?>
<?php require_once(PATH_VIEWS.'headertab.php');?>

<div class="aler">
<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>
</div>
<!--  Debut de la page -->
<div class="tab">
<h1 class="display-2"><?php echo "Hello ".$_SESSION['name']." !"; ?></h1>
<form action="index.php?page=tab" method="post">
<button name="addqcm" type="submit" class="btn btn-outline-info"><?= ADD_QCM ?></button>
<button name="deleteqcm" type="submit" class="btn btn-outline-info"><?= DELET_QCM ?></button>
</form>
<!--  Fin de la page -->
</div>
<!--  Pied de page -->
<?php
require_once(PATH_VIEWS.'footer.php');
