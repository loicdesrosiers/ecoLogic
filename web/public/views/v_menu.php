<?php

?>
<!-- Menu du site -->
<nav class="white" role="navigation">
  <div class="nav-wrapper container">
    <a id="logo-container" href="#" class="brand-logo"><?= TITRE ?></a>
    <ul class="right hide-on-med-and-down">
     		<li><a href="index.php"><?= MENU_ACC ?></a></li>
        <li><a href="/backoffice/index.php"><?= MENU_ACCES_BO ?></a></li>
    </ul>

    <ul id="nav-mobile" class="side-nav">
      <li><a href="index.php"><?= MENU_ACC ?></a></li>
      <li><a href="/backoffice/index.php"><?= MENU_ACCES_BO ?></a></li>
    </ul>
    <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
  </div>
</nav>
