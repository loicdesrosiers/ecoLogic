<?php

?>
<!-- Menu du site -->

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
       <?php if(isset($_SESSION['logged'])){ ?>
       <li <?php echo ($page=='index' ? 'class="active"':'')?>>
         <a href="index.php?page=membre">
           <?= MENU_TABLEAU ?>
         </a>
       </li>
       <?php }?>
   </ul>
   <ul class="nav navbar-nav navbar-right">
   	<?php if(isset($_SESSION['logged'])) { ?>
   		<li <?php echo ($page=='index' ? 'class="active"':'')?>>
   					<a href="index.php?page=deconnexion">
   						<?= MENU_DECONNEXION ?>
   					</a>
   				</li>

   	<?php } else { ?>
   		<li <?php echo ($page=='index' ? 'class="active"':'')?>>
   					<a href="index.php">
   						<?= MENU_ACC ?>
   					</a>
   				</li>
   	<?php } ?>
   	</ul>
  </div>
</nav>
