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
<form action="index.php?page=addqcm" method="post">
  <div class="form-group">
    <label for="intitule"><?= FORM_INTITU ?></label>
    <input type="text" class="form-control" id="intitule" name="intitule" placeholder="1+1=?">
  </div>
  <div class="form-group">
    <label for="rep1"><?= FORM_REP1 ?></label>
    <input type="text" class="form-control" id="rep1" name="rep1" placeholder="2">
  </div>
  <div class="form-group">
    <label for="rep2"><?= FORM_REP2 ?></label>
    <input type="text" class="form-control" id="rep2" name="rep2" placeholder="4">
  </div>
  <div class="form-group">
    <label for="rep3"><?= FORM_REP3 ?></label>
    <input type="text" class="form-control" id="rep3" name="rep3" placeholder="3">
  </div>
  <div class="form-group">
    <label for="rep4"><?= FORM_REP4 ?></label>
    <input type="text" class="form-control" id="rep4" name="rep4" placeholder="5">
  </div>
  <div class="form-group">
    <label for="bonnerep"><?= FORM_GOODREP ?></label>
    <input type="text" class="form-control" id="bonnerep" name="bonnerep" placeholder="2">
  </div>
  <div class="form-group">
    <label for="expl"><?= FORM_EXPL ?></label>
    <input type="text" class="form-control" id="expl" name="expl" placeholder="car c'est la vie">
  </div>
  <div class="form-group">
    <label for="score"><?= FORM_SCORE ?></label>
    <input type="text" class="form-control" id="score" name="score" placeholder="20">
  </div>
  <button type="submit" class="btn btn-outline-success"><?= VALID ?></button>

</form>

<!--  Pied de page -->
<?php
require_once(PATH_VIEWS.'footer.php');
