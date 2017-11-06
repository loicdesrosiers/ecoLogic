<?php

?>
<!-- Menu du site -->
<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand" href="#"><?= NAVIG ?></a>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <?php if(isset($_SESSION['logged'])){ ?>
      <li class="nav-item active">
        <a  class="nav-link" href="index.php?page=tab">
          <?= MENU_TABLEAU ?>
        </a>
      </li>
      <?php }?>
      <?php if(isset($_SESSION['logged'])) { ?>
     		<li class="nav-item active">
     					<a class="nav-link" href="index.php?page=deconnexion">
     						<?= MENU_DECONNEXION ?>
     					</a>
     				</li>

     	<?php } else { ?>
     		<li class="nav-item active">
     					<a class="nav-link" href="index.php">
     						<?= MENU_ACC ?>
     					</a>
     				</li>
     	<?php } ?>
      <li class="nav-item active">
        <a  class="nav-link" href="/backoffice/index.php">
      <?= MENU_ACCES_BO ?>
    </a>
  </li>
    </ul>
    <span class="navbar-text">
      <?= TITRE ?>
    </span>

  </div>
</nav>
