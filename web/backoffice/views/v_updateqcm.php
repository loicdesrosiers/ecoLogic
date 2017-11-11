<?php
/*
  Bug detectés :
  - rien ne s'affiche dans les cellules.
*/
//  En tete
?>
<?php require_once(PATH_VIEWS.'headertab.php');?>
<div class="deleteqcm">
<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>
</div>
<!--  Debut de la page -->
<table class="table table-responsive">
  <thead>
    <tr>
      <th><?= TAB_ID ?></th>
      <th><?= TAB_INTITU ?></th>
      <th><?= TAB_REP1 ?></th>
      <th><?= TAB_REP2 ?></th>
      <th><?= TAB_REP3 ?></th>
      <th><?= TAB_REP4 ?></th>
      <th><?= TAB_GR ?></th>
      <th><?= TAB_EXPLIC ?></th>
      <th><?= TAB_SCORE ?></th>
    </tr>
  </thead>
  <tbody>

      <?php foreach($question as $value) { ?>
        <tr>
        <th scope="row"><?php echo $value->getId(); ?></th>
          <td><?php echo $value->getIntitule(); ?></td>
          <td><?php echo $value->getRep1(); ?></td>
          <td><?php echo $value->getRep2(); ?></td>
          <td><?php echo $value->getRep3(); ?></td>
          <td><?php echo $value->getRep4(); ?></td>
          <td><?php echo $value->getGoodrep(); ?></td>
          <td><?php echo $value->getExplication(); ?></td>
          <td><?php echo $value->getScore(); ?></td>
          </tr>
      <?php } ?>


  </tbody>
</table>
<div class="deleteqcm">
<form method="post" action="index.php">
  <div class="form-group">
    <label for="idQuestion"><?= FORM_ID ?></label>
    <select id="idQuestion" class="form-control">
      <?php foreach($question as $value) { ?>
          <option><?= $value->getId(); ?></option>
     <?php } ?>

    </select>
  </div>
  <button type="submit" class="btn btn-outline-success"><?= VALID ?></button>
</form>
</div>
<!--  Pied de page -->
<?php
require_once(PATH_VIEWS.'footer.php');
