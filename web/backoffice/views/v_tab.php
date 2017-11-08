<?php
/*
  Bug detectés :
  - /
*/
//  En tete
?>
<?php require_once(PATH_VIEWS.'headertab.php');?>
<div class="pos">
<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>

<!--  Debut de la page -->
<form action="index.php?page=tab" method="post">
<button name="addqcm" type="submit" class="btn btn-outline-info"><?= ADD_QCM ?></button>
<button name="updateqcm" type="submit" class="btn btn-outline-info"><?= MODIF_QCM ?></button>
</form>
<!--  Fin de la page -->
</div>
<!--  Pied de page -->
<?php
require_once(PATH_VIEWS.'footer.php');
